import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScraperKeyValue {
    public static void main(String[] args) {
        String url = "http://books.toscrape.com/";
        String fileName = "products.csv"; // using .txt because format is not standard CSV

        try {
            // Fetch HTML
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String html = response.body();

            // File writer
            PrintWriter pw = new PrintWriter(new FileWriter(fileName));

            // Extract products
            Pattern productPattern = Pattern.compile("<article class=\"product_pod\">(.*?)</article>", Pattern.DOTALL);
            Matcher productMatcher = productPattern.matcher(html);

            while (productMatcher.find()) {
                String productBlock = productMatcher.group(1);

                // Title
                Pattern titlePattern = Pattern.compile("title=\"(.*?)\"");
                Matcher titleMatcher = titlePattern.matcher(productBlock);
                String title = titleMatcher.find() ? titleMatcher.group(1) : "";

                // Price
                Pattern pricePattern = Pattern.compile("<p class=\"price_color\">(.*?)</p>");
                Matcher priceMatcher = pricePattern.matcher(productBlock);
                String price = priceMatcher.find() ? priceMatcher.group(1) : "";

                // Availability
                Pattern availPattern = Pattern.compile("<p class=\"instock availability\">\\s*(.*?)\\s*</p>", Pattern.DOTALL);
                Matcher availMatcher = availPattern.matcher(productBlock);
                String availability = availMatcher.find() ? availMatcher.group(1) : "";
                availability = availability.replaceAll("<.*?>", "").trim();

                // Write in key -> value format
                pw.println("\"Title\" -> " + title);
                pw.println("\"Price\" -> " + price);
                pw.println("\"Availability\" -> " + availability);
                pw.println(); // empty line between products
            }

            pw.close();
            System.out.println("Data saved in key -> value format in " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
