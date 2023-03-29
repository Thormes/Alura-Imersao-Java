package ConsoleStyler;

public enum Cor {
    VERDE("0;255;0"),
    VERMELHO("255;0;0"),
    AMARELO("255;255;0"),
    AZUL("255;0;0"),
    BRANCO("255;255;255"),
    PRETO("0;0;0"),
    CINZA("110;110;110");

    public final String value;

    Cor(String value) {
        this.value = value;
    }
}
