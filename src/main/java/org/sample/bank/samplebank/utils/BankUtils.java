package org.sample.bank.samplebank.utils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BankUtils {

	public static Double getAmount(Double principal, Date futureDate, Float roI) {
		long duration = futureDate.getTime() - (new Date()).getTime();

		long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
		Double intrest = (diffInDays * roI * principal) / 36500;
		return principal + Math.round(intrest*100)/100.0d;
	}
}
