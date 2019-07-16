package br.com.softplan.cadastropf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * Converte String (yyyy-MM-dd) para Date
     * @param strDate
     * @return
     */
    public static Date stringToDateOnly(String strDate)  {
        try {
            if(strDate == null)
                return null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            date = new Date(sdf.parse(strDate).getTime());
            return date;
        } catch (ParseException e) {
            return null;
        }
    }
}
