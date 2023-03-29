public enum Realce {
    NEGRITO("1"),
    ITALICO("3"),
    SUBLINHADO("4"),
    PISCANDO("5"),
    INVERTIDO("7"),
    RISCADO("9");

    public final String value;

    Realce(String value) {
        this.value = value;
    }
}
