package fr.univaix.iut.pokebattle.bot;

import fr.univaix.iut.pokebattle.twitter.Answer;
import fr.univaix.iut.pokebattle.twitter.Tweet;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;

public class TimerBotTest {
    @Test
    public void testAskWithTime() throws Exception {
        TimerBot timerBot = new TimerBot();
        Tweet tweet = new Tweet("nedseb", "#WakeMeUp 3 Min Plop Plop");
        Answer answer = timerBot.askWithTime(tweet);
        assertThat(answer.getDelay(TimeUnit.MINUTES)).isEqualTo(2);
        assertThat(answer.getText()).isEqualTo("@nedseb #DringDring Plop Plop");

        tweet = new Tweet("nedseb", "coucou");
        answer = timerBot.askWithTime(tweet);
        assertThat(answer.getText()).isEqualTo("@nedseb Pika pika");

    }
}
