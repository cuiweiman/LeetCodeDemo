package com.bishi.bytedance;

import java.util.Objects;

/**
 * @description: 最长回文子串
 * @date: 2021/3/19 9:32
 * @author: wei·man cui
 */
public class Palindrome {

    public static void main(String[] args) {
        System.out.println(hui("abtabcdedcbatb"));
        System.out.println(hui2("abtabcdedcbatb"));
    }

    /**
     * 中心扩散
     *
     * @param str 字符串
     * @return 最长回文子串
     */
    public static String hui2(String str) {
        if (Objects.equals(str, "")) {
            return str;
        }
        int l = 0;
        int r = 1;
        int maxLen = 1;
        for (int i = 0; i < str.length(); i++) {
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
                if (right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    l = left;
                    r = right;
                }
                left--;
                right++;
            }

            left = i;
            right = i + 1;
            while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
                if (right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    l = left;
                    r = right;
                }
                left--;
                right++;
            }
        }
        return str.substring(l, r + 1);
    }

    /**
     * 方案一：截取子串 暴力破解，判断子串是否是回文，然后取最大长度。待优化
     *
     * @param str 字符串
     * @return 回文子串最大长度
     */
    public static Integer hui(String str) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String sub = str.substring(i, j);
                if (judge(sub)) {
                    if (max < sub.length()) {
                        max = sub.length();
                    }
                }
            }
        }
        return max;
    }

    public static boolean judge(String str) {
        int mid = str.length() / 2;
        String left = str.substring(0, mid);
        int rightIndex = str.length() % 2 == 0 ? mid : mid + 1;
        String right = str.substring(rightIndex);
        return Objects.equals(left, new StringBuffer(right).reverse().toString());

        /*int j = 0;
        for (int i = left.length() - 1; i >= 0; i--) {
            if (left.charAt(i) != right.charAt(j)) {
                return false;
            }
            j++;
        }
        return true;*/
    }

}
