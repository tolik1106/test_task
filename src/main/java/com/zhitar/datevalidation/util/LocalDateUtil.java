package com.zhitar.datevalidation.util;

import com.zhitar.datevalidation.exception.DateValidationException;

import java.time.LocalDate;

public class LocalDateUtil {

    private LocalDateUtil() {
    }

    public static boolean validateFromToDates(LocalDate fromDate, LocalDate toDate) {
        if (fromDate.isAfter(toDate)) {
            throw new DateValidationException("toDate must be greater than fromDate");
        }
        return true;
    }

    public static LocalDate canBePast(LocalDate localDate) {
        if (localDate == null) {
            return LocalDate.MIN;
        }
        return localDate;
    }

    public static LocalDate mustBeFuture(LocalDate localDate) {
        LocalDate today = LocalDate.now();
        if (localDate == null) {
            return LocalDate.MAX;
        }
        if (localDate.isBefore(today) || localDate.isEqual(today)) {
            throw new DateValidationException("LocalDate must be in future: " + localDate.toString());
        }
        return localDate;
    }

    public static LocalDate inclusive(LocalDate localDate) {
        return localDate;
    }

    public static LocalDate exclusive(LocalDate localDate) {
        return localDate.minusDays(1);
    }

}
