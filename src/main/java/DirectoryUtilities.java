import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

@Slf4j
public class DirectoryUtilities {

    private ArrayList<String> directoryContents;

    public ArrayList<String> getDirectoryContents(String directory) {
        directoryContents = new ArrayList<>();
        try {
            Files.list(new File(directory).toPath())
                    .limit(10000)
                    .forEach(file -> {
                        log.info("Found file: {}", file);
                        if (fileMatchesFormat(file.toString())) {
                            directoryContents.add(file.toString());
                        }
                    });
            return directoryContents;
        } catch (IOException e) {
            log.error("Can not find specified directory: {} ", e.getMessage());
        }
        return null;
    }

    private Boolean fileMatchesFormat(String fileName) {
        return fileName.matches(".*Level \\d+ Overrides\\.txt");
    }
}
