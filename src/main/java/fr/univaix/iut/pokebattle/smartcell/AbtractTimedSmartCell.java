package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Tweet;

public abstract class AbtractTimedSmartCell implements TimedSmartCell {
    @Override
    public String ask(Tweet question) {
        return askWithTime(question).getText();
    }
}
