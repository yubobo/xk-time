package com.xkzhangsan.time.calendar;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.xkzhangsan.time.calculator.DateTimeCalculatorUtil;
import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;
import com.xkzhangsan.time.utils.CollectionUtil;

/**
 * 日历工具类 
 * 包括：
 *  1.生成指定时间的日历方法，generateCalendar* 比如generateCalendar(int year, int
 * month) 生成指定年月的日历
 *  2.生成指定时间的日历，包含农历和所有节假日信息方法，generateCalendarWithHoliday*， 比如generateCalendarWithHoliday(int year, int month, Map<String, String> localHolidayMap,
 *			Map<String, String> chineseHolidayMap) 生成指定年月的日历，包含农历和所有节假日信息
 * @ClassName: CalendarUtil
 * @Description: CalendarUtil
 * @author xkzhangsan
 * @date 2020年03月18日
 */
public class CalendarUtil {

	private CalendarUtil() {
	}

	/**
	 * 生成指定年月的日历
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static CalendarWrapper generateCalendar(int year, int month) {
		return generateCalendar(year, month, false, false, null, null);
	}

	/**
	 * 生成指定年月的日历， 包含农历信息
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static CalendarWrapper generateCalendarWithLunar(int year, int month) {
		return generateCalendar(year, month, true, false, null, null);
	}

	/**
	 * 生成指定年月的日历，包含公历节假日信息
	 * 
	 * @param year
	 * @param month
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 * @return
	 */
	public static CalendarWrapper generateCalendarWithLocalHoliday(int year, int month,
			Map<String, String> localHolidayMap) {
		return generateCalendar(year, month, false, true, localHolidayMap, null);
	}

	/**
	 * 生成指定年月的日历，包含农历和所有节假日信息
	 * 
	 * @param year
	 * @param month
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 * @param chineseHolidayMap
	 *            自定义农历节日信息，特殊节日如除夕 用CHUXI表示，为null时，使用默认数据 ChineseHolidayEnum
	 * @return
	 */
	public static CalendarWrapper generateCalendarWithHoliday(int year, int month, Map<String, String> localHolidayMap,
			Map<String, String> chineseHolidayMap) {
		return generateCalendar(year, month, true, true, localHolidayMap, chineseHolidayMap);
	}

	/**
	 * 生成指定年月的日历，包含农历和所有节假日信息
	 * 
	 * @param year
	 * @param month
	 * @param includeLunarDate
	 * @param includeHoliday
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 * @param chineseHolidayMap
	 *            自定义农历节日信息，特殊节日如除夕 用CHUXI表示，为null时，使用默认数据 ChineseHolidayEnum
	 * @return
	 */
	private static CalendarWrapper generateCalendar(int year, int month, boolean includeLunarDate,
			boolean includeHoliday, Map<String, String> localHolidayMap, Map<String, String> chineseHolidayMap) {
		YearMonth yearMonth = YearMonth.of(year, month);
		CalendarWrapper calendarWrapper = new CalendarWrapper();
		Map<String, DayWrapper> dayMap = new ConcurrentHashMap<String, DayWrapper>();
		List<DayWrapper> dayList = new ArrayList<DayWrapper>();
		List<LocalDateTime> localDateTimeList = DateTimeCalculatorUtil.getLocalDateTimeList(YearMonth.of(year, month));
		if (CollectionUtil.isEmpty(localDateTimeList)) {
			return calendarWrapper;
		}
		List<DayWrapper> dayWrapperList = new ArrayList<>();
		localDateTimeList.stream().forEach(localDateTime -> {
			DayWrapper dayWrapper = new DayWrapper(localDateTime, includeLunarDate, includeHoliday, localHolidayMap,
					chineseHolidayMap);
			dayWrapperList.add(dayWrapper);
			dayMap.put(DateTimeFormatterUtil.formatToDateStr(localDateTime), dayWrapper);
			dayList.add(dayWrapper);
		});

		MonthWrapper monthWrapper = new MonthWrapper(month, dayWrapperList, yearMonth.lengthOfMonth());
		List<MonthWrapper> monthWrapperList = new ArrayList<>();
		monthWrapperList.add(monthWrapper);
		YearWrapper yearWrapper = new YearWrapper(year, monthWrapperList);

		List<YearWrapper> yearWrapperList = new ArrayList<>();
		yearWrapperList.add(yearWrapper);

		calendarWrapper = new CalendarWrapper(yearWrapperList, dayMap, dayList);
		return calendarWrapper;
	}

	/**
	 * 生成指定年月的日历
	 * 
	 * @param year
	 * @return
	 */
	public static CalendarWrapper generateCalendar(int year) {
		return generateCalendar(year, false, false, null, null);
	}

	/**
	 * 生成指定年月的日历， 包含农历信息
	 * 
	 * @param year
	 * @return
	 */
	public static CalendarWrapper generateCalendarWithLunar(int year) {
		return generateCalendar(year, true, false, null, null);
	}

	/**
	 * 生成指定年月的日历，包含公历节假日信息
	 * 
	 * @param year
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 * @return
	 */
	public static CalendarWrapper generateCalendarWithLocalHoliday(int year, Map<String, String> localHolidayMap) {
		return generateCalendar(year, false, true, localHolidayMap, null);
	}

	/**
	 * 生成指定年月的日历，包含农历和所有节假日信息
	 * 
	 * @param year
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 * @param chineseHolidayMap
	 *            自定义农历节日信息，特殊节日如除夕 用CHUXI表示，为null时，使用默认数据 ChineseHolidayEnum
	 * @return
	 */
	public static CalendarWrapper generateCalendarWithHoliday(int year, Map<String, String> localHolidayMap,
			Map<String, String> chineseHolidayMap) {
		return generateCalendar(year, true, true, localHolidayMap, chineseHolidayMap);
	}

	/**
	 * 生成指定年月的日历，包含农历和所有节假日信息
	 * 
	 * @param year
	 * @param includeLunarDate
	 * @param includeHoliday
	 * @param localHolidayMap
	 *            自定义公历节日数据，特殊节日如，"母亲节", "5-W-2-7"
	 *            5表示5月，W表示星期，2表示第二个星期，7表示星期的第7天，为null时，使用默认数据 LocalHolidayEnum
	 * @param chineseHolidayMap
	 *            自定义农历节日信息，特殊节日如除夕 用CHUXI表示，为null时，使用默认数据 ChineseHolidayEnum
	 * @return
	 */
	private static CalendarWrapper generateCalendar(int year, boolean includeLunarDate, boolean includeHoliday,
			Map<String, String> localHolidayMap, Map<String, String> chineseHolidayMap) {
		CalendarWrapper calendarWrapper = new CalendarWrapper();
		Map<String, DayWrapper> dayMap = new ConcurrentHashMap<String, DayWrapper>();
		List<DayWrapper> dayList = new ArrayList<DayWrapper>();
		List<MonthWrapper> monthWrapperList = new ArrayList<>();
		for (int i = 1; i <= 12; i++) {
			YearMonth yearMonth = YearMonth.of(year, i);
			List<LocalDateTime> localDateTimeList = DateTimeCalculatorUtil.getLocalDateTimeList(YearMonth.of(year, i));
			if (CollectionUtil.isEmpty(localDateTimeList)) {
				continue;
			}
			List<DayWrapper> dayWrapperList = new ArrayList<>();
			localDateTimeList.stream().forEach(localDateTime -> {
				DayWrapper dayWrapper = new DayWrapper(localDateTime, includeLunarDate, includeHoliday, localHolidayMap,
						chineseHolidayMap);
				dayWrapperList.add(dayWrapper);
				dayMap.put(DateTimeFormatterUtil.formatToDateStr(localDateTime), dayWrapper);
				dayList.add(dayWrapper);
			});

			MonthWrapper monthWrapper = new MonthWrapper(i, dayWrapperList, yearMonth.lengthOfMonth());
			monthWrapperList.add(monthWrapper);
		}

		List<YearWrapper> yearWrapperList = new ArrayList<>();
		YearWrapper yearWrapper = new YearWrapper(year, monthWrapperList);
		yearWrapperList.add(yearWrapper);
		calendarWrapper = new CalendarWrapper(yearWrapperList, dayMap, dayList);
		return calendarWrapper;
	}
}
