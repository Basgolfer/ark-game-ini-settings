import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

public class RegexUtilitiesTests {

    private RegexUtilities regexUtilities;

    @Before
    public void setup() {
        regexUtilities = new RegexUtilities();
    }

    @Test
    public void constructorTest() throws Exception {
        regexUtilities = new RegexUtilities();
        Field patternField = regexUtilities.getClass().getDeclaredField("pattern");
        Assert.assertNotNull(patternField);
    }

    @Test
    public void lineMatchEngramPatternTest_whenLineIsCorrect() {
        String line = "OverrideNamedEngramEntries=(EngramClassName=\"EngramEntry_SPlusCraftingStation_C\", EngramHidden=true)";
        Assert.assertTrue(regexUtilities.lineMatchesEngramPattern(line));
    }

    @Test
    public void lineMatchEngramPatternTest_whenLineIsIncorrect() {
        String line = "I won't watch the pattern";
        Assert.assertFalse(regexUtilities.lineMatchesEngramPattern(line));
    }
}
