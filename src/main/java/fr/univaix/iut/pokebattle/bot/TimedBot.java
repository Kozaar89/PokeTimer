package fr.univaix.iut.pokebattle.bot;

import fr.univaix.iut.pokebattle.twitter.Answer;
import fr.univaix.iut.pokebattle.twitter.Tweet;

import java.util.concurrent.DelayQueue;

public interface TimedBot extends Bot {
    DelayQueue<Answer> getDelayQueue();

    Answer askWithTime(Tweet question);
}
