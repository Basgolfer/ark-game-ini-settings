import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DirectoryUtilities {

    private ArrayList<String> directoryContents;

    public ArrayList<String> getDirectoryContents(String directory) {
        directoryContents = new ArrayList<>();
        try {
            Files.list(new File(directory).toPath())
                    .limit(10000)
                    .forEach(file -> {
                        //log.info("Found file: {}", file);
                        if (fileMatchesFormat(file.toString())) {
                            directoryContents.add(file.toString());
                        }
                    });
            return sortByLevelNumber();
        } catch (IOException e) {
            log.error("Can not find specified directory: {} ", e.getMessage());
        }
        return null;
    }

    private Boolean fileMatchesFormat(String fileName) {
        return fileName.matches(".*Level \\d+ Overrides\\.txt");
    }

    private ArrayList<String> sortByLevelNumber() throws FileSortingException {
        directoryContents.sort((string1, string2) -> {
            Integer string1Integer = getIntegerFromFilePath(string1);
            Integer string2Integer = getIntegerFromFilePath(string2);
            if (null != string1Integer && null != string2Integer) {
                return string1Integer - string2Integer;
            }
            throw new FileSortingException("File sorting error has occurred. Neither string1Integer or string2Integer should be null.");
        });
        return directoryContents;
    }

    private Integer getIntegerFromFilePath(String filePath) {
        Pattern pattern = Pattern.compile("(?<fileNumber>\\d+)");
        Matcher matcher = pattern.matcher(filePath);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group("fileNumber"));
        }
        log.error("No integer found in the file path!");
        return null;
    }
}
