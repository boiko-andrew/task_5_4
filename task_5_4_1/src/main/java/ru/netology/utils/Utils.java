package ru.netology.utils;

public class Utils {
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
