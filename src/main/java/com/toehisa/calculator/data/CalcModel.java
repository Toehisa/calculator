package com.toehisa.calculator.data;

import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public void clear(Label label) {
        builder.setLength(0);
        label.setText("");
    }

    public void delLast(Label label) {
        builder.deleteCharAt(builder.length() - 1);
        label.setText(builder.toString());
    }
    public void delLast() {
        builder.deleteCharAt(builder.length() - 1);
    }

    public void calculate(Label label) {
        int start = 0;
        char[] chars = {'+', '-', '*', '/', '%'};

        List<Character> operations = new LinkedList<>();
        List<Double> values = new LinkedList<>();

        delExtraSymbol(chars);

        for (int i = 0; i < builder.length(); i++) {
            if (listContainsChar(builder.charAt(i), chars)) {
                values.add(Double.parseDouble(builder.substring(start, i)));
                operations.add(builder.charAt(i));
                start = i+1;
            }
        }


        if (start < builder.length()) {
            values.add(
                    Double.parseDouble(
                            builder.substring(start, builder.length())
                    )
            );
        }


        double value = new BigDecimal(getValue(values, operations))
                .setScale(4, RoundingMode.HALF_UP)
                .doubleValue();

        label.setText(Double.toString(value));
    }

    private void delExtraSymbol(char... chars) {
        if (listContainsChar(last(), chars)) {
            delLast();
        }
    }

    private static double getValue(List<Double> values, List<Character> operations) {
        double value = values.getFirst();

        for (int i = 0, j = 1; i <= values.size() && j <= operations.size(); ) {
            switch (operations.get(i++)) {
                case '+':
                    value += values.get(j++);
                    break;
                case '-':
                    value -= values.get(j++);
                    break;
                case '*':
                    value *= values.get(j++);
                    break;
                case '/':
                    value /= values.get(j++);
                    break;
                case '%':
                    value *= values.get(j++) / 100;
                    break;
            }
        }
        return value;
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
