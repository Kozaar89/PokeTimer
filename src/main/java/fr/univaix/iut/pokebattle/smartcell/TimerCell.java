package fr.univaix.iut.pokebattle.smartcell;

import fr.univaix.iut.pokebattle.twitter.Answer;
import fr.univaix.iut.pokebattle.twitter.Tweet;
import org.joda.time.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimerCell extends AbtractTimedSmartCell {
    @Override
    public Answer askWithTime(Tweet question) {
        Pattern p = Pattern.compile(".*#WakeMeUp ?(\\d+) ?Min ?(.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(question.getText());
        if (matcher.matches() && question.getScreenName() != null) {
            int nbMinute = Integer.parseInt(matcher.group(1));
            String message = matcher.group(2);
            return new Answer("@" + question.getScreenName() + " #DringDring " + message, DateTime.now().plusMinutes(nbMinute));
        }
        return null;
    }
}
