import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            String apiKey = "ffbc55ace15edc5d6109da09";
            Scanner scanner = new Scanner(System.in);
            String asterisks = "*".repeat(40);
            System.out.println("*" + asterisks);
            //Para adicionar mais moedas a conversão, basta implemntar "menu" e relocar a opção "Sair".
            String menu = """
                                    
                    Bem-vindo! Este é um conversor de moedas.
                                    
                    1) Dólar => Peso argentino(USD/ARS)
                    2) Peso argentino => Dólar(ARS/USD)
                    3) Dólar => Real brasileiro(USD/BRL)
                    4) Real brasileiro => Dólar(BRL/USD)
                    5) Dólar => Peso colombiano(USD/COP)
                    6) Peso colombiano => Dólar(COP/USD)
                    7) Sair
                                    
                    Digite o número da opção desejada:
                    """;

            System.out.println(menu);
            System.out.println("*" + asterisks);
            int opcao = scanner.nextInt();

            if (opcao == 7) {
                System.out.println("Obrigado por usar o conversor de moedas!");
                return;
            }
            //Para trocar a moeda de base, alterar "baseCurrency" em "case";
            //Para ampliar o "menu", precisa adicionar opções em "case";
            String baseCurrency = ""; // Moeda base
            String targetCurrency = ""; // Moeda de destino

            switch (opcao) {
                case 1:
//                    baseCurrency = "USD";
                    baseCurrency = "USD";// Peso argentino para Dólar
                    targetCurrency = "ARS";
                    break;
                case 2:
                    baseCurrency = "ARS";// Dolar para Peso argentino
                    targetCurrency = "USD";
                    break;
                case 3:
                    baseCurrency = "USD";// Real brasileiro para Dólar
                    targetCurrency = "BRL";
                    break;
                case 4:
                    baseCurrency = "BRL";// Dolar para Real brasileiro
                    targetCurrency = "USD";
                    break;
                case 5:
                    baseCurrency = "USD";// Peso colombiano para Dólar
                    targetCurrency = "COP";
                    break;
                case 6:
                    baseCurrency = "COP";//Dólar para Peso colombiano
                    targetCurrency = "USD";
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    return;
            }

            //Para substituir o API, é obrigatório alterar "apiKey" e "String" e "Double", dentro da classe "Titulo"
            String apiUrl ="https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + baseCurrency+ "/" + targetCurrency ;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            //Para teste da resposta http, ativar o "println" abaixo;
//        System.out.println(response.statusCode());
            String json = response.body();
            //Para teste do retorno Json, ativar o "println" abaixo;
//        System.out.println(json);
            Gson gson = new Gson();

            CoinRate meuTitulo = gson.fromJson(json, CoinRate.class);

            Name meuNome = new Name(meuTitulo);
            System.out.println(meuNome);


        } catch (NumberFormatException e ) {
            System.out.println("Opção não encontrada!");

        } catch (IllegalArgumentException e){
            System.out.println("Opção não encontrada!");

        }catch (InputMismatchException e){
            System.out.println("Você digitou algum caracter inválido, tente novamente!");
        }
    }
}


