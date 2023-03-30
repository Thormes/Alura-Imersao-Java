public enum API {
    IMDB(URLList.TOP_MOVIES.url, new ExtratorDeConteudoDoIMDB()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14", new ExtratorDeConteudoDaNasa()),
    MARVEL((new MarvelAPIUrlCreator()).getUrl(), new ExtratorDeConteudoDaMarvel());

    private String url;
    private ExtratorDeConteudo extrator;

    API(String url, ExtratorDeConteudo extrator){
        this.url = url;
        this.extrator = extrator;
    }

    public String getUrl(){
        return this.url;
    }
    public ExtratorDeConteudo getExtrator(){
        return this.extrator;
    }
}
