import data.CalcModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    CalcModel model = new CalcModel();
    @Test
    public void testDotInSequence() {
        assertFalse(model.dotInSequence(),"builder is empty");
        model.add("0");
        assertTrue(model.dotInSequence(),"no dot");
        model.add(".");
        assertFalse(model.dotInSequence(),"dot in sequence");
        model.add("10+0");
        assertTrue(model.dotInSequence(),"bad split");
        model.add(".");
        assertFalse(model.dotInSequence(),"bad split");
    }
}



