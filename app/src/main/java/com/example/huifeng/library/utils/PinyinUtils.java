package com.example.huifeng.library.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by HIMan on 2016/10/18.
 */

public class PinyinUtils {

    public static String getPinyin(String text) {
        StringBuilder sb = new StringBuilder();
        //输出格式
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        //去掉音调
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //转化成大写
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        if (text != null) {

            char[] chars = text.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                //如果c是空格，直接跳过
                if (Character.isWhitespace(c)) {
                    continue;
                }
                //如果是键盘上直接打出来的字符 A-Z 0-9
                if (-128 < c && c <= 127) {
                    sb.append(c);
                } else {
                    try {
                        if (isChinese(c)) {
                            String[] strings = PinyinHelper.toHanyuPinyinStringArray(c, format);
                            sb.append(strings[0]);
                        } else {
                            sb.append(String.valueOf(c));
                        }
                    } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                        badHanyuPinyinOutputFormatCombination.printStackTrace();
                    }
                }
            }
        }

        return sb.toString();
    }

    public static String getPinyinLowercase(String text) {
        try {
            StringBuilder sb = new StringBuilder();
            //输出格式
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            //去掉音调
            format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            //转化成大写
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            if (text != null) {
                char[] chars = text.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char c = chars[i];
                    //如果c是空格，直接跳过
                    if (Character.isWhitespace(c)) {
                        continue;
                    }
                    //如果是键盘上直接打出来的字符 A-Z 0-9
                    if (-128 < c && c <= 127) {
                        sb.append(c);
                    } else {
                        try {
                            if (isChinese(c)) {
                                String[] strings = PinyinHelper.toHanyuPinyinStringArray(c, format);
                                sb.append(strings[0]);
                            } else {
                                sb.append(String.valueOf(c));
                            }
                        } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                            badHanyuPinyinOutputFormatCombination.printStackTrace();
                        }
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }


    public static boolean isChinese(char c) {
        if ((c >= 0x4e00) && (c <= 0x9fbb)) {
            return true;
        }
        return false;
    }

    public static boolean containsChinese(String s) {
        if (null == s || "".equals(s.trim()))
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (isChinese(s.charAt(i)))
                return true;
        }
        return false;
    }

}
