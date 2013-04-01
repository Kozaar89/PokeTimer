package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Answer;
import fr.univaix.iut.pokebattle.twitter.Tweet;
import org.joda.time.DateTime;

/**
 * Reply to all.
 */
public class PokemonCriesCell extends AbtractTimedSmartCell {
    @Override
    public Answer askWithTime(Tweet question) {
        if (question.getScreenName() != null)
            return new Answer("@" + question.getScreenName() + " Pika pika", DateTime.now().plusSeconds(20));
        return new Answer("Pika pika", DateTime.now().plusSeconds(20));
    }
}
