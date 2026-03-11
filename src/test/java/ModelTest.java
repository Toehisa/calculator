import data.CalcModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    CalcModel model = new CalcModel(31);
    @Test
    public void testDotInSequence_old() {
        model.add("0");
        assertFalse(model.dotInSequence_old(),"no dot");
        model.clear();
        model.add("0.");
        assertTrue(model.dotInSequence_old(),"dot in sequence");
        model.clear();
        model.add("0.10+0");
        assertFalse(model.dotInSequence_old(),"bad split");
        model.clear();
        model.add("0.10+0.");
        assertTrue(model.dotInSequence_old(),"bad split");
    }

    @Test
    public void testDotInSequence_new() {
        model.add("0");
        assertFalse(model.dotInSequence_new(),"no dot: 0");
        model.clear();
        model.add("0.");
        assertTrue(model.dotInSequence_new(),"dot in sequence: 0.");
        model.clear();
        model.add("0.10+0");
        assertFalse(model.dotInSequence_new(),"bad split: 0.10+0");
        model.clear();
        model.add("0.10+0.");
        assertTrue(model.dotInSequence_new(),"bad split: 0.10+0.");
    }


}



