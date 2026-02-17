package com.toehisa.calculator.data;

import javafx.scene.control.Label;

import java.util.*;


public class CalcModel {
    private final StringBuilder builder = new StringBuilder();

    public void add(Object obj) {
        builder.append(obj);
    }

    public void go(Label label) {
        label.setText(builder.toString());
    }

    public void replaceLast(Object obj) {
        builder.replace(builder.length() - 1, builder.length(), (String) obj);
    }

    public boolean lastIsDigit() {
        if (!builder.isEmpty()) {
            return Character.isDigit(last());
        } else {
            return false;
        }
    }

    public boolean dotInSequence() {
        String[] sArr = builder.toString().split("[+\\-*/%]");

        char[] cArr = sArr[sArr.length - 1].toCharArray();

        for (int i = cArr.length - 1; i >= 0; i--) {
            if (cArr[i] == '.') {
                return false;
            }
        }
        return true;
    }

    public void clear() {
        builder.setLength(0);
    }

    public void delLast(Label label) {
        builder.deleteCharAt(builder.length() - 1);
        label.setText(builder.toString());
    }

    public boolean keepDigitAfterZero() {
        if (builder.isEmpty()) {
            return true;
        } else {
            for (int i = builder.length() - 1; i > 0; i--) {
                if (builder.charAt(i) == '.') {
                    return true;
                } else if (listContainsChar(builder.charAt(i), '+', '-', '*', '/', '%')) {
                    if (builder.length() - 1 < i + 1) {
                        return true;
                    } else {
                        return builder.charAt(i + 1) != '0';
                    }
                }
            }
            return builder.charAt(0) != '0';
        }
    }

    private boolean listContainsChar(char charVal, char... list) {
        for (char c : list) {
            if (c == charVal) {
                return true;
            }
        }
        return false;
    }

    private char last() {
        return builder.charAt(builder.length() - 1);
    }


}
