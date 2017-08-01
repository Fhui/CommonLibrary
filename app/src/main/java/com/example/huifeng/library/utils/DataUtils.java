package com.example.huifeng.library.utils;

import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by HIMan on 16/8/25.
 */
public class DataUtils {

    public static String getData() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sim.format(cal.getTime());
        return date;
    }

    /**
     * 限制一位小数
     * 跟编辑框的相关性非常大，所以参数传递两个进来。
     */
    public static void Limit1Decimal(EditText editText, CharSequence charSequence) {
        if (charSequence.toString().contains(".")) {
            if (charSequence.length() - 1 - charSequence.toString().indexOf(".") > 1) {
                charSequence = charSequence.toString().subSequence(0,
                        charSequence.toString().indexOf(".") + 2);
                editText.setText(charSequence.toString());
                editText.setSelection(charSequence.length());
            }
        }
        if (charSequence.toString().trim().substring(0).equals(".")) {
            charSequence = "0" + charSequence;
            editText.setText(charSequence);
            editText.setSelection(2);
        }

        if (charSequence.toString().startsWith("0")
                && charSequence.toString().trim().length() > 1) {
            if (!charSequence.toString().substring(1, 2).equals(".")) {
                editText.setText(charSequence.subSequence(0, 1));
                editText.setSelection(1);
                return;
            }
        }
    }

    /**
     * 限制两位小数
     * 跟编辑框的相关性非常大，所以参数传递两个进来。
     */
    public static void Limit2Decimal(EditText editText, CharSequence charSequence) {
        //包含小数点
        if (charSequence.toString().contains(".")) {
            if (charSequence.length() - 1 - charSequence.toString().indexOf(".") > 2) {
                charSequence = charSequence.toString().subSequence(0,
                        charSequence.toString().indexOf(".") + 3);
                editText.setText(charSequence.toString());
                editText.setSelection(charSequence.length());
            }
        }
        if (charSequence.toString().trim().substring(0).equals(".")) {
            charSequence = "0" + charSequence;
            editText.setText(charSequence);
            editText.setSelection(2);
        }
    }
    //个位为零
//        if (charSequence.toString().startsWith("0")
//                && charSequence.toString().trim().length() > 2) {
//            if (!charSequence.toString().substring(1, 3).equals(".")) {
//                editText.setText(charSequence.subSequence(0, 2));
//                editText.setSelection(2);
//                return;
//            }
//        }


    public static String dataFormat(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = sdf.parse(dateStr);
            return df1.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
