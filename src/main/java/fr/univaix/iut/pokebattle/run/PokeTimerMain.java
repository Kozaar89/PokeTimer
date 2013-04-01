package fr.univaix.iut.pokebattle.run;

import fr.univaix.iut.pokebattle.bot.TimerBot;

public class PokeTimerMain {
    public static void main(String[] args) {
        BotRunner.runBot(new TimerBot(), "twitter4j.properties");
    }
}
