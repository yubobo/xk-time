package com.xkzhangsan.time.test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.junit.Test;

import com.xkzhangsan.time.formatter.DateTimeFormatterUtil;

public class FormatterTest {
	
	@Test
	public void simpleFormatTest(){
		Date d = DateTimeFormatterUtil.parseDateTimeStrToDate("2019-12-01 17:03:03");
		System.out.println(DateTimeFormatterUtil.formatToDateTimeStr(d));//2019-12-01 17:03:03
		System.out.println(DateTimeFormatterUtil.formatToDateStr(d));//2019-12-01
		System.out.println(DateTimeFormatterUtil.formatToTimeStr(d));//17:03:03
	}
	
	@Test
	public void dateFormatTest(){
		Date date = new Date();
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HH_MM_SS_FMT));//1
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.HHMMSS_FMT));//2
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_CN_FMT));//3
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_CN_FMT));//4
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_FMT));//5

		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.MM_DD_HH_MM_SS_CN_FMT));//6
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_FMT));//7
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_CN_FMT));//8
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_CN_FMT));//9
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_EN_FMT));//10
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_FMT));//11
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_FMT));//12
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));//13
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));//14
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT));//15
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_DD_POINT_FMT));//16
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_EN_FMT));//17
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYY_MM_FMT));//18
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMM_FMT));//19
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDD_FMT));//20
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMM_FMT));//21
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMMSS_FMT));//22
		System.out.println(DateTimeFormatterUtil.format(date, DateTimeFormatterUtil.YYYYMMDDHHMMSSSSS_FMT));//23
	}
	
	@Test
	public void localDateTimeFormatTest(){
		LocalDateTime lss = LocalDateTime.now();
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.HH_MM_SS_FMT));//1
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.HHMMSS_FMT));//2
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.MM_DD_CN_FMT));//3
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.MM_DD_HH_MM_CN_FMT));//4
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.MM_DD_HH_MM_FMT));//5
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.MM_DD_HH_MM_SS_CN_FMT));//6
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_FMT));//7
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_CN_FMT));//8
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_CN_FMT));//9
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_EN_FMT));//10
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_FMT));//11
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_FMT));//12
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));//13
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));//14
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT));//15
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_DD_POINT_FMT));//16
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_EN_FMT));//17
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYY_MM_FMT));//18
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYYMM_FMT));//19
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYYMMDD_FMT));//20
		
		System.out.println("=============================");
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYYMMDDHHMM_FMT));//21
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYYMMDDHHMMSS_FMT));//22
		System.out.println(DateTimeFormatterUtil.format(lss, DateTimeFormatterUtil.YYYYMMDDHHMMSSSSS_FMT));//23
	}
	
	@Test
	public void parseToDateTest(){
		System.out.println(DateTimeFormatterUtil.parseToDate("2019年12月01日 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));
		System.out.println(DateTimeFormatterUtil.parseToDate("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));
	}
	
	@Test
	public void parseToLocalDateTimeTest(){
		System.out.println(DateTimeFormatterUtil.parseToLocalDateTime("2019年12月01日 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));
		System.out.println(DateTimeFormatterUtil.parseToLocalDateTime("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));
	}
	
	@Test
	public void parseToInstantTest(){
		System.out.println(DateTimeFormatterUtil.parseToInstant("2019年12月01日 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_CN_FMT));
		System.out.println(DateTimeFormatterUtil.parseToInstant("2019-12-01 17:03:03", DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_FMT));
	}
	
	/**
	 * 时区时间格式化和ISO常用格式化
	 * YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ"
	 */
	@Test
	public void zonedDateTimeFormatTest(){
		//默认为巴黎时区
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
		//2020-02-18T22:37:55+0800
		System.out.println(DateTimeFormatterUtil.format(zonedDateTime, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_Z_FMT));
		
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.ISO_DATE_FMT));
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.ISO_DATE_TIME_FMT));
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.ISO_INSTANT_FMT));
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.ISO_LOCAL_DATE_FMT));
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.ISO_LOCAL_DATE_TIME_FMT));
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.ISO_LOCAL_TIME_FMT));
		
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.ISO_TIME_FMT));
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.ISO_WEEK_DATE_FMT));
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.ISO_ZONED_DATE_TIME_FMT));
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.BASIC_ISO_DATE_FMT));
		
		System.out.println("=========其他格式化重新设置时区，用于非系统默认时区时间格式化=========");
		System.out.println(zonedDateTime.format(DateTimeFormatterUtil.YYYY_MM_DD_HH_MM_SS_SSS_FMT.withZone(ZoneId.of("Europe/Paris"))));
	}
	
	/**
	 * 时区时间解析
	 * YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ssZ"
	 */
	@Test
	public void parseToZonedDateTimeTest(){
		String text = "2020-02-18T22:37:55+0800";
		ZonedDateTime zonedDateTime = DateTimeFormatterUtil.parseToZonedDateTime(text, DateTimeFormatterUtil.YYYY_MM_DD_T_HH_MM_SS_Z_FMT);
		System.out.println(zonedDateTime);
		
		String text2 = "2020-02-19T12:30:25.121+08:00[Asia/Shanghai]";
		ZonedDateTime zonedDateTime2 = DateTimeFormatterUtil.parseToZonedDateTime(text2, DateTimeFormatterUtil.ISO_ZONED_DATE_TIME_FMT);
		System.out.println(zonedDateTime2);
		
		ZonedDateTime zonedDateTime3 = ZonedDateTime.parse(text2);
		System.out.println(zonedDateTime3);
	}
}
