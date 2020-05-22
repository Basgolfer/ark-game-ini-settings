import lombok.extern.slf4j.Slf4j;

import java.io.*;

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
            return bufferedReader.readLine();
        } catch (NullPointerException | IOException e) {
            log.error("Buffered reader instance variable is null or an IO exception occurred. Exception message: {} ", e.getMessage());
        }
        return null;
    }

    public void deleteAllBlankLinesAtEndOfFile(String file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        long length = randomAccessFile.length();
        byte b;
        do {
            length -= 1;
            randomAccessFile.seek(length);
            b = randomAccessFile.readByte();
        } while(b != 10);
        randomAccessFile.setLength(length - 1);
        randomAccessFile.close();
    }
}
