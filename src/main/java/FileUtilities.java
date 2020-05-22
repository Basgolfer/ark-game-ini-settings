import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileUtilities {

    private BufferedReader bufferedReader;
    private RandomAccessFile randomAccessFile;

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

    private void deleteLastLineFromFile() throws IOException {
        long length = randomAccessFile.length();
        byte b;
        do {
            length -= 1;
            randomAccessFile.seek(length);
            b = randomAccessFile.readByte();
        } while(b != 10);
        randomAccessFile.setLength(length - 1);
    }

    public void deleteAllLinesThatAreBlankAtEndOfFile(String file) throws IOException {
        randomAccessFile = new RandomAccessFile(file, "rw");
        String line;
        while ((line = randomAccessFile.readLine()) != null) {
            if ("".equalsIgnoreCase(line)) {
                System.out.println("empty line");
                deleteLastLineFromFile();
            }
            else {
                System.out.println(line);
            }
        }
        randomAccessFile.close();
    }
}
