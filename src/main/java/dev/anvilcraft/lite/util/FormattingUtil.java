package dev.anvilcraft.lite.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class FormattingUtil {
    public static String toEnglishName(Object internalName) {
        return Arrays.stream(internalName.toString().toLowerCase(Locale.ROOT).split("_"))
            .map(StringUtils::capitalize)
            .collect(Collectors.joining(" "));
    }
}
