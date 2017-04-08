package com.gosolar2.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalTime;

/**
 * Created by Joshua King on 4/8/17.
 */
@Converter
public class LocalTimeToIntConverter implements AttributeConverter<LocalTime, Integer> {

	@Override public Integer convertToDatabaseColumn (LocalTime localTime) {
		int hourMills = localTime.getHour() * 60 * 60;
		int minMills = localTime.getMinute() * 60;
		int secMills = localTime.getSecond();
		return hourMills + minMills + secMills;
	}

	@Override public LocalTime convertToEntityAttribute (Integer s) {
		return LocalTime.ofSecondOfDay(s);
	}
}
