package com.shia.library.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * String类型公共类
 * */
public class StringUtil {

    /**
     * check String is null or not;
     * 
     * @param checkStr
     * @return boolean
     */
    public static boolean isNull(String checkStr) {
        return checkStr == null || checkStr.trim().length() == 0 || checkStr.trim().equalsIgnoreCase("null");
    }

    /**
     * parse String to int
     * 
     * @param intStr
     * @param defaultInt
     * @return int
     */
    public static int parseInt(String intStr, int defaultInt) {
        try {
            return Integer.parseInt(intStr);
        } catch (Exception e) {
            return defaultInt;
        }
    }

    /**
     * parse String to int default is 0
     * 
     * @param intStr
     * @return int
     */
    public static int parseInt(String intStr) {
        return parseInt(intStr, 0);
    }

    /**
     * check String is int
     * 
     * @param str
     * @return boolean
     */
    public static boolean isInt(String str) {
        Pattern pattern = Pattern.compile("(0|[1-9][0-9]*)");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 
     * 
     * @param str
     * @param charset
     * @return String
     */
    public static String encodeStr(String str, String charset) {
        if (isNull(str)) {
            return str;
        } else {
            try {
                return new String(str.getBytes(charset));
            } catch (Exception e) {
                return str;
            }
        }
    }

    /**
     * trim String
     * 
     * @param str
     * @return String
     */
    public static String trimStr(String str) {
        if (null != str)
            return str.trim();
        else
            return str;
    }

    /**
     * get substring term from src
     * 
     * @param src
     * @param term
     * @return String
     */
    public static String subBefore(String src, String term) {
        if (src == null || term == null) {
            return null;
        } else {
            int index = src.indexOf(term);
            return index >= 0 ? src.substring(0, index) : src;
        }
    }

    /**
     * get substring after term from src
     * 
     * @param src
     * @param term
     * @return string
     */
    public static String subAfter(String src, String term) {
        if (src == null || term == null) {
            return null;
        } else {
            int index = src.indexOf(term);
            return index >= 0 ? src.substring(index + term.length()) : src;
        }
    }

    /**
     * get substring last before term from src
     * 
     * @param src
     * @param term
     * @return string
     */
    public static String subLastBefore(String src, String term) {
        if (src == null || term == null) {
            return null;
        } else {
            int index = src.lastIndexOf(term);
            return index >= 0 ? src.substring(0, index) : src;
        }
    }

    /**
     * get substring last after term from src
     * 
     * @param src
     * @param term
     * @return string
     */
    public static String subLastAfter(String src, String term) {
        if (src == null || term == null) {
            return null;
        } else {
            int index = src.lastIndexOf(term);
            return index >= 0 ? src.substring(index + term.length()) : src;
        }
    }

    /**
     * <p>
     * Gets a substring from the specified String avoiding exceptions.
     * </p>
     * 
     * <p>
     * A negative start position can be used to start <code>n</code> characters
     * from the end of the String.
     * </p>
     * 
     * <p>
     * A <code>null</code> String will return <code>null</code>. An empty ("")
     * String will return "".
     * </p>
     * 
     * <pre>
     * StringUtils.substring(null, *)   = null
     * StringUtils.substring("", *)     = ""
     * StringUtils.substring("abc", 0)  = "abc"
     * StringUtils.substring("abc", 2)  = "c"
     * StringUtils.substring("abc", 4)  = ""
     * StringUtils.substring("abc", -2) = "bc"
     * StringUtils.substring("abc", -4) = "abc"
     * </pre>
     * 
     * @param str
     *            the String to get the substring from, may be null
     * @param start
     *            the position to start from, negative means count back from the
     *            end of the String by this many characters
     * @return substring from start position, <code>null</code> if null String
     *         input
     */
    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        }

        // handle negatives, which means last n characters
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return "";
        }

        return str.substring(start);
    }

    /**
     * <p>
     * Gets a substring from the specified String avoiding exceptions.
     * </p>
     * 
     * <p>
     * A negative start position can be used to start/end <code>n</code>
     * characters from the end of the String.
     * </p>
     * 
     * <p>
     * The returned substring starts with the character in the
     * <code>start</code> position and ends before the <code>end</code>
     * position. All postion counting is zero-based -- i.e., to start at the
     * beginning of the string use <code>start = 0</code>. Negative start and
     * end positions can be used to specify offsets relative to the end of the
     * String.
     * </p>
     * 
     * <p>
     * If <code>start</code> is not strictly to the left of <code>end</code>, ""
     * is returned.
     * </p>
     * 
     * <pre>
     * StringUtils.substring(null, *, *)    = null
     * StringUtils.substring("", * ,  *)    = "";
     * StringUtils.substring("abc", 0, 2)   = "ab"
     * StringUtils.substring("abc", 2, 0)   = ""
     * StringUtils.substring("abc", 2, 4)   = "c"
     * StringUtils.substring("abc", 4, 6)   = ""
     * StringUtils.substring("abc", 2, 2)   = ""
     * StringUtils.substring("abc", -2, -1) = "b"
     * StringUtils.substring("abc", -4, 2)  = "ab"
     * </pre>
     * 
     * @param str
     *            the String to get the substring from, may be null
     * @param start
     *            the position to start from, negative means count back from the
     *            end of the String by this many characters
     * @param end
     *            the position to end at (exclusive), negative means count back
     *            from the end of the String by this many characters
     * @return substring from start position to end positon, <code>null</code>
     *         if null String input
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return "";
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    // Left/Right/Mid
    // -----------------------------------------------------------------------
    /**
     * <p>
     * Gets the leftmost <code>len</code> characters of a String.
     * </p>
     * 
     * <p>
     * If <code>len</code> characters are not available, or the String is
     * <code>null</code>, the String will be returned without an exception. An
     * exception is thrown if len is negative.
     * </p>
     * 
     * <pre>
     * StringUtils.left(null, *)    = null
     * StringUtils.left(*, -ve)     = ""
     * StringUtils.left("", *)      = ""
     * StringUtils.left("abc", 0)   = ""
     * StringUtils.left("abc", 2)   = "ab"
     * StringUtils.left("abc", 4)   = "abc"
     * </pre>
     * 
     * @param str
     *            the String to get the leftmost characters from, may be null
     * @param len
     *            the length of the required String, must be zero or positive
     * @return the leftmost characters, <code>null</code> if null String input
     */
    public static String left(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return "";
        }
        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(0, len);
        }
    }

    /**
     * <p>
     * Gets the rightmost <code>len</code> characters of a String.
     * </p>
     * 
     * <p>
     * If <code>len</code> characters are not available, or the String is
     * <code>null</code>, the String will be returned without an an exception.
     * An exception is thrown if len is negative.
     * </p>
     * 
     * <pre>
     * StringUtils.right(null, *)    = null
     * StringUtils.right(*, -ve)     = ""
     * StringUtils.right("", *)      = ""
     * StringUtils.right("abc", 0)   = ""
     * StringUtils.right("abc", 2)   = "bc"
     * StringUtils.right("abc", 4)   = "abc"
     * </pre>
     * 
     * @param str
     *            the String to get the rightmost characters from, may be null
     * @param len
     *            the length of the required String, must be zero or positive
     * @return the rightmost characters, <code>null</code> if null String input
     */
    public static String right(String str, int len) {
        if (str == null) {
            return null;
        }
        if (len < 0) {
            return "";
        }
        if (str.length() <= len) {
            return str;
        } else {
            return str.substring(str.length() - len);
        }
    }

    /**
     * check term is exist from src
     * 
     * @param src
     * @param term
     * @return boolean
     */
    public static boolean exist(String src, String term) {
        if (src == null || term == null) {
            return false;
        }
        int index = src.indexOf(term);
        return index >= 0;
    }

    /**
     * 
     * @param str
     * @param rex
     * @return boolean
     */
    public static boolean rexp(String str, String rex) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static String join(Object[] arr) {
        return join(arr, ",");
    }

    public static String join(Object[][] arr) {
        return join(arr, ",");
    }

    /**
     * 将一个数组以指定的分隔符拼成一个字符串
     */
    public static String join(Object[] arr, String spliter) {
        if (arr == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                sb.append(spliter);
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static String join(Object[][] arr, String spliter) {
        if (arr == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) {
                sb.append(spliter);
            }
            sb.append(join(arr[i], spliter));
        }
        return sb.toString();
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }


    /**
     * 将字符串转换成可以在JAVA表达式中直接使用的字符串，处理一些转义字符
     * 
     * @param txt
     *            需要编码的字符串
     * @return
     */
    public static String javaEncode(String txt) {
        if (txt == null || txt.length() == 0) {
            return txt;
        }
        txt = replaceEx(txt, "\\", "\\\\");
        txt = replaceEx(txt, "\r\n", "\n");
        txt = replaceEx(txt, "\n", "\\n");
        txt = replaceEx(txt, "\"", "\\\"");
        txt = replaceEx(txt, "\'", "\\\'");
        return txt;
    }

    public static String javaDecode(String txt) {
        if (txt == null || txt.length() == 0) {
            return txt;
        }
        txt = replaceEx(txt, "\\\\", "\\");
        txt = replaceEx(txt, "\\n", "\n");
        txt = replaceEx(txt, "\\r", "\r");
        txt = replaceEx(txt, "\\\"", "\"");
        txt = replaceEx(txt, "\\\'", "\'");
        return txt;
    }

    public static String replaceEx(String str, String subStr, String reStr) {
        if (str == null) {
            return null;
        }
        if (subStr == null || subStr.equals("") || subStr.length() > str.length() || reStr == null) {
            return str;
        }
        StringBuffer sb = new StringBuffer();
        String tmp = str;
        int index = -1;
        while (true) {
            index = tmp.indexOf(subStr);
            if (index < 0) {
                break;
            } else {
                sb.append(tmp.substring(0, index));
                sb.append(reStr);
                tmp = tmp.substring(index + subStr.length());
            }
        }
        sb.append(tmp);
        return sb.toString();
    }

    /**
     * Javascript中escape的JAVA实现
     */
    public static String escape(String src) {
        char j;
        StringBuffer sb = new StringBuffer();
        sb.ensureCapacity(src.length() * 6);
        for (int i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) {
                sb.append(j);
            } else if (j < 256) {
                sb.append("%");
                if (j < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toString(j, 16));
            } else {
                sb.append("%u");
                sb.append(Integer.toString(j, 16));
            }
        }
        return sb.toString();
    }

    /**
     * Javascript中unescape的JAVA实现
     */
    public static String unescape(String src) {
        StringBuffer sb = new StringBuffer();
        sb.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    sb.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    sb.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    sb.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    sb.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return sb.toString();
    }

}
