package com.zhitar.date_validation.util;

import com.zhitar.datevalidation.exception.DateValidationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;

import static com.zhitar.datevalidation.util.LocalDateUtil.*;
import static org.junit.Assert.*;

public class LocalDateUtilTest {

    private LocalDate now = LocalDate.now();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void canBePastBothDatesTest() throws Exception {
        LocalDate from = now.minusDays(25);
        LocalDate to = now.minusDays(20);

        assertTrue(validateFromToDates(canBePast(from), inclusive(canBePast(to))));
    }

    @Test
    public void canBePastSameDatesTest() throws Exception {
        LocalDate from = now.minusDays(2);

        assertTrue(validateFromToDates(canBePast(from), inclusive(canBePast(from))));
    }

    @Test
    public void fromDateGreaterToDateThrowExceptionTest() {
        LocalDate from = now.minusDays(6);
        LocalDate to = now.minusDays(7);

        thrown.expect(DateValidationException.class);
        thrown.expectMessage("toDate must be greater than fromDate");
        assertTrue(validateFromToDates(canBePast(from), inclusive(canBePast(to))));
    }

    @Test
    public void fromDateInPastEqualsToDateExclusiveThrowExceptionTest() {
        LocalDate from = now.minusDays(6);

        thrown.expect(DateValidationException.class);
        thrown.expectMessage("toDate must be greater than fromDate");
        assertTrue(validateFromToDates(canBePast(from), exclusive(canBePast(from))));
    }

    @Test
    public void bothDatesInFutureSameDateInclusiveTest() {
        LocalDate from = now.plusDays(1);
        LocalDate to = now.plusDays(1);

        assertTrue(validateFromToDates(mustBeFuture(from), inclusive(mustBeFuture(to))));
    }

    @Test
    public void bothDatesInFutureSameDateExclusiveThrowExceptionTest() {
        LocalDate from = now.plusDays(2);

        thrown.expect(DateValidationException.class);
        thrown.expectMessage("toDate must be greater than fromDate");
        assertTrue(validateFromToDates(mustBeFuture(from), exclusive(mustBeFuture(from))));
    }

    @Test
    public void bothDatesInFutureExclusiveTest() {
        LocalDate from = now.plusDays(10);
        LocalDate to = now.plusDays(11);

        assertTrue(validateFromToDates(mustBeFuture(from), exclusive(mustBeFuture(to))));
    }

    @Test
    public void fromDatePastToDateNowThrowExceptionTest() {
        LocalDate from = now.minusDays(3);

        thrown.expect(DateValidationException.class);
        thrown.expectMessage("LocalDate must be in future: " + now);
        assertTrue(validateFromToDates(canBePast(from), inclusive(mustBeFuture(now))));
    }

    @Test
    public void fromDatePastToDateFutureTest() {
        LocalDate from = now.minusDays(6);
        LocalDate to = now.plusDays(1);

        assertTrue(validateFromToDates(canBePast(from), inclusive(mustBeFuture(to))));
    }

    @Test
    public void fromDateCanBePastEqualsToDateFutureExclusiveThrowExceptionTest() {
        LocalDate from = now.plusDays(1);
        LocalDate to = now.plusDays(1);

        thrown.expect(DateValidationException.class);
        thrown.expectMessage("toDate must be greater than fromDate");
        assertTrue(validateFromToDates(canBePast(from), exclusive(mustBeFuture(to))));
    }
}