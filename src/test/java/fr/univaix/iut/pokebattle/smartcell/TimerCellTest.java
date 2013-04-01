package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Answer;
import fr.univaix.iut.pokebattle.twitter.Tweet;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: nedjar
 * Date: 01/04/13
 * Time: 22:10
 */
public class TimerCellTest {
    @Test
    public void testWakeUp() throws Exception {
        TimerCell timerCell = new TimerCell();
        Tweet tweet = new Tweet("nedseb", "#WakeMeUp 3 Min Plop Plop");
        Answer answer = timerCell.askWithTime(tweet);
        assertThat(answer.getDelay(TimeUnit.MINUTES)).isGreaterThanOrEqualTo(2).isLessThanOrEqualTo(3);
        assertThat(answer.getText()).isEqualTo("@nedseb #DringDring Plop Plop");
    }
}
