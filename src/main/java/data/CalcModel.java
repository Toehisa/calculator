package data;

import javafx.scene.control.Label;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;
import java.util.List;


public class CalcModel {
    private final StringBuilder builder;

    public CalcModel() {
        builder = new StringBuilder();
    }

    public CalcModel(int i) {
        builder = new StringBuilder(i);
    }

    public void add(String str) {
        builder.append(str);
    }

    public void clear() {
        builder.setLength(0);
    }

    public boolean isNotEmpty() {
        return !builder.isEmpty();
    }

    public void replaceLast(String str) {
        builder.replace(builder.length() - 1, builder.length(), str);
    }

    public boolean lastIsDigit() {
        if (!builder.isEmpty()) {
            return Character.isDigit(last());
        } else {
            return false;
        }
    }

    public boolean dotInSequence_old() {
        String[] sArr = builder.toString().split("[+\\-*/%]");
        char[] cArr = sArr[sArr.length - 1].toCharArray();

        for (int i = cArr.length - 1; i >= 0; i--) {
            if (cArr[i] == '.') {
                return true;
            }
        }
        return false;
    }

    public boolean dotInSequence_new() {
        for (int i = builder.length() - 1; i > 0; i--) {
            if (builder.charAt(i) == '.') {
                return true;
            } else if (isMathOperation(builder.charAt(i - 1))) {
                return false;
            }
        }
        return false;
    }

    public void delLast() {
        builder.deleteCharAt(builder.length() - 1);
    }

    public String calculate() {
        int start = 0;

        List<Character> operations = new LinkedList<>();
        List<Double> values = new LinkedList<>();

        delExtraSymbol();

        for (int i = 0; i < builder.length(); i++) {
            if (isMathOperation(builder.charAt(i))) {
                values.add(Double.parseDouble(builder.substring(start, i)));
                operations.add(builder.charAt(i));
                start = i + 1;
            }
        }


        if (start < builder.length()) {
            values.add(
                    Double.parseDouble(
                            builder.substring(start, builder.length())
                    )
            );
        }

        double value = calcValue(values, operations);
        return Double.toString(roundDoubleValue(value));
    }

    public boolean keepDigitAfterZero() {
        for (int i = builder.length() - 1; i > 0; i--) {
            if (builder.charAt(i) == '.') {
                return true;
            } else if (isMathOperation(builder.charAt(i))) {
                if (builder.length() - 1 < i + 1) {
                    return true;
                } else {
                    return builder.charAt(i + 1) != '0';
                }
            }
        }
        return builder.charAt(0) != '0';
    }

    public void go(Label label) {
        label.setText(builder.toString());
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    private void delExtraSymbol() {
        if (isMathOperation(last())) {
            delLast();
        }
    }

    private static double calcValue(List<Double> values, List<Character> operations) {
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
                    if (value == 0.0 || values.get(j) == 0.0) {
                        return getZeroDivideResult(value, values.get(j));
                    } else {
                        value /= values.get(j++);
                    }
                    break;
                case '%':
                    value *= values.get(j++) / 100;
                    break;
            }
        }
        return value;
    }

    private boolean isMathOperation(char charVal) {
        return switch (charVal) {
            case '+', '/', '-', '*', '%' -> true;
            default -> false;
        };
    }

    private char last() {
        return builder.charAt(builder.length() - 1);
    }

    private static double getZeroDivideResult(double val, double val2) {
        if (val != 0 && val2 == 0) {
            return Double.POSITIVE_INFINITY;
        } else if (val == 0 && val2 != 0) {
            return Double.NEGATIVE_INFINITY;
        } else {
            return Double.NaN;
        }
    }

    private static double roundDoubleValue(double value) {
        MathContext mc = new MathContext(6);

        try {
            return new BigDecimal(value)
                    .round(mc)
                    .doubleValue();
        } catch (NumberFormatException ignored) {
            return value;
        }
    }
}
