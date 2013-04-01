package fr.univaix.iut.pokebattle.bot;

import fr.univaix.iut.pokebattle.smartcell.PokemonCriesCell;
import fr.univaix.iut.pokebattle.smartcell.TimedSmartCell;
import fr.univaix.iut.pokebattle.smartcell.TimerCell;
import fr.univaix.iut.pokebattle.twitter.Answer;
import fr.univaix.iut.pokebattle.twitter.Tweet;

import java.util.concurrent.DelayQueue;

public class TimerBot implements TimedBot {
    /**
     * List of smartcell the questions go through to
     * find an answer.
     */
    private final TimedSmartCell[] smartCells = new TimedSmartCell[]{
            new TimerCell(),
            new PokemonCriesCell(),
    };

    DelayQueue<Answer> delayQueue = new DelayQueue<Answer>();

    @Override
    public DelayQueue<Answer> getDelayQueue() {
        return delayQueue;
    }

    /**
     * Ask something to Bot, it will respond to you.
     *
     * @param question The question you ask.
     * @return An answer... or null if it doesn't get it.
     */
    @Override
    public Answer askWithTime(Tweet question) {
        for (TimedSmartCell cell : smartCells) {
            Answer answer = cell.askWithTime(question);
            if (answer != null)
                return answer;
        }
        return null;
    }

    @Override
    public String ask(Tweet question) {
        Answer answer = askWithTime(question);
        if (answer != null)
            return answer.getText();
        return null;
    }

}
