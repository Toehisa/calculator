package benchmark;

import data.CalcModel;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(value = 5)
@Warmup(iterations = 10, time = 50, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 50, timeUnit = TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.All)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ModelBenchmark {
    CalcModel model = new CalcModel();

    @Setup
    public void setup() {
        model.clear();
        model.add("0.000000001%0.1*0.000000001/12345-345673467363+523453.000005521");
    }

    @Benchmark
    public void dotInSequence_old() {
        model.dotInSequence_old();
    }
    @Benchmark
    public void dotInSequence_new() {
        model.dotInSequence_new();
    }

    @Benchmark
    public void onEqBtn() {
        model.calculate();
    }
}




