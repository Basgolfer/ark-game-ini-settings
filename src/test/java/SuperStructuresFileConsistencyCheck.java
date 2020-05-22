import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SuperStructuresFileConsistencyCheck {

    private DirectoryUtilities directoryUtilities;

    private FileUtilities fileUtilities;

    private RegexUtilities regexUtilities;

    private FileWriter fileWriter;

    @Before
    public void setup() throws IOException {
        directoryUtilities = new DirectoryUtilities();
        fileUtilities = new FileUtilities();
        regexUtilities = new RegexUtilities();
        fileWriter = new FileWriter("I:\\ark-game-ini-settings\\src\\main\\resources\\ARK Mods\\Super Structures\\_Master_File.txt");
    }

    @Test
    public void testAllSuperStructuresFiles() throws IOException {
        ArrayList<String> files = directoryUtilities.getDirectoryContents("I:\\ark-game-ini-settings\\src\\main\\resources\\ARK Mods\\Super Structures");
        for (String file : files) {
            fileUtilities.setBufferedReaderFromFile(file);
            String line;
            while ((line = fileUtilities.readLine()) != null) {
                if (!regexUtilities.lineMatchesEngramPattern(line)) {
                    Assert.fail("A line doesn't match the specified Engram Regex Pattern!");
                }
                fileWriter.write(line);
                fileWriter.write(System.getProperty("line.separator"));
            }
            fileWriter.write(System.getProperty("line.separator"));
        }
        fileWriter.close();
        //fileUtilities.deleteAllLinesThatAreBlankAtEndOfFile("I:\\ark-game-ini-settings\\src\\main\\resources\\ARK Mods\\Super Structures\\_Master_File.txt");
    }
}
