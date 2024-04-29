import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;


public  class Name {
    @SerializedName("base_code")
    private String dolar;
    @SerializedName("target_code")
    private String moedas;
    @SerializedName("conversion_rate")
    private Double cotacao;

    public Name(String dolar, String moedas, Double cotacao) {
        this.dolar = dolar;
        this.moedas = moedas;
        this.cotacao = cotacao;

    }

    public Name(CoinRate meuTitulo) {
        this.dolar = meuTitulo.base_code();
        this.moedas = meuTitulo.target_code();
        this.cotacao = Double.valueOf(meuTitulo.conversion_rate());

    }

    @Override
    public String toString() {
        //Para ampliar a abrangência decimal, acrescentar "#":
        DecimalFormat df = new DecimalFormat("####.#####");// Quatro casas decimais
        String cotacaoFormatada = df.format(cotacao);
        return "Converção de " + dolar + " para " + moedas + " = Cotação do dia: " + cotacaoFormatada;
    }

}


