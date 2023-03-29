import java.nio.charset.StandardCharsets;

public class ConsoleStyler {
    private static final String ANSI_START = "\u001b[xm";
    private static final String ANSI_END = "\u001b[m";
    public String estilizado(String text, String[] styles){
        StringBuilder s = new StringBuilder();
        text = new String(text.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1);
        for (String ansi: styles) {
            s.append(ANSI_START.replace("x", ansi));
        }
        return (s + text + ANSI_END);
    }
    public String textColor(Cor cor){
        return "38;2;" + cor.value;
    }
    public String backColor(Cor cor){
        return "48;2;" + cor.value;
    }

    public String backColorFromRating(String rating){
        double classificacao = Double.parseDouble(rating);
        if (classificacao <= 4.0){
            return backColor(Cor.VERMELHO);
        } else if (classificacao <= 7) {
            return backColor(Cor.AMARELO);
        } else{
            return backColor(Cor.VERDE);
        }
    }

    public String starsFromRating(String rating){
        String star = "⭐";
        double classificacao = Double.parseDouble(rating);
        StringBuilder s = new StringBuilder();
        for (double i = classificacao; i >= 1; i--){
            s.append(star);
        }
        return s.toString();
    }

}

