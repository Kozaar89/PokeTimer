package fr.univaix.iut.pokebattle.twitter;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;

public class AnswerTest {
    @Test
    public void testGetDelay() throws Exception {
        Answer answer = new Answer("coucou", DateTime.now().plusMinutes(1));
        assertThat(answer.getDelay(TimeUnit.MILLISECONDS)).isPositive();

        answer = new Answer("coucou", DateTime.now().minusMinutes(1));
        assertThat(answer.getDelay(TimeUnit.MILLISECONDS)).isNegative();
    }

    @Test
    public void testCompareTo() throws Exception {
        Answer answerBeforeNow = new Answer("coucou", DateTime.now().plusMinutes(-1));
        Answer answerAfterNow = new Answer("coucou", DateTime.now().plusMinutes(1));
        Answer answerNow = new Answer("coucou", DateTime.now());

        assertThat(answerNow.compareTo(answerAfterNow)).isLessThan(0);
        assertThat(answerNow.compareTo(answerBeforeNow)).isGreaterThan(0);
        assertThat(answerNow.compareTo(answerNow)).isEqualTo(0);
    }
}
