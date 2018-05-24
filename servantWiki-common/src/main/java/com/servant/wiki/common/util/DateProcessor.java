/**
 * ClassName: DateProcessor
 * CopyRight: TalkWeb
 * Date: 12-11-24
 * Version: 1.0
 */
package com.servant.wiki.common.util;

import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * Description : JSON解析时的时间处理器
 * User : zhanglingzhi
 */
public class DateProcessor implements JsonValueProcessor {
    private String datePatten;  //时间格式

    public DateProcessor(String datePatten) {
        this.datePatten = datePatten;
    }

    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        String[] returnValue;
        if (o instanceof Date[]) {
            Date[] dates = (Date[]) o;
            returnValue = new String[dates.length];
            for (int i = 0; i < dates.length; i++) {
                returnValue[i] = DateUtils.format(dates[i], datePatten);
            }

            return returnValue;
        } else if (o instanceof java.sql.Date[]) {
            java.sql.Date[] dates = (java.sql.Date[]) o;
            returnValue = new String[dates.length];
            for (int i = 0; i < dates.length; i++) {
                Date date = new Date(dates[i].getTime());
                returnValue[i] = DateUtils.format(date, datePatten);
            }
        }

        return null;
    }

    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        if (o instanceof Date) {
            Date date = (Date) o;
            return DateUtils.format(date, datePatten);
        } else if (o instanceof java.sql.Date) {
            Date date = new Date(((java.sql.Date) o).getTime());
            return DateUtils.format(date, datePatten);
        }

        return null;
    }
}
