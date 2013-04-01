package fr.univaix.iut.pokebattle.run;

import com.twitter.hbc.httpclient.ControlStreamException;
import fr.univaix.iut.pokebattle.bot.TimedBot;
import fr.univaix.iut.pokebattle.tuse.Credentials;
import fr.univaix.iut.pokebattle.twitter.TimedTwitterBot;

import java.io.IOException;
import java.io.InputStream;

public class BotRunner {
    static public void runBot(TimedBot bot, String credentialsFileName) {
        try {
            InputStream inputStream = getResourceAsStream(credentialsFileName);
            Credentials credentials = Credentials.loadCredentials(inputStream);
            TimedTwitterBot twitterBot = new TimedTwitterBot(bot, credentials);
            twitterBot.startBot();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ControlStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static InputStream getResourceAsStream(String fileName) {
        return BotRunner.class.getClassLoader().getResourceAsStream(fileName);
    }
}