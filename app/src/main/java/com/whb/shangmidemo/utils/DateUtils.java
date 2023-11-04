package com.whb.shangmidemo.utils;

import android.icu.util.Calendar;
import android.os.Build;

import com.whb.shangmidemo.entity.DateDayPickerBean;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DateUtils {

    /**
     * 根据传入的年份和月份获取当月的天数
     */
    public static int getDaysInMonth(int year, int month) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            YearMonth yearMonth = YearMonth.of(year, month);
            return yearMonth.lengthOfMonth();
        }

        return 0;
    }

    /**
     * 根据传入的月份获取当月的天数
     */
    public static List<Integer> generateDayList(int daysInMonth) {
        return IntStream.rangeClosed(1, daysInMonth)
                .boxed()
                .collect(Collectors.toList());
    }



    /**
     * 根据传入的月份获取当月的天数
     * 并添加空白项
     */
    public static List<DateDayPickerBean> generateDayList(int daysInMonth, int empty) {
        return Stream.concat(
                        IntStream.range(0, empty)
                                .mapToObj(i -> {
                                    DateDayPickerBean bean = new DateDayPickerBean();
                                    bean.setDataDay(0);
                                    return bean;
                                }),
                        IntStream.range(1, daysInMonth + 1)
                                .mapToObj(day -> {
                                    DateDayPickerBean bean = new DateDayPickerBean();
                                    bean.setDataDay(day);
                                    return bean;
                                })
                )
                .collect(Collectors.toList());
    }


    /**
     * 根据传入的值获取历史的年月
     */
    public static List<YearMonth> getLastNMonths(int n) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            YearMonth currentYearMonth = YearMonth.now();
            return IntStream.range(0, n)
                    .mapToObj(currentYearMonth::minusMonths)
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();
    }

    public static List<String> getWeeks() {
        List<String> week = new ArrayList<>();
        week.add("周一");
        week.add("周二");
        week.add("周三");
        week.add("周四");
        week.add("周五");
        week.add("周六");
        week.add("周日");
        return week;
    }

    /**
     * 获取当前月份第一天是星期几
     * 通过结果判断该添加几个空白项
     * */
    public static int getEmptyDaysInMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); // 月份从0开始，所以减去1

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK); // 获取星期几，1 表示星期日，2 表示星期一，依此类推
        return (dayOfWeek + 5) % 7; // 使用模运算来计算空白天数
    }

}
