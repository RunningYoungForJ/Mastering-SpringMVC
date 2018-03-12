package masterSpringMvc.chapter3.utils;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by yangkun on 2018/3/12.
 */
public class USLocalDateFormatter implements Formatter<LocalDate>{
    private static final String US_PATTERN="MM/dd/yyyy";
    private static final String NORMAL_PATTERN="dd/MM/yyyy";

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(getPattern(locale)));
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return DateTimeFormatter.ofPattern(getPattern(locale)).format(localDate);
    }

    public static String getPattern(Locale locale){
        return isUnitStates(locale)?US_PATTERN:NORMAL_PATTERN;
    }

    public static boolean isUnitStates(Locale locale){
        return Locale.US.getCountry().equals(locale.getCountry());
    }
}
