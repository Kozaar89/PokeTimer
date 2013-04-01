package fr.univaix.iut.pokebattle.twitter;

import fr.univaix.iut.pokebattle.bot.TimedBot;
import fr.univaix.iut.pokebattle.tuse.Credentials;
import fr.univaix.iut.pokebattle.tuse.TwitterUserStreamEasy;
import fr.univaix.iut.pokebattle.tuse.UserStreamAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.UserStreamListener;

import java.util.concurrent.DelayQueue;

public class TwitterUserStreamEasyBuilder {
    private final static Logger logger = LoggerFactory.getLogger(TwitterUserStreamEasyBuilder.class);
    private Credentials credentials;
    private Twitter twitter;
    private TimedBot bot;

    public TwitterUserStreamEasyBuilder(Credentials credentials, Twitter twitter, TimedBot bot) {
        this.credentials = credentials;
        this.twitter = twitter;
        this.bot = bot;
    }

    public TwitterUserStreamEasy build() {
        UserStreamListener listener = new UserStreamAdapter() {
            @Override
            public void onStatus(Status status) {
                logger.info("TwitterUserStreamEasyExample.onStatus()");
                try {
                    processNewQuestion(status, bot);
                } catch (TwitterException e) {
                    e.printStackTrace();
                }
            }
        };
        return new TwitterUserStreamEasy(listener, credentials);
    }

    private void processNewQuestion(Status status, TimedBot bot) throws TwitterException {
        if (isNotANewQuestion(status)) {
            logger.info("Ignored status change");
            return;
        }

        Answer answer = bot.askWithTime(new Tweet(status.getUser().getScreenName(), status.getText()));

        if (answer != null) {
            DelayQueue<Answer> delayQueue = bot.getDelayQueue();
            delayQueue.put(answer);
            logger.info("Answer posted to the event queue");
        }
    }

    private boolean isNotANewQuestion(Status status) throws TwitterException {
        return isTweetOfMe(status) || !isTweetForMe(status);
    }

    private boolean isTweetForMe(Status status) throws TwitterException {
        return status.getText().toLowerCase().contains(twitter.getScreenName().toLowerCase());
    }

    private boolean isTweetOfMe(Status status) throws TwitterException {
        return status.getUser().getId() == twitter.getId();
    }
}