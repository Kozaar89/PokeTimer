package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Answer;
import fr.univaix.iut.pokebattle.twitter.Tweet;

public interface TimedSmartCell extends SmartCell {
    Answer askWithTime(Tweet question);
}
