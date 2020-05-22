import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtilitiesTests {

    private FileUtilities fileUtilities;

    @Before
    public void setup() {
        fileUtilities = new FileUtilities();
    }

    @Test
    public void setBufferedReaderFromFileTest_whenFileIsValid() {
        Assert.assertTrue(fileUtilities.setBufferedReaderFromFile("I:\\ark-game-ini-settings\\src\\test\\resources\\test file.txt"));
    }

    @Test
    public void setBufferedReaderFromFileTest_whenFileDoesNotExist() {
        Assert.assertFalse(fileUtilities.setBufferedReaderFromFile("I:\\doesNotExist"));
    }

    @Test
    public void readLineTest_whenFileIsValid() {
        fileUtilities.setBufferedReaderFromFile("I:\\ark-game-ini-settings\\src\\test\\resources\\test file.txt");

        String expectedLine = "hello I am a test file";
        String actualLine = fileUtilities.readLine();
        Assert.assertEquals(expectedLine, actualLine);

        expectedLine = "Can you read me?";
        actualLine = fileUtilities.readLine();
        Assert.assertEquals(expectedLine, actualLine);

        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());

        Assert.assertNull(fileUtilities.readLine());
    }

    @Test
    public void readLineTest_whenBufferedReaderIsNull() {
        Assert.assertNull(fileUtilities.readLine());
    }

    @Test
    public void deleteLastLineFromFileTest() throws IOException {
        fileUtilities.setBufferedReaderFromFile("I:\\ark-game-ini-settings\\src\\test\\resources\\test file.txt");
        fileUtilities.deleteLastLineFromFile("I:\\ark-game-ini-settings\\src\\test\\resources\\test file.txt");

        Assert.assertEquals("hello I am a test file", fileUtilities.readLine());
        Assert.assertEquals("Can you read me?", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertEquals("", fileUtilities.readLine());
        Assert.assertNull(fileUtilities.readLine());

        //Add line back after tests so we can run again

        FileWriter fileWriter = new FileWriter("I:\\ark-game-ini-settings\\src\\test\\resources\\test file.txt");
        fileWriter.append("hello I am a test file");
        fileWriter.append(System.getProperty("line.separator"));
        fileWriter.append("Can you read me?");
        fileWriter.append(System.getProperty("line.separator"));
        fileWriter.append(System.getProperty("line.separator"));
        fileWriter.append(System.getProperty("line.separator"));
        fileWriter.append(System.getProperty("line.separator"));
        fileWriter.append(System.getProperty("line.separator"));
        fileWriter.append(System.getProperty("line.separator"));
        fileWriter.append(System.getProperty("line.separator"));
        fileWriter.append(System.getProperty("line.separator"));
        fileWriter.close();
    }
}
