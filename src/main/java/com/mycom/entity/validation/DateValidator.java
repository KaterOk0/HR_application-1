package com.mycom.entity.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator implements ConstraintValidator<Date, String> {
    private static final String DATE_PATTERN
            = "((19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";

    @Override
    public void initialize(Date paramA) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext ctx) {


        final Pattern pattern;
        Matcher matcher;

        pattern = Pattern.compile(DATE_PATTERN);
        matcher = pattern.matcher(date);
        if (matcher.matches()) {
            matcher.reset();
            if (matcher.find()) {
                String day = matcher.group(3);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(1));
                if ("31".equals(day)) {
                    // 31 день может быть только в месяцах
                    // 1, 3, 5, 7, 8, 10, 12
                    return Arrays.asList("01", "03",
                            "05", "07", "08", "10", "12")
                            .contains(month);
                } else if ("2".equals(month) || "02".equals(month)) {
                    // Проверяем февраль
                    if (year % 4 == 0) {
                        if (year % 100 == 0) {
                            if (year % 400 == 0) {
                                // Високосный год
                                return Integer.parseInt(day) <= 29;
                            }
                            // Невисокосный год
                            return Integer.parseInt(day) <= 28;
                        }
                        // Високосный год
                        return Integer.parseInt(day) <= 29;
                    } else {
                        // Невисокосный год
                        return Integer.parseInt(day) <= 28;
                    }

                } else {
                    return true;
                }
            }
        }
        return false;
    }
}