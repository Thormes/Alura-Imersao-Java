import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnvParser {
    private static final Pattern REGEX_ATRIBUTOS = Pattern.compile("(.+?)=(.*)");
    public Map<String, String> properties;

    public EnvParser() throws IOException {
        Path path = Paths.get(".env");
        List<String> lines = Files.readAllLines(path);
        this.properties = this.parse(lines);
    }

    private Map<String, String> parse(List<String> lines) {
        Map<String, String> dados = new HashMap<>();
        for (String item : lines) {
            Matcher matcherAtributosJson = REGEX_ATRIBUTOS.matcher(item);
            while (matcherAtributosJson.find()) {
                String atributo = matcherAtributosJson.group(1);
                String valor = matcherAtributosJson.group(2);
                dados.put(atributo, valor);
            }
        }
        return dados;
    }
}
