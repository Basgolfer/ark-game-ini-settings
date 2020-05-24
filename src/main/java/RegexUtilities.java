import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RegexUtilities {

    private static final String regex = "OverrideNamedEngramEntries=\\(EngramClassName=\"EngramEntry_(?<EngramEntry>\\w+_C)\", EngramHidden=(?<engramHidden>(?:true|false))(?:, EngramLevelRequirement=(?<EngramLevelRequirement>\\d+)|\\))(?:\\)|)";

    private final Pattern pattern;

    public RegexUtilities() {
        pattern = Pattern.compile(regex);
    }

    public Boolean lineMatchesEngramPattern(String line) {
        Matcher matcher = pattern.matcher(line);
        Boolean matches = matcher.matches();
        if (matches) {
            log.info("{} matches Engram Pattern!", line);
        } else {
            log.error("{} does not match Engram Pattern!", line);
        }
        return matches;
    }
}
