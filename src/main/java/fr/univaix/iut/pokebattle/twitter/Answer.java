package fr.univaix.iut.pokebattle.twitter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.Period;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Answer implements Delayed {
    String text;
    DateTime date;

    public Answer(String text, DateTime date) {
        this.text = text;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public DateTime getDate() {
        return date;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Period period = new Period(date.getMillis() - DateTimeUtils.currentTimeMillis());
        long result = 0;
        switch (unit) {
            case NANOSECONDS:
                result = period.toStandardDuration().getMillis() * 1000 * 1000;
                break;
            case MICROSECONDS:
                result = period.toStandardDuration().getMillis() * 1000;
                break;
            case MILLISECONDS:
                result = period.toStandardDuration().getMillis();
                break;
            case SECONDS:
                result = period.toStandardSeconds().getSeconds();
                break;
            case MINUTES:
                result = period.toStandardMinutes().getMinutes();
                break;
            case HOURS:
                result = period.toStandardHours().getHours();
                break;
            case DAYS:
                result = period.toStandardDays().getDays();
                break;
        }
        return result;
    }

    @Override
    public int compareTo(Delayed o) {
        if (o instanceof Answer) {
            Answer answer = (Answer) o;
            return this.date.compareTo(answer.getDate());
        } else if (this.getDelay(TimeUnit.NANOSECONDS) > o.getDelay(TimeUnit.NANOSECONDS))
            return 1;
        else if (this.getDelay(TimeUnit.NANOSECONDS) < o.getDelay(TimeUnit.NANOSECONDS))
            return -1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
