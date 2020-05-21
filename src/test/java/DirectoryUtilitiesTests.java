import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;

public class DirectoryUtilitiesTests {

    private DirectoryUtilities directoryUtilities;

    @Before
    public void setup() {
        directoryUtilities = new DirectoryUtilities();
    }

    @Test
    public void getDirectoryContentsTest_whenPathProvidedIsValid() {
        String directory = "I:\\ark-game-ini-settings\\src\\test\\resources";

        ArrayList<String> expectedDirectoryContents = new ArrayList<>();
        expectedDirectoryContents.add("I:\\ark-game-ini-settings\\src\\test\\resources\\Level 9 Overrides.txt");
        expectedDirectoryContents.add("I:\\ark-game-ini-settings\\src\\test\\resources\\Level 90 Overrides.txt");
        expectedDirectoryContents.add("I:\\ark-game-ini-settings\\src\\test\\resources\\Level 9001 Overrides.txt");
        expectedDirectoryContents.add("I:\\ark-game-ini-settings\\src\\test\\resources\\Level 9003 Overrides.txt");

        ArrayList<String> actualDirectoryContents = directoryUtilities.getDirectoryContents(directory);

        Assert.assertNotNull(actualDirectoryContents);
        Assert.assertEquals(expectedDirectoryContents, actualDirectoryContents);
        Assert.assertEquals(actualDirectoryContents.size(), actualDirectoryContents.size());

    }

    @Test
    public void getDirectoryContentsTest_whenPathProvidedIsInvalid() {
        String invalidDirectory = "I:\\invalidDirectory";
        Assert.assertNull(directoryUtilities.getDirectoryContents(invalidDirectory));
    }

    @Test (expected = FileSortingException.class)
    public void sortByLevelNumberTest_whenSortReturns0() {
        ArrayList<String> list = new ArrayList<>();
        list.add("\\FakeFileWithNoNumber");
        list.add("\\FakeFileWithStillNoNumber");
        ReflectionTestUtils.setField(directoryUtilities, "directoryContents", list);
        ReflectionTestUtils.invokeMethod(directoryUtilities, "sortByLevelNumber");
    }

    @Test
    public void getIntegerFromFilePathTest_whenNoIntegerInFilePath() {
        Assert.assertNull(ReflectionTestUtils.invokeMethod(directoryUtilities, "getIntegerFromFilePath", "I:\\ark-game-ini-settings\\src\\test\\resources\\test file.txt"));
    }
}
