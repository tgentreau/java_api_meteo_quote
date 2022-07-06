import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException, IOException {
        //instance class
        MeteoAPI meteoAPI = new MeteoAPI();
        QuotesAPI quotesAPI = new QuotesAPI();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez votre ville favorite");
        String res = scanner.nextLine();
        meteoAPI.init(res);
        quotesAPI.intAPIQuotes();
    }
}
