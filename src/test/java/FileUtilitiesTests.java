import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

        Assert.assertNull(fileUtilities.readLine());
    }

    @Test
    public void readLineTest_whenBufferedReaderIsNull() {
        Assert.assertNull(fileUtilities.readLine());
    }
}
