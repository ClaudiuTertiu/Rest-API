package utils;

<<<<<<< Updated upstream
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
=======
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
>>>>>>> Stashed changes
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;

public class ApiUtils {

    private ApiUtils() {
    }

    public static Map<String, List<String>> splitQuery(String query) {
        if (query == null || "".equals(query)) {
            return Collections.emptyMap();
        }

        return Pattern.compile("&").splitAsStream(query)
            .map(s -> Arrays.copyOf(s.split("="), 2))
            .collect(groupingBy(s -> decode(s[0]), mapping(s -> decode(s[1]), toList())));

    }

    private static String decode(final String encoded) {
        try {
            return encoded == null ? null : URLDecoder.decode(encoded, "UTF-8");
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 is a required encoding", e);
        }
    }

<<<<<<< Updated upstream
=======
    public static void jsonPrettyArray(String filename) throws IOException {
        Path path = Paths.get(filename);
        Charset charset = StandardCharsets.UTF_8;
        String content = new String(Files.readAllBytes(path), charset);
        content = content.replaceAll("} \\[ \\{", "} , {");
        Files.write(path, content.getBytes(charset));
    }

    public static RandomAccessFile makeArrayNotFinished(String filename) throws IOException {
        RandomAccessFile output = new RandomAccessFile(filename, "rw");
        long pos = output.length();
        while (output.length() > 0) {
            pos--;
            output.seek(pos);
            if (output.readByte() == ']') {
                output.seek(pos);
                break;
            }
        }
        return output;
    }
>>>>>>> Stashed changes

}
