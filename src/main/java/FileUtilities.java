import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class FileUtilities {

    private BufferedReader bufferedReader;

    public Boolean setBufferedReaderFromFile(String fileName) {
        try {
            bufferedReader = new BufferedReader(new FileReader(fileName));
            log.info("Buffered reader set to: {}", fileName);
            return true;
        } catch (FileNotFoundException e) {
            log.error("File not found: {}", fileName);
        }
        return false;
    }

    public String readLine() {
        try {
            String line = bufferedReader.readLine();
            log.info("Reading line: {}", line);
            return line;
        } catch (NullPointerException | IOException e) {
            log.error("Buffered reader instance variable is null or an IO exception occurred. Exception message: {} ", e.getMessage());
        }
        return null;
    }
}
