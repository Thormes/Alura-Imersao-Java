public enum URLList {
    TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json"),
    TOP_TV("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json"),
    POPULAR_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json"),
    POPULAR_TV("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json");

    public final String url;

    URLList(String url) {
        this.url = url;
    }
}
