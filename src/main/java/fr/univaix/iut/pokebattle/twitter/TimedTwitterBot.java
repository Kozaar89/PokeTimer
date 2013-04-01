package fr.univaix.iut.pokebattle.twitter;

import com.twitter.hbc.httpclient.ControlStreamException;
import fr.univaix.iut.pokebattle.bot.TimedBot;
import fr.univaix.iut.pokebattle.tuse.Credentials;
import fr.univaix.iut.pokebattle.tuse.TwitterUserStreamEasy;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.concurrent.DelayQueue;

public class TimedTwitterBot {
    private TwitterUserStreamEasy twitterUserStreamEasy;
    private Twitter twitter;
    private Runnable answersConsumer;

    public TimedTwitterBot(final TimedBot bot, Credentials credentials) {
        this.twitter = new TwitterBuilder(credentials).build();
        this.twitterUserStreamEasy = new TwitterUserStreamEasyBuilder(credentials, twitter, bot).build();
        answersConsumer = new Runnable() {
            @Override
            public void run() {
                DelayQueue<Answer> delayQueue = bot.getDelayQueue();
                while (true) {
                    Answer answer;
                    try {
                        answer = delayQueue.take();
                        System.out.println("Event take : " + answer);
                        twitter.updateStatus(answer.getText());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (TwitterException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public void startBot() throws InterruptedException, ControlStreamException, IOException {
        twitterUserStreamEasy.oauth();
        new Thread(answersConsumer).start();
    }
}
