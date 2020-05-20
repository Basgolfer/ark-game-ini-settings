import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SuperStructuresFileConsistencyCheck {

    private DirectoryUtilities directoryUtilities;

    private FileUtilities fileUtilities;

    private RegexUtilities regexUtilities;

    @Before
    public void setup() {
        directoryUtilities = new DirectoryUtilities();
        fileUtilities = new FileUtilities();
        regexUtilities = new RegexUtilities();
    }

    @Test
    public void testAllSuperStructuresFiles() {
        ArrayList<String> files = directoryUtilities.getDirectoryContents("I:\\ark-game-ini-settings\\src\\main\\resources\\ARK Mods\\Super Structures");
        for (String file : files) {
            fileUtilities.setBufferedReaderFromFile(file);
            String line;
            while ((line = fileUtilities.readLine()) != null) {
                if (!regexUtilities.lineMatchesEngramPattern(line)) {
                    Assert.fail("Problem!");
                }
            }
        }
    }
}
