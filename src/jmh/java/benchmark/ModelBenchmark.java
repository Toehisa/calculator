package benchmark;

import data.CalcModel;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(value = 3)
@Warmup(iterations = 3, time = 5, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.All)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ModelBenchmark {
    CalcModel model = new CalcModel();
    String ZERO = "0";
    String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] operations = {"+", "-", "*", "/", "%"};


    //@Benchmark
    public void userEmulator() {

        int i = 0, j = 0;

        while (i < digits.length) {
            onDotBtn();
            if (j >= operations.length) {
                j = 0;
            }
            onOperationBtn(operations[j++]);
            onDotBtn();
            //onEqBtn();

            onDigBtn(digits[i]);
            if (j >= operations.length) {
                j = 0;
            }
            onOperationBtn(operations[j++]);
            onDigBtn(digits[i]);
            onDigBtn(digits[i]);

            //onEqBtn();
            if (j >= operations.length) {
                j = 0;
            }
            onOperationBtn(operations[j++]);
            onDotBtn();

            onDelBtn();
            onDelBtn();
            onDelBtn();
            i++;
        }

        onAcBtn();

    }

    public void onDigBtn(String value) {
        if (model.keepDigitAfterZero()) {
            model.add(value);
        }
    }


    public void onOperationBtn(String value) {
        if (model.lastIsDigit()) {
            model.add(value);
        } else {
            model.replaceLast(value);
        }

    }
    @Benchmark
    public void onDotBtn() {
        if (model.dotInSequence()) {
            if (model.lastIsDigit()) {
                model.add(ZERO);
            } else {
                model.replaceLast(ZERO);
            }
        }
    }

    public void onAcBtn() {
        model.clear();
    }

    public void onDelBtn() {
        model.delLast();
    }

    public void onEqBtn() {
        model.calculate();
    }
}




