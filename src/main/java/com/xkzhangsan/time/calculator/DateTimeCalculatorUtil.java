package com.xkzhangsan.time.calculator;

import static com.xkzhangsan.time.constants.Constant.MONTHDAY_FORMAT_PRE;

import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.xkzhangsan.time.TemporalAdjusterExtension;
import com.xkzhangsan.time.converter.DateTimeConverterUtil;
import com.xkzhangsan.time.enums.ConstellationNameEnum;
import com.xkzhangsan.time.enums.MonthNameEnum;
import com.xkzhangsan.time.enums.WeekNameEnum;
import com.xkzhangsan.time.enums.ZoneIdEnum;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

/**
 * 日期计算工具类
 * 包括：
 * 1.获取时间属性方法，get* 比如getYear(Date date) 获取年部分，getMonthCnLong(Date date)获取月份中文，getDayOfWeekCn(Date date)，获取星期中文
 * 2.获取时间加操作方法，plus* 比如plusYears(Date date, long amountToAdd) 当前时间年增加amountToAdd值
 * 3.获取时间减操作方法，minus* 比如minusYears(Date date, long amountToSubtract) 当前时间年减少amountToSubtract值
 * 4.获取时间修改属性方法，with* 比如withYear(Date date, long newValue) 修改当前时间年值为newValue
 * 5.获取比较2个时间方法，between* 比如betweenYears(Date startInclusive, Date endExclusive) 比较2个时间，获取年部分
 * 6.其他常用方法，比如isLeapYear(Date date) 判断是否闰年，isWeekend(Date date) 判断是否周末，isExpiry(String yearMonthStr) 是否过期等
 * 7.时区转换计算方法，transform*，比如transform(ZonedDateTime zonedDateTime, String zoneId)
 * 8.比较2个时间大小和相等方法，compare*，比如compare(Date date1, Date date2)
 * 9.获取准确的起始时间方法，start*,end*，比如startTimeOfMonth() 当月起始时间 当月第一天日期+00:00:00 endTimeOfMonth() 当月最后一天日期+23:59:59
 * 10.相同月日比较判断方法，isSameMonthDay*，betweenNextSameMonthDay*，nextSameMonthDay*， 比如用于生日，节日等周期性的日期比较判断
 * 11.星座计算方法，getConstellation*，比如getConstellationNameCn(String monthDayStr)，根据日期计算星座
 * 12.计算指定年月或起始时间区间的时间列表，get*List， 比如getDateList(int year, int month)，计算指定年月的时间列表
* @ClassName: DateTimeCalculatorUtil 
* @Description:  DateTime Calculator
* @author xkzhangsan
* @date 2019年12月1日
*
 */
public class DateTimeCalculatorUtil {
	
	private DateTimeCalculatorUtil(){
	}
	
	// get base time property
	
	/**
	 * 获取年，比如2020
	 * @param date
	 * @return
	 */
	public static int getYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getYear();
	}
	
	/**
	 * 获取年，比如2020
	 * @param instant
	 * @return
	 */
	public static int getYear(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getYear();
	}
	
	/**
	 * 获取年，比如2020
	 * LocalDateTime LocalDate ZonedDateTime 可以直接getYear()
	 * @param localDateTime
	 * @return
	 */
	public static int getYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getYear();
	}	
	
	/**
	 * 获取月， 比如 1
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getMonthValue();
	}
	
	/**
	 * 获取月， 比如 1
	 * @param instant
	 * @return
	 */
	public static int getMonth(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getMonthValue();
	}
	
	/**
	 * 获取月， 比如 1
	 * LocalDateTime LocalDate ZonedDateTime 可以直接getMonthValue()
	 * @param localDateTime
	 * @return
	 */
	public static int getMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getMonthValue();
	}
	
	
	
	/**
	 * 获取月英文全称， 比如 January
	 * @param date
	 * @return
	 */
	public static String getMonthEnLong(Date date){
		return MonthNameEnum.getFullNameEnByCode(getMonth(date));
	}
	
	/**
	 * 获取月英文全称， 比如 January
	 * @param instant
	 * @return
	 */
	public static String getMonthEnLong(Instant instant){
		return MonthNameEnum.getFullNameEnByCode(getMonth(instant));
	}
	
	/**
	 * 获取月英文全称， 比如 January
	 * LocalDateTime LocalDate ZonedDateTime 可以直接.getMonth().toString()
	 * @param localDateTime
	 * @return
	 */
	public static String getMonthEnLong(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return MonthNameEnum.getFullNameEnByCode(getMonth(localDateTime));
	}
	
	/**
	 * 获取月英文简称， 比如 Jan
	 * @param date
	 * @return
	 */
	public static String getMonthEnShort(Date date){
		return MonthNameEnum.getShortNameEnByCode(getMonth(date));
	}
	
	/**
	 * 获取月英文简称， 比如 Jan
	 * @param instant
	 * @return
	 */
	public static String getMonthEnShort(Instant instant){
		return MonthNameEnum.getShortNameEnByCode(getMonth(instant));
	}
	
	/**
	 * 获取月英文简称， 比如 Jan
	 * @param localDateTime
	 * @return
	 */
	public static String getMonthEnShort(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return MonthNameEnum.getShortNameEnByCode(getMonth(localDateTime));
	}
	
	/**
	 * 获取月份中文全称， 比如一月
	 * @param date
	 * @return
	 */
	public static String getMonthCnLong(Date date){
		return MonthNameEnum.getFullNameCnByCode(getMonth(date));
	}
	
	/**
	 * 获取月份中文全称， 比如一月
	 * @param instant
	 * @return
	 */
	public static String getMonthCnLong(Instant instant){
		return MonthNameEnum.getFullNameCnByCode(getMonth(instant));
	}
	
	/**
	 * 获取月份中文全称， 比如一月
	 * @param localDateTime
	 * @return
	 */
	public static String getMonthCnLong(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return MonthNameEnum.getFullNameCnByCode(getMonth(localDateTime));
	}
	
	/**
	 * 获取月份中文简称， 比如一
	 * @param date
	 * @return
	 */
	public static String getMonthCnShort(Date date){
		return MonthNameEnum.getShortNameCnByCode(getMonth(date));
	}
	
	/**
	 * 获取月份中文简称， 比如一
	 * @param instant
	 * @return
	 */
	public static String getMonthCnShort(Instant instant){
		return MonthNameEnum.getShortNameCnByCode(getMonth(instant));
	}
	
	/**
	 * 获取月份中文简称， 比如一
	 * @param localDateTime
	 * @return
	 */
	public static String getMonthCnShort(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return MonthNameEnum.getShortNameCnByCode(getMonth(localDateTime));
	}
	
	/**
	 * 获取天
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfMonth();
	}
	
	/**
	 * 获取天
	 * @param instant
	 * @return
	 */
	public static int getDayOfMonth(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfMonth();
	}
	
	/**
	 * 获取天
	 * LocalDateTime LocalDate ZonedDateTime 可以直接.getDayOfMonth()
	 * @param localDateTime
	 * @return
	 */
	public static int getDayOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getDayOfMonth();
	}
	
	/**
	 * 获取天（一年中）
	 * @param date
	 * @return
	 */
	public static int getDayOfYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfYear();
	}
	
	/**
	 * 获取天（一年中）
	 * @param instant
	 * @return
	 */
	public static int getDayOfYear(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfYear();
	}
	
	/**
	 * 获取天（一年中）
	 * LocalDateTime LocalDate ZonedDateTime 可以直接.getDayOfYear()获取
	 * @param localDateTime
	 * @return
	 */
	public static int getDayOfYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getDayOfYear();
	}
	
	/**
	 * 获取小时
	 * @param date
	 * @return
	 */
	public static int getHour(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getHour();
	}
	
	/**
	 * 获取小时
	 * @param instant
	 * @return
	 */
	public static int getHour(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getHour();
	}
	
	/**
	 * 获取小时
	 * LocalDateTime LocalTime ZonedDateTime 可以直接.getHour()获取
	 * @param localDateTime
	 * @return
	 */
	public static int getHour(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getHour();
	}	
	
	/**
	 * 获取分钟
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getMinute();
	}
	
	/**
	 * 获取分钟
	 * @param instant
	 * @return
	 */
	public static int getMinute(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getMinute();
	}
	
	/**
	 * 获取分钟
	 * LocalDateTime LocalTime ZonedDateTime 可以直接.getMinute()获取
	 * @param localDateTime
	 * @return
	 */
	public static int getMinute(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getMinute();
	}	
	
	/**
	 * 获取秒
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getSecond();
	}
	
	/**
	 * 获取秒
	 * @param instant
	 * @return
	 */
	public static int getSecond(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getSecond();
	}
	
	/**
	 * 获取秒
	 * LocalDateTime LocalTime ZonedDateTime 可以直接.getMinute()获取
	 * @param localDateTime
	 * @return
	 */
	public static int getSecond(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getSecond();
	}
	
	/**
	 * 获取当前时间的时间戳（毫秒）
	 * @return
	 */
	public static long getEpochMilli(){
		return Instant.now().toEpochMilli();
	}
	
	/**
	 * 根据年月日创建Date，时间部分为：00:00:00
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @return
	 */
	public static Date getDate(int year, int month, int dayOfMonth){
		return DateTimeConverterUtil.toDate(LocalDate.of(year, month, dayOfMonth));
	}
	
	// plus two times
	
	/**
	 * 增加年
	 * @param date
	 * @param amountToAdd
	 * @return
	 */
	public static Date plusYears(Date date, long amountToAdd){
		return plus(date, ChronoUnit.YEARS, amountToAdd);
	}
	
	public static LocalDateTime plusYears(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.YEARS, amountToAdd);
	}
	
	public static LocalDate plusYears(LocalDate localDate, long amountToAdd){
		return (LocalDate) plus(localDate, ChronoUnit.YEARS, amountToAdd);
	}
	
	public static Date plusMonths(Date date, long amountToAdd){
		return plus(date, ChronoUnit.MONTHS, amountToAdd);
	}
	
	public static LocalDateTime plusMonths(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.MONTHS, amountToAdd);
	}
	
	public static LocalDate plusMonths(LocalDate localDate, long amountToAdd){
		return (LocalDate) plus(localDate, ChronoUnit.MONTHS, amountToAdd);
	}
	
	public static Date plusWeeks(Date date, long amountToAdd){
		return plus(date, ChronoUnit.WEEKS, amountToAdd);
	}
	
	public static LocalDateTime plusWeeks(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.WEEKS, amountToAdd);
	}
	
	public static LocalDate plusWeeks(LocalDate localDate, long amountToAdd){
		return (LocalDate) plus(localDate, ChronoUnit.WEEKS, amountToAdd);
	}	
	
	public static Date plusDays(Date date, long amountToAdd){
		return plus(date, ChronoUnit.DAYS, amountToAdd);
	}
	
	public static LocalDateTime plusDays(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.DAYS, amountToAdd);
	}
	
	public static LocalDate plusDays(LocalDate localDate, long amountToAdd){
		return (LocalDate) plus(localDate, ChronoUnit.DAYS, amountToAdd);
	}
	
	public static Date plusHours(Date date, long amountToAdd){
		return plus(date, ChronoUnit.HOURS, amountToAdd);
	}
	
	public static LocalDateTime plusHours(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.HOURS, amountToAdd);
	}
	
	public static LocalTime plusHours(LocalTime localTime, long amountToAdd){
		return (LocalTime) plus(localTime, ChronoUnit.HOURS, amountToAdd);
	}
	
	public static Date plusMinutes(Date date, long amountToAdd){
		return plus(date, ChronoUnit.MINUTES, amountToAdd);
	}
	
	public static LocalDateTime plusMinutes(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.MINUTES, amountToAdd);
	}
	
	public static LocalTime plusMinutes(LocalTime localTime, long amountToAdd){
		return (LocalTime) plus(localTime, ChronoUnit.MINUTES, amountToAdd);
	}
	
	public static Date plusSeconds(Date date, long amountToAdd){
		return plus(date, ChronoUnit.SECONDS, amountToAdd);
	}
	
	public static LocalDateTime plusSeconds(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.SECONDS, amountToAdd);
	}
	
	public static LocalTime plusSeconds(LocalTime localTime, long amountToAdd){
		return (LocalTime) plus(localTime, ChronoUnit.SECONDS, amountToAdd);
	}
	
	/**
	 * 增加毫秒
	 * @param date
	 * @param amountToAdd
	 * @return
	 */
	public static Date plusMillis(Date date, long amountToAdd){
		return plus(date, ChronoUnit.MILLIS, amountToAdd);
	}
	
	public static LocalDateTime plusMillis(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) plus(localDateTime, ChronoUnit.MILLIS, amountToAdd);
	}
	
	public static LocalTime plusMillis(LocalTime localTime, long amountToAdd){
		return (LocalTime) plus(localTime, ChronoUnit.MILLIS, amountToAdd);
	}
	
	// minus two times
	
	/**
	 * 减少年
	 * @param date
	 * @param amountToSubtract
	 * @return
	 */
	public static Date minusYears(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.YEARS, amountToSubtract);
	}
	
	public static LocalDateTime minusYears(LocalDateTime localDateTime, long amountToSubtract){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.YEARS, amountToSubtract);
	}
	
	public static LocalDate minusYears(LocalDate localDate, long amountToSubtract){
		return (LocalDate) minus(localDate, ChronoUnit.YEARS, amountToSubtract);
	}
	
	public static Date minusMonths(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.MONTHS, amountToSubtract);
	}
	
	public static LocalDateTime minusMonths(LocalDateTime localDateTime, long amountToAdd){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.MONTHS, amountToAdd);
	}
	
	public static LocalDate minusMonths(LocalDate localDate, long amountToSubtract){
		return (LocalDate) minus(localDate, ChronoUnit.MONTHS, amountToSubtract);
	}
	
	public static Date minusWeeks(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.WEEKS, amountToSubtract);
	}
	
	public static LocalDateTime minusWeeks(LocalDateTime localDateTime, long amountToSubtract){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.WEEKS, amountToSubtract);
	}
	
	public static LocalDate minusWeeks(LocalDate localDate, long amountToSubtract){
		return (LocalDate) minus(localDate, ChronoUnit.WEEKS, amountToSubtract);
	}	
	
	public static Date minusDays(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.DAYS, amountToSubtract);
	}
	
	public static LocalDateTime minusDays(LocalDateTime localDateTime, long amountToSubtract){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.DAYS, amountToSubtract);
	}
	
	public static LocalDate minusDays(LocalDate localDate, long amountToSubtract){
		return (LocalDate) minus(localDate, ChronoUnit.DAYS, amountToSubtract);
	}
	
	public static Date minusHours(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.HOURS, amountToSubtract);
	}
	
	public static LocalDateTime minusHours(LocalDateTime localDateTime, long amountToSubtract){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.HOURS, amountToSubtract);
	}
	
	public static LocalTime minusHours(LocalTime localTime, long amountToSubtract){
		return (LocalTime) minus(localTime, ChronoUnit.HOURS, amountToSubtract);
	}
	
	public static Date minusMinutes(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.MINUTES, amountToSubtract);
	}
	
	public static LocalDateTime minusMinutes(LocalDateTime localDateTime, long amountToSubtract){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.MINUTES, amountToSubtract);
	}
	
	public static LocalTime minusMinutes(LocalTime localTime, long amountToSubtract){
		return (LocalTime) minus(localTime, ChronoUnit.MINUTES, amountToSubtract);
	}
	
	public static Date minusSeconds(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.SECONDS, amountToSubtract);
	}
	
	public static LocalDateTime minusSeconds(LocalDateTime localDateTime, long amountToSubtract){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.SECONDS, amountToSubtract);
	}
	
	public static LocalTime minusSeconds(LocalTime localTime, long amountToSubtract){
		return (LocalTime) minus(localTime, ChronoUnit.SECONDS, amountToSubtract);
	}
	
	public static Date minusMillis(Date date, long amountToSubtract){
		return minus(date, ChronoUnit.MILLIS, amountToSubtract);
	}
	
	public static LocalDateTime minusMillis(LocalDateTime localDateTime, long amountToSubtract){
		return (LocalDateTime) minus(localDateTime, ChronoUnit.MILLIS, amountToSubtract);
	}
	
	public static LocalTime minusMillis(LocalTime localTime, long amountToSubtract){
		return (LocalTime) minus(localTime, ChronoUnit.MILLIS, amountToSubtract);
	}	
	
	// modify property
	
	/**
	 * 修改年属性
	 * @param date
	 * @param newValue
	 * @return
	 */
	public static Date withYear(Date date, long newValue){
		return with(date, ChronoField.YEAR, newValue);
	}
	
	public static LocalDateTime withYear(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.YEAR, newValue);
	}
	
	public static LocalDate withYear(LocalDate localDate, long newValue){
		return (LocalDate) with(localDate, ChronoField.YEAR, newValue);
	}
	
	public static Date withMonth(Date date, long newValue){
		return with(date, ChronoField.MONTH_OF_YEAR, newValue);
	}
	
	public static LocalDateTime withMonth(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.MONTH_OF_YEAR, newValue);
	}
	
	public static LocalDate withMonth(LocalDate localDate, long newValue){
		return (LocalDate) with(localDate, ChronoField.MONTH_OF_YEAR, newValue);
	}
	
	public static Date withDayOfMonth(Date date, long newValue){
		return with(date, ChronoField.DAY_OF_MONTH, newValue);
	}
	
	public static LocalDateTime withDayOfMonth(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.DAY_OF_MONTH, newValue);
	}
	
	public static LocalDate withDayOfMonth(LocalDate localDate, long newValue){
		return (LocalDate) with(localDate, ChronoField.DAY_OF_MONTH, newValue);
	}	
	
	public static Date withDayOfYear(Date date, long newValue){
		return with(date, ChronoField.DAY_OF_YEAR, newValue);
	}
	
	public static LocalDateTime withDayOfYear(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.DAY_OF_YEAR, newValue);
	}
	
	public static LocalDate withDayOfYear(LocalDate localDate, long newValue){
		return (LocalDate) with(localDate, ChronoField.DAY_OF_YEAR, newValue);
	}
	
	public static Date withHour(Date date, long newValue){
		return with(date, ChronoField.HOUR_OF_DAY, newValue);
	}
	
	public static LocalDateTime withHour(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.HOUR_OF_DAY, newValue);
	}
	
	public static LocalTime withHour(LocalTime localTime, long newValue){
		return (LocalTime) with(localTime, ChronoField.HOUR_OF_DAY, newValue);
	}
	
	public static Date withMinute(Date date, long newValue){
		return with(date, ChronoField.MINUTE_OF_HOUR, newValue);
	}
	
	public static LocalDateTime withMinute(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.MINUTE_OF_HOUR, newValue);
	}
	
	public static LocalTime withMinute(LocalTime localTime, long newValue){
		return (LocalTime) with(localTime, ChronoField.MINUTE_OF_HOUR, newValue);
	}
	
	public static Date withSecond(Date date, long newValue){
		return with(date, ChronoField.SECOND_OF_MINUTE, newValue);
	}
	
	public static LocalDateTime withSecond(LocalDateTime localDateTime, long newValue){
		return (LocalDateTime) with(localDateTime, ChronoField.SECOND_OF_MINUTE, newValue);
	}
	
	public static LocalTime withSecond(LocalTime localTime, long newValue){
		return (LocalTime) with(localTime, ChronoField.SECOND_OF_MINUTE, newValue);
	}
	
	// get the difference between two times
	
	/**
	 * 获取2个日期的相差年月天的年数部分，不是相差总年数，
	 * 比如2020-02-29 2021-03-07，返回1
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenYears(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的年数部分，不是相差总年数，
	 * 比如2020-02-29 2021-03-07，返回1
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenYears(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的年数部分，不是相差总年数，
	 * 比如2020-02-29 2021-03-07，返回1
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenYears(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getYears();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分，不是相差总月数，
	 * 比如2020-02-29 2021-03-07，返回0
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenMonths(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分，不是相差总月数，
	 * 比如2020-02-29 2021-03-07，返回0
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenMonths(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的月数部分，不是相差总月数，
	 * 比如2020-02-29 2021-03-07，返回0
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenMonths(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getMonths();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分，不是相差总天数，
	 * 比如2020-02-29 2021-03-07，返回7
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenDays(LocalDateTime startInclusive, LocalDateTime endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getDays();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分，不是相差总天数，
	 * 比如2020-02-29 2021-03-07，返回7
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenDays(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return Period.between(DateTimeConverterUtil.toLocalDate(startInclusive),
				DateTimeConverterUtil.toLocalDate(endExclusive)).getDays();
	}
	
	/**
	 * 获取2个日期的相差年月天的天数部分，不是相差总天数，
	 * 比如2020-02-29 2021-03-07，返回7
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenDays(LocalDate startInclusive, LocalDate endExclusive){
		return Period.between(startInclusive, endExclusive).getDays();
	}
	
	/**
	 * 获取2个日期的相差总天数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalDays(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toDays();
	}
	
	/**
	 * 获取2个日期的相差总天数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalDays(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toDays();
	}
	
	/**
	 * 获取2个日期的相差总小时数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalHours(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toHours();
	}
	
	/**
	 * 获取2个日期的相差总小时数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalHours(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toHours();
	}
	
	/**
	 * 获取2个日期的相差总小时数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalHours(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toHours();
	}
	
	/**
	 * 获取2个日期的相差总分钟数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMinutes(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMinutes();
	}
	
	/**
	 * 获取2个日期的相差总分钟数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMinutes(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMinutes();
	}
	
	/**
	 * 获取2个日期的相差总分钟数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMinutes(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toMinutes();
	}
	
	/**
	 * 获取2个日期的相差总秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalSeconds(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).getSeconds();
	}
	
	/**
	 * 获取2个日期的相差总秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalSeconds(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).getSeconds();
	}
	
	/**
	 * 获取2个日期的相差总秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalSeconds(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).getSeconds();
	}
	
	/**
	 * 获取2个日期的相差总毫秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMillis(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMillis();
	}
	
	/**
	 * 获取2个日期的相差总毫秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMillis(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toMillis();
	}
	
	/**
	 * 获取2个日期的相差总毫秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalMillis(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toMillis();
	}	
	
	/**
	 * 获取2个日期的相差总纳秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalNanos(LocalDateTime startInclusive, LocalDateTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toNanos();
	}
	
	/**
	 * 获取2个日期的相差总纳秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalNanos(LocalTime startInclusive, LocalTime endExclusive){
		return Duration.between(startInclusive, endExclusive).toNanos();
	}
	
	/**
	 * 获取2个日期的相差总纳秒数
	 * @param startInclusive
	 * @param endExclusive
	 * @return
	 */
	public static long betweenTotalNanos(Date startInclusive, Date endExclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endExclusive, "endExclusive");
		return durationBetween(DateTimeConverterUtil.toLocalDateTime(startInclusive), DateTimeConverterUtil.toLocalDateTime(endExclusive)).toNanos();
	}
	
	
	// get other properties
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).getDayOfWeek().getValue();
	}
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param localDateTime
	 * @return
	 */
	public static int getDayOfWeek(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.getDayOfWeek().getValue();
	}
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param localDate
	 * @return
	 */
	public static int getDayOfWeek(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.getDayOfWeek().getValue();
	}	
	
	/**
	 * 获取星期值 1-7，星期一到星期日
	 * @param instant
	 * @return
	 */
	public static int getDayOfWeek(Instant instant){
		return DateTimeConverterUtil.toLocalDateTime(instant).getDayOfWeek().getValue();
	}
	
	/**
	 * 获取星期英文全称，比如Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekEnLong(Date date){
		return WeekNameEnum.getFullNameEnByCode(getDayOfWeek(date));
	}
	
	/**
	 * 获取星期英文全称，比如Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
	 * @param localDateTime
	 * @return
	 */
	public static String getDayOfWeekEnLong(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return WeekNameEnum.getFullNameEnByCode(getDayOfWeek(localDateTime));
	}
	
	/**
	 * 获取星期英文全称，比如Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
	 * @param localDate
	 * @return
	 */
	public static String getDayOfWeekEnLong(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return WeekNameEnum.getFullNameEnByCode(getDayOfWeek(localDate));
	}	
	
	/**
	 * 获取星期英文全称，比如Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday
	 * @param instant
	 * @return
	 */
	public static String getDayOfWeekEnLong(Instant instant){
		return WeekNameEnum.getFullNameEnByCode(getDayOfWeek(instant));
	}
	
	/**
	 * 获取星期英文简称，比如Mon
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekEnShort(Date date){
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(date));
	}
	
	/**
	 * 获取星期英文简称，比如Mon
	 * @param localDateTime
	 * @return
	 */
	public static String getDayOfWeekEnShort(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(localDateTime));
	}
	
	/**
	 * 获取星期英文简称，比如Mon
	 * @param localDate
	 * @return
	 */
	public static String getDayOfWeekEnShort(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(localDate));
	}	
	
	/**
	 * 获取星期英文简称，比如Mon
	 * @param instant
	 * @return
	 */
	public static String getDayOfWeekEnShort(Instant instant){
		return WeekNameEnum.getShortNameEnByCode(getDayOfWeek(instant));
	}	
	
	
	/**
	 * 获取星期中文，比如星期一
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekCn(Date date){
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(date));
	}
	
	/**
	 * 获取星期中文，比如星期一
	 * @param localDateTime
	 * @return
	 */
	public static String getDayOfWeekCn(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(localDateTime));
	}
	
	/**
	 * 获取星期中文，比如星期一
	 * @param localDate
	 * @return
	 */
	public static String getDayOfWeekCn(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(localDate));
	}	
	
	/**
	 * 获取星期中文，比如星期一
	 * @param instant
	 * @return
	 */
	public static String getDayOfWeekCn(Instant instant){
		return WeekNameEnum.getNameCnByCode(getDayOfWeek(instant));
	}
	
	/**
	 * 获取当前月的第一天
	 * @param localDate
	 * @return
	 */
	public static LocalDate firstDayOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.firstDayOfMonth());
	}
	
	/**
	 * 获取当前月的第一天
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime firstDayOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(TemporalAdjusters.firstDayOfMonth());
	}
	
	/**
	 * 获取当前月的第一天
	 * @param date
	 * @return
	 */
	public static Date firstDayOfMonth(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(TemporalAdjusters.firstDayOfMonth()));
	}	
	
	/**
	 * 获取当前月的最后一天
	 * @param localDate
	 * @return
	 */
	public static LocalDate lastDayOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.lastDayOfMonth());
	}
	
	/**
	 * 获取当前月的最后一天
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime lastDayOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
	}
	
	/**
	 * 获取当前月的最后一天
	 * @param date
	 * @return
	 */
	public static Date lastDayOfMonth(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(TemporalAdjusters.lastDayOfMonth()));
	}	
	
	/**
	 * 判断是否闰年
	 * @param localDate
	 * @return
	 */
	public static boolean isLeapYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.isLeapYear();
	}
	
	/**
	 * 判断是否闰年
	 * @param localDateTime
	 * @return
	 */
	public static boolean isLeapYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().isLeapYear();
	}
	
	/**
	 * 判断是否闰年
	 * @param date
	 * @return
	 */
	public static boolean isLeapYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().isLeapYear();
	}
	
	/**
	 * 判断是否闰年
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year){
		return ((year % 4) == 0) && ((year % 100) != 0 || (year % 400) == 0);
	}
	
	/**
	 * 下一个闰年
	 * @param localDate
	 * @return
	 */
	public static int nextLeapYear(int year){
		for (int i = 0; i < 8; i++) {
			year++;
			if(isLeapYear(year)){
				return year;
			}
		}
		return -1;
	}
	
	/**
	 * 下一个闰年
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime nextLeapYear(LocalDateTime localDateTime){
		return localDateTime.withYear(nextLeapYear(localDateTime.getYear()));
	}
	
	/**
	 * 下一个闰年
	 * @param localDate
	 * @return
	 */
	public static LocalDate nextLeapYear(LocalDate localDate){
		return localDate.withYear(nextLeapYear(localDate.getYear()));
	}
	
	/**
	 * 下一个闰年
	 * @param date
	 * @return
	 */
	public static Date nextLeapYear(Date date){
		return DateTimeConverterUtil.toDate(nextLeapYear(DateTimeConverterUtil.toLocalDateTime(date)));
	}
	
	/**
	 * 判断是否工作日 （周一到周五）
	 * @param date
	 * @return
	 */
	public static boolean isWorkDay(Date date){
		int dayOfWeek = getDayOfWeek(date);
		if(dayOfWeek == 6 || dayOfWeek == 7){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断是否工作日 （周一到周五）
	 * @param localDateTime
	 * @return
	 */
	public static boolean isWorkDay(LocalDateTime localDateTime){
		int dayOfWeek = getDayOfWeek(localDateTime);
		if(dayOfWeek == 6 || dayOfWeek == 7){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断是否工作日 （周一到周五）
	 * @param localDate
	 * @return
	 */
	public static boolean isWorkDay(LocalDate localDate){
		int dayOfWeek = getDayOfWeek(localDate);
		if(dayOfWeek == 6 || dayOfWeek == 7){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 判断是否周末（周六周日）
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date){
		return ! isWorkDay(date);
	}
	
	/**
	 * 判断是否周末（周六周日）
	 * @param localDateTime
	 * @return
	 */
	public static boolean isWeekend(LocalDateTime localDateTime){
		return ! isWorkDay(localDateTime);
	}
	
	/**
	 * 判断是否周末（周六周日）
	 * @param localDate
	 * @return
	 */
	public static boolean isWeekend(LocalDate localDate){
		return ! isWorkDay(localDate);
	}
	
	/**
	 * 获取月的天数
	 * @param localDate
	 * @return
	 */
	public static int lengthOfMonth(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.lengthOfMonth();
	}
	
	/**
	 * 获取月的天数
	 * @param localDateTime
	 * @return
	 */
	public static int lengthOfMonth(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().lengthOfMonth();
	}
	
	/**
	 * 获取月的天数
	 * @param date
	 * @return
	 */
	public static int lengthOfMonth(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().lengthOfMonth();
	}
	
	/**
	 *  获取年的天数
	 * @param localDate
	 * @return
	 */
	public static int lengthOfYear(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.lengthOfYear();
	}
	
	/**
	 * 获取年的天数
	 * @param localDateTime
	 * @return
	 */
	public static int lengthOfYear(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.toLocalDate().lengthOfYear();
	}
	
	/**
	 * 获取年的天数
	 * @param date
	 * @return
	 */
	public static int lengthOfYear(Date date){
		return DateTimeConverterUtil.toLocalDateTime(date).toLocalDate().lengthOfYear();
	}	
	
	/**
	 * 下一个星期几
	 * @param localDate
	 * @param dayOfWeek
	 * @return
	 */
	public static LocalDate next(LocalDate localDate, DayOfWeek dayOfWeek){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.next(dayOfWeek));
	}
	
	/**
	 * 下一个星期几
	 * @param localDateTime
	 * @param dayOfWeek
	 * @return
	 */
	public static LocalDateTime next(LocalDateTime localDateTime, DayOfWeek dayOfWeek){
		return localDateTime.with(TemporalAdjusters.next(dayOfWeek));
	}

	/**
	 * 下一个星期几
	 * @param date
	 * @param dayOfWeek
	 * @return
	 */
	public static Date next(Date date, DayOfWeek dayOfWeek){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(TemporalAdjusters.next(dayOfWeek)));
	}
	
	
	/**
	 * 上一个星期几
	 * @param localDate
	 * @param dayOfWeek
	 * @return
	 */
	public static LocalDate previous(LocalDate localDate, DayOfWeek dayOfWeek){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusters.previous(dayOfWeek));
	}
	
	/**
	 * 上一个星期几
	 * @param localDateTime
	 * @param dayOfWeek
	 * @return
	 */
	public static LocalDateTime previous(LocalDateTime localDateTime, DayOfWeek dayOfWeek){
		return localDateTime.with(TemporalAdjusters.previous(dayOfWeek));
	}

	/**
	 * 上一个星期几
	 * @param date
	 * @param dayOfWeek
	 * @return
	 */
	public static Date previous(Date date, DayOfWeek dayOfWeek){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(TemporalAdjusters.previous(dayOfWeek)));
	}
	
	/**
	 * 获下一个工作日
	 * @param localDate
	 * @return
	 */
	public static LocalDate nextWorkDay(LocalDate localDate){
		Objects.requireNonNull(localDate, "localDate");
		return localDate.with(TemporalAdjusterExtension.nextWorkDay());
	}
	
	/**
	 * 获下一个工作日
	 * @param localDateTime
	 * @return
	 */
	public static LocalDateTime nextWorkDay(LocalDateTime localDateTime){
		Objects.requireNonNull(localDateTime, "localDateTime");
		return localDateTime.with(TemporalAdjusterExtension.nextWorkDay());
	}
	
	/**
	 * 获下一个工作日
	 * @param date
	 * @return
	 */
	public static Date nextWorkDay(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(TemporalAdjusterExtension.nextWorkDay()));
	}
	
	/**
	 * 获取当前系统当前时区时间
	 * @param zoneId
	 * @return
	 */
	public static ZonedDateTime getZonedDateTimeNowOfDefault(){
		return ZonedDateTime.now(ZoneId.systemDefault());
	}
	
	/**
	 * 获取当前上海时区时间（北京时间）
	 * @param zoneId
	 * @return
	 */
	public static ZonedDateTime getZonedDateTimeNowOfShanghai(){
		return ZonedDateTime.now(ZoneId.of(ZoneIdEnum.CTT.getZoneIdName()));
	}
	
	/**
	 * 获取当前巴黎时区时间
	 * @param zoneId
	 * @return
	 */
	public static ZonedDateTime getZonedDateTimeNowOfParis(){
		return ZonedDateTime.now(ZoneId.of(ZoneIdEnum.ECT.getZoneIdName()));
	}
	
	/**
	 * 获取当前美国东部标准时区（纽约、华盛顿）
	 * @param zoneId
	 * @return
	 */
	public static ZonedDateTime getZonedDateTimeNowOfEST(){
		return ZonedDateTime.now(ZoneId.of(ZoneIdEnum.EST.getZoneIdName()));
	}
	
	/**
	 * 获取当前东京时区时间
	 * @param zoneId
	 * @return
	 */
	public static ZonedDateTime getZonedDateTimeNowOfTokyo(){
		return ZonedDateTime.now(ZoneId.of(ZoneIdEnum.JST.getZoneIdName()));
	}	
	
	// base operation
	
	/**
	 * 日期加操作
	 * @param temporal
	 * @param unit
	 * @param amountToAdd
	 * @return
	 */
	public static Temporal plus(Temporal temporal, TemporalUnit unit, long amountToAdd) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.plus(amountToAdd, unit);
	}
	
	/**
	 * date日期加操作
	 * @param date
	 * @param unit
	 * @param amountToAdd
	 * @return
	 */
	public static Date plus(Date date, TemporalUnit unit, long amountToAdd) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).plus(amountToAdd, unit));
	}
	
	/**
	 * 日期减操作
	 * @param temporal
	 * @param unit
	 * @param amountToSubtract
	 * @return
	 */
	public static Temporal minus(Temporal temporal, TemporalUnit unit, long amountToSubtract) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.minus(amountToSubtract, unit);
	}
	
	/**
	 * date日期减操作
	 * @param date
	 * @param unit
	 * @param amountToSubtract
	 * @return
	 */
	public static Date minus(Date date, TemporalUnit unit, long amountToSubtract) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).minus(amountToSubtract, unit));
	}
	
	/**
	 * 根据field修改属性
	 * @param temporal
	 * @param field
	 * @param newValue
	 * @return
	 */
	public static Temporal with(Temporal temporal, TemporalField field, long newValue) {
		Objects.requireNonNull(temporal, "temporal");
		return temporal.with(field, newValue);
	}
	
	/**
	 * 根据field修改属性
	 * @param date
	 * @param field
	 * @param newValue
	 * @return
	 */
	public static Date with(Date date, TemporalField field, long newValue) {
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDateTime(date).with(field, newValue));
	}
	
	/**
	 * 获取2个日期的总的天时分秒毫秒纳秒
	 * @param startInclusive
	 * @param endExclusive
	 * @return Duration
	 */
	public static Duration durationBetween(Temporal startInclusive, Temporal endExclusive){
		return Duration.between(startInclusive, endExclusive);
	}
	
	/**
	 * 获取2个日期的相差年月日部分属性
	 * @param startDateInclusive
	 * @param endDateExclusive
	 * @return Period
	 */
	public static Period periodBetween(LocalDate startDateInclusive, LocalDate endDateExclusive){
		return Period.between(startDateInclusive, endDateExclusive);
	}
	
	/**
	 * 获取时区当前时间
	 * @param zoneId
	 * @return
	 */
	public static ZonedDateTime getZonedDateTimeNow(String zoneId){
		Objects.requireNonNull(zoneId, "zoneId");
		return ZonedDateTime.now(ZoneId.of(zoneId));
	}
	
	/**
	 * 时区转换计算
	 * @param zonedDateTime
	 * @param zoneId 例如 Asia/Shanghai
	 * @return
	 */
	public static ZonedDateTime transform(ZonedDateTime zonedDateTime, String zoneId){
		Objects.requireNonNull(zoneId, "zoneId");
		return transform(zonedDateTime, ZoneId.of(zoneId));
	}
	
	/**
	 * 时区转换计算
	 * @param zonedDateTime
	 * @param zone
	 * @return
	 */
	public static ZonedDateTime transform(ZonedDateTime zonedDateTime, ZoneId zone){
		Objects.requireNonNull(zonedDateTime, "zonedDateTime");
		Objects.requireNonNull(zone, "zone");
		return zonedDateTime.withZoneSameInstant(zone);
	}
	
	/**
	 * 比较2个时间Date
	 * @param date1
	 * @param date2
	 * @return date1 大于 date2 返回1， date1 小于 date2 返回-1，date1 等于date2 返回0
	 */
	public static int compare(Date date1, Date date2){
		return compare(DateTimeConverterUtil.toLocalDateTime(date1), DateTimeConverterUtil.toLocalDateTime(date2));
	}
	
	/**
	 * 比较2个时间,可用于LocalDateTime,LocalDate,LocalTime,Instant
	 * @param temporal1
	 * @param temporal2
	 * @return temporal1 大于 temporal2 返回1， temporal1 小于 temporal2 返回-1，temporal1 等于temporal2 返回0
	 */
	public static int compare(Temporal temporal1, Temporal temporal2){
		Objects.requireNonNull(temporal1, "temporal1");
		Objects.requireNonNull(temporal2, "temporal2");
		if(temporal1 instanceof LocalDateTime && temporal2 instanceof LocalDateTime){
			LocalDateTime localDateTimeA = (LocalDateTime)temporal1;
			LocalDateTime localDateTimeB = (LocalDateTime)temporal2;
			return localDateTimeA.compareTo(localDateTimeB);
		}else if(temporal1 instanceof LocalDate && temporal2 instanceof LocalDate){
			LocalDate localDateA = (LocalDate)temporal1;
			LocalDate localDateB = (LocalDate)temporal2;
			return localDateA.compareTo(localDateB);
		}else if(temporal1 instanceof LocalTime && temporal2 instanceof LocalTime){
			LocalTime localTimeA = (LocalTime)temporal1;
			LocalTime localTimeB = (LocalTime)temporal2;
			return localTimeA.compareTo(localTimeB);
		}else if(temporal1 instanceof Instant && temporal2 instanceof Instant){
			Instant instantA = (Instant)temporal1;
			Instant instantB = (Instant)temporal2;
			return instantA.compareTo(instantB);
		}
		
		throw new DateTimeException("Unsupported Temporal, must be LocalDateTime,LocalDate,LocalTime,Instant");
	}
	
	//获取精确起始时间 00:00:00 - 23:59:59
	
	/**
	 * 一天开始时间 00:00:00
	 * @return
	 */
	public static LocalTime startTimeOfDay(){
		return LocalTime.of(0, 0, 0);
	}
	
	/**
	 * 一天开始时间 23:59:59
	 * @return
	 */
	public static LocalTime endTimeOfDay(){
		return LocalTime.of(23, 59, 59);
	}
	
	/**
	 * 昨天起始时间 即：昨天日期+00:00:00
	 * @return
	 */
	public static Date startTimeOfYesterday(){
		return DateTimeConverterUtil.toDate(LocalDate.now().minusDays(1).atTime(startTimeOfDay()));
	}
	
	/**
	 * 昨天结束时间即：昨天日期+23:59:59
	 * @return
	 */
	public static Date endTimeOfYesterday(){
		return DateTimeConverterUtil.toDate(LocalDate.now().minusDays(1).atTime(endTimeOfDay()));
	}
	
	/**
	 * 今天起始时间 即：今天日期+00:00:00
	 * @return
	 */
	public static Date startTimeOfToday(){
		return DateTimeConverterUtil.toDate(LocalDate.now().atTime(startTimeOfDay()));
	}
	
	/**
	 * 今天结束时间即：今天日期+23:59:59
	 * @return
	 */
	public static Date endTimeOfToday(){
		return DateTimeConverterUtil.toDate(LocalDate.now().atTime(endTimeOfDay()));
	}
	
	/**
	 * 上个月第一天起始时间 即：上个月第一天日期+00:00:00
	 * @return
	 */
	public static Date startTimeOfLastMonth(){
		return DateTimeConverterUtil.toDate(firstDayOfMonth(LocalDate.now().minusMonths(1)).atTime(startTimeOfDay()));
	}
	
	/**
	 * 上个月最后一天结束时间 即：上个月最后一天日期+23:59:59
	 * @return
	 */
	public static Date endTimeOfLastMonth(){
		return DateTimeConverterUtil.toDate(lastDayOfMonth(LocalDate.now().minusMonths(1)).atTime(endTimeOfDay()));
	}
	
	/**
	 * 当月第一天起始时间 即：当月第一天日期+00:00:00
	 * @return
	 */
	public static Date startTimeOfMonth(){
		return DateTimeConverterUtil.toDate(firstDayOfMonth(LocalDate.now()).atTime(startTimeOfDay()));
	}
	
	/**
	 * 当月最后一天结束时间即：当月最后一天日期+23:59:59
	 * @return
	 */
	public static Date endTimeOfMonth(){
		return DateTimeConverterUtil.toDate(lastDayOfMonth(LocalDate.now()).atTime(endTimeOfDay()));
	}
	
	/**
	 * 获date起始时间
	 * @param date
	 * @return
	 */
	public static Date startTimeOfDate(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).atTime(startTimeOfDay()));
	}
	
	/**
	 * 获取date结束时间
	 * @param date
	 * @return
	 */
	public static Date endTimeOfDate(Date date){
		return DateTimeConverterUtil.toDate(DateTimeConverterUtil.toLocalDate(date).atTime(endTimeOfDay()));
	}

	/**
	 * 获取指定年月的第一天起始时间
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date startTimeOfSpecialMonth(int year, int month){
		return DateTimeConverterUtil.toDate(LocalDate.of(year, month, 1).atTime(startTimeOfDay()));
	}
	
	/**
	 * 获取指定年月的最后一天结束时间
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date endTimeOfSpecialMonth(int year, int month){
		return DateTimeConverterUtil.toDate(lastDayOfMonth(LocalDate.of(year, month, 1)).atTime(endTimeOfDay()));
	}
	
	/**
	 * 获取指定日期的起始时间
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @return
	 */
	public static Date startTimeOfDate(int year, int month, int dayOfMonth){
		return DateTimeConverterUtil.toDate(LocalDate.of(year, month, dayOfMonth).atTime(startTimeOfDay()));
	}
	
	/**
	 * 获取指定日期的结束时间
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @return
	 */
	public static Date endTimeOfDate(int year, int month, int dayOfMonth){
		return DateTimeConverterUtil.toDate(LocalDate.of(year, month, dayOfMonth).atTime(endTimeOfDay()));
	}
	
	// 使用MonthDay对比时间的月日，用于生日，节日等周期性的日期比较判断。
	
	/**
	 * 相同月日比较判断，用于生日，节日等周期性的日期比较判断。
	 * @param localDate1
	 * @param monthDay
	 * @return
	 */
	public static boolean isSameMonthDay(LocalDate localDate1, MonthDay monthDay){
		Objects.requireNonNull(localDate1, "localDate1");
		Objects.requireNonNull(monthDay, "monthDay");
		return MonthDay.of(localDate1.getMonthValue(), localDate1.getDayOfMonth()).equals(monthDay);
	}
	
	/**
	 * 相同月日比较判断，用于生日，节日等周期性的日期比较判断。
	 * @param localDate1
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static boolean isSameMonthDay(LocalDate localDate1, String monthDayStr){
		Objects.requireNonNull(monthDayStr, "monthDayStr");
		return isSameMonthDay(localDate1, MonthDay.parse(MONTHDAY_FORMAT_PRE + monthDayStr));
	}
	
	/**
	 * 相同月日比较判断，用于生日，节日等周期性的日期比较判断。
	 * @param localDate1
	 * @param localDate2
	 * @return
	 */
	public static boolean isSameMonthDay(LocalDate localDate1, LocalDate localDate2){
		Objects.requireNonNull(localDate2, "localDate2");
		return isSameMonthDay(localDate1, MonthDay.of(localDate2.getMonthValue(), localDate2.getDayOfMonth()));
	}
	
	/**
	 * 相同月日比较判断，用于生日，节日等周期性的日期比较判断。
	 * @param date
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static boolean isSameMonthDay(Date date, String monthDayStr){
		return isSameMonthDay(DateTimeConverterUtil.toLocalDate(date), monthDayStr);
	}
	
	/**
	 * 相同月日比较判断，用于生日，节日等周期性的日期比较判断。
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameMonthDay(Date date1, Date date2){
		Objects.requireNonNull(date1, "date1");
		Objects.requireNonNull(date2, "date2");
		return isSameMonthDay(DateTimeConverterUtil.toLocalDate(date1), DateTimeConverterUtil.toLocalDate(date2));
	}
	
	/**
	 * 相同月日比较判断，与当前日期对比，用于生日，节日等周期性的日期比较判断
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static boolean isSameMonthDayOfNow(String monthDayStr){
		return isSameMonthDay(LocalDate.now(), monthDayStr);
	}
	
	/**
	 * 下个固定月日相差天数，用于生日，节日等周期性的日期推算
	 * @param localDate1
	 * @param month
	 * @param dayOfMonth
	 * @return
	 */
	public static long betweenNextSameMonthDay(LocalDate localDate1, int month, int dayOfMonth) {
		Objects.requireNonNull(localDate1, "localDate1");
		MonthDay monthDay1 = MonthDay.of(localDate1.getMonthValue(), localDate1.getDayOfMonth());
		MonthDay monthDay2 = MonthDay.of(month, dayOfMonth);
		
		// localDate1 月日 小于 month dayOfMonth
		if (monthDay1.compareTo(monthDay2) == -1) {
			return betweenTotalDays(localDate1.atStartOfDay(),
					localDate1.withMonth(month).withDayOfMonth(dayOfMonth).atStartOfDay());
		} else {
			// 闰年2月29
			MonthDay leapMonthDay = MonthDay.of(2, 29);
			if (leapMonthDay.equals(monthDay2)) {
				LocalDate nextLeapYear = nextLeapYear(localDate1);
				return betweenTotalDays(localDate1.atStartOfDay(),
						nextLeapYear.withMonth(month).withDayOfMonth(dayOfMonth).atStartOfDay());
			} else {
				LocalDate next = localDate1.plusYears(1);
				return betweenTotalDays(localDate1.atStartOfDay(),
						next.withMonth(month).withDayOfMonth(dayOfMonth).atStartOfDay());
			}
		}
	}
	
	/**
	 * 下个固定月日相差天数，用于生日，节日等周期性的日期推算
	 * @param localDate
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static long betweenNextSameMonthDay(LocalDate localDate, String monthDayStr) {
		Objects.requireNonNull(monthDayStr, "monthDayStr");
		MonthDay monthDay2 = MonthDay.parse(MONTHDAY_FORMAT_PRE + monthDayStr);
		return betweenNextSameMonthDay(localDate, monthDay2.getMonthValue(), monthDay2.getDayOfMonth());
	}
	
	/**
	 * 下个固定月日相差天数，用于生日，节日等周期性的日期推算
	 * @param date
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static long betweenNextSameMonthDay(Date date, String monthDayStr) {
		Objects.requireNonNull(monthDayStr, "monthDayStr");
		MonthDay monthDay2 = MonthDay.parse(MONTHDAY_FORMAT_PRE + monthDayStr);
		return betweenNextSameMonthDay(DateTimeConverterUtil.toLocalDate(date), monthDay2.getMonthValue(),
				monthDay2.getDayOfMonth());
	}
	
	/**
	 * 下个固定月日相差天数，与当前日期对比，用于生日，节日等周期性的日期推算
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static long betweenNextSameMonthDayOfNow(String monthDayStr) {
		Objects.requireNonNull(monthDayStr, "monthDayStr");
		MonthDay monthDay2 = MonthDay.parse(MONTHDAY_FORMAT_PRE + monthDayStr);
		return betweenNextSameMonthDay(LocalDate.now(), monthDay2.getMonthValue(),
				monthDay2.getDayOfMonth());
	}
	
	/**
	 * 下个固定月日相差日期，用于生日，节日等周期性的日期推算
	 * @param localDate
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static LocalDate nextSameMonthDay(LocalDate localDate, String monthDayStr){
		return localDate.plusDays(betweenNextSameMonthDay(localDate, monthDayStr));
	}
	
	/**
	 * 下个固定月日相差日期，用于生日，节日等周期性的日期推算
	 * @param date
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static Date nextSameMonthDay(Date date, String monthDayStr){
		return DateTimeConverterUtil.toDate(nextSameMonthDay(DateTimeConverterUtil.toLocalDate(date), monthDayStr));
	}
	
	/**
	 * 下个固定月日相差日期，与当前日期对比，用于生日，节日等周期性的日期推算
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static Date nextSameMonthDayOfNow(String monthDayStr){
		return nextSameMonthDay(new Date(), monthDayStr);
	}
	
	/**
	 * 根据日期查询星座中文名称
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static String getConstellationNameCn(String monthDayStr){
		return ConstellationNameEnum.getNameCnByMonthDay(monthDayStr);
	}
	
	/**
	 * 根据日期查询星座中文名称
	 * @param date
	 * @return
	 */
	public static String getConstellationNameCn(Date date){
		String monthDayStr = DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_FMT);
		return ConstellationNameEnum.getNameCnByMonthDay(monthDayStr);
	}
	
	/**
	 * 根据日期查询星座英文名称
	 * @param monthDayStr MM-dd格式
	 * @return
	 */
	public static String getConstellationNameEn(String monthDayStr){
		return ConstellationNameEnum.getNameEnByMonthDay(monthDayStr);
	}
	
	/**
	 * 获取指定区间的时间列表，包含起始
	 * @param startInclusive
	 * @param endInclusive
	 * @return
	 */
	public static List<LocalDateTime> getLocalDateTimeList(LocalDateTime startInclusive, LocalDateTime endInclusive){
		Objects.requireNonNull(startInclusive, "startInclusive");
		Objects.requireNonNull(endInclusive, "endInclusive");
		if(startInclusive.isAfter(endInclusive)){
			throw new DateTimeException("startInclusive must before or equal endInclusive!");
		}
		List<LocalDateTime> localDateTimeList = new ArrayList<LocalDateTime>();
		long days = betweenTotalDays(startInclusive, endInclusive)+1;
		for(long i=0; i<days; i++){
			localDateTimeList.add(startInclusive.plusDays(i));
		}
		return localDateTimeList;
	}
	
	/**
	 * 获取指定区间的时间列表，包含起始
	 * @param startInclusive
	 * @param endInclusive
	 * @return
	 */
	public static List<LocalDate> getLocalDateList(LocalDate startInclusive, LocalDate endInclusive){
		return getLocalDateTimeList(DateTimeConverterUtil.toLocalDateTime(startInclusive),
				DateTimeConverterUtil.toLocalDateTime(endInclusive)).stream()
						.map(localDateTime -> localDateTime.toLocalDate()).collect(Collectors.toList());
	}
	
	/**
	 * 获取指定区间的时间列表，包含起始
	 * @param startInclusive
	 * @param endInclusive
	 * @return
	 */
	public static List<Date> getDateList(Date startInclusive, Date endInclusive){
		return getLocalDateTimeList(DateTimeConverterUtil.toLocalDateTime(startInclusive),
				DateTimeConverterUtil.toLocalDateTime(endInclusive)).stream()
						.map(localDateTime -> DateTimeConverterUtil.toDate(localDateTime)).collect(Collectors.toList());
	}
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param YearMonth
	 * @return
	 */
	public static List<LocalDate> getLocalDateList(YearMonth yearMonth){
		Objects.requireNonNull(yearMonth, "yearMonth");
		List<LocalDate> localDateList = new ArrayList<LocalDate>();
		long days = yearMonth.lengthOfMonth();
		LocalDate localDate = DateTimeConverterUtil.toLocalDateStartOfMonth(yearMonth);
		for(long i=0; i<days; i++){
			localDateList.add(plusDays(localDate, i));
		}
		return localDateList;
	}
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param yearMonthStr yyyy-MM
	 * @return
	 */
	public static List<LocalDate> getLocalDateList(String yearMonthStr){
		Objects.requireNonNull(yearMonthStr, "yearMonthStr");
		YearMonth yearMonth = YearMonth.parse(yearMonthStr);
		return getLocalDateList(yearMonth);
	}
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<LocalDate> getLocalDateList(int year, int month){
		YearMonth yearMonth = YearMonth.of(year, month);
		return getLocalDateList(yearMonth);
	}
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param yearMonth
	 * @return
	 */
	public static List<LocalDateTime> getLocalDateTimeList(YearMonth yearMonth){
		return getLocalDateList(yearMonth).stream()
				.map(localDate -> DateTimeConverterUtil.toLocalDateTime(localDate)).collect(Collectors.toList());
	}	
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param yearMonthStr yyyy-MM
	 * @return
	 */
	public static List<LocalDateTime> getLocalDateTimeList(String yearMonthStr){
		return getLocalDateList(yearMonthStr).stream()
				.map(localDate -> DateTimeConverterUtil.toLocalDateTime(localDate)).collect(Collectors.toList());
	}
	
	/**
	 *  获取指定年月的所有日期列表
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<LocalDateTime> getLocalDateTimeList(int year, int month){
		return getLocalDateList(YearMonth.of(year, month)).stream()
				.map(localDate -> DateTimeConverterUtil.toLocalDateTime(localDate)).collect(Collectors.toList());
	}
	
	/**
	 * 获取指定年月的所有日期列表
	 * @param yearMonthStr yyyy-MM
	 * @return
	 */
	public static List<Date> getDateList(String yearMonthStr){
		return getLocalDateList(yearMonthStr).stream().map(localDate -> DateTimeConverterUtil.toDate(localDate))
				.collect(Collectors.toList());
	}
	
	/**
	 * 获取指定年月的所有日期列表
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<Date> getDateList(int year, int month){
		return getLocalDateList(YearMonth.of(year, month)).stream().map(localDate -> DateTimeConverterUtil.toDate(localDate))
				.collect(Collectors.toList());
	}	
	
	/**
	 * 判断是否过期，（输入年月小于当前年月）
	 * @param yearMonth
	 * @return
	 */
	public static boolean isExpiry(YearMonth yearMonth){
		Objects.requireNonNull(yearMonth, "yearMonth");
		if(yearMonth.isBefore(YearMonth.now())){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否过期，（输入年月小于当前年月）
	 * @param yearMonthStr yyyy-MM
	 * @return
	 */
	public static boolean isExpiry(String yearMonthStr){
		Objects.requireNonNull(yearMonthStr, "yearMonthStr");
		YearMonth yearMonth = YearMonth.parse(yearMonthStr);
		return isExpiry(yearMonth);
	}
	
}
