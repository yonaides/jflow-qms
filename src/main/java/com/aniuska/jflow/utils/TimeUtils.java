/**
 * 
 */
package com.aniuska.jflow.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author hventura@citrus.com.do
 */
public class TimeUtils {

    public static BigDecimal getDiffTimeMinutes(Date firstDate, Date lastDate) {
        //Milisecons
        long diff = lastDate.getTime() - firstDate.getTime();

        //milisecons / ( 60 secons * milisecons)
        return BigDecimal.valueOf((double) diff / (60 * 1000));
    }

}
