import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        expectedDirectoryContents.add("I:\\ark-game-ini-settings\\src\\test\\resources\\Level 9001 Overrides.txt");
        expectedDirectoryContents.add("I:\\ark-game-ini-settings\\src\\test\\resources\\Level 9003 Overrides.txt");

        ArrayList<String> actualDirectoryContents = directoryUtilities.getDirectoryContents(directory);

        Assert.assertNotNull(actualDirectoryContents);
        Assert.assertEquals(expectedDirectoryContents, actualDirectoryContents);
        Assert.assertEquals(actualDirectoryContents.size(), actualDirectoryContents.size());
    }

    @Test
    public void getDirectoryContentsTest_WhenPathProvidedIsInvalid() {
        String invalidDirectory = "I:\\invalidDirectory";
        Assert.assertNull(directoryUtilities.getDirectoryContents(invalidDirectory));
    }
}
