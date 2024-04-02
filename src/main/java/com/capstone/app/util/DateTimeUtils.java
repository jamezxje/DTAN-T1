package com.capstone.app.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {
	public static String formatLocalDateTime(LocalDateTime datetime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return datetime.format(formatter);
	}

	public static long calculateTotalDays(LocalDateTime start, LocalDateTime end) {

		long diffSeconds = ChronoUnit.SECONDS.between(start, end);

		long diffDays = diffSeconds / 60 / 60 / 24; // diff days

		if (diffSeconds % (60 * 60 * 24) > 0) { // diff seconds
			diffDays++;
		}

		return diffDays;
	}
}

