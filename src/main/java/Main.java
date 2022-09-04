import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        LocalDate today = LocalDate.parse("2006-01-02");
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);

        String url = "https://www.mirela.bg/index.php?p=stats_list&price_type=1&type=1&etype=543&city_id=3&week=" + nextWeek + "&month=2022-07-01";

        Document doc = Jsoup.connect(url).get();
        Elements getHtmlTables =  doc.getElementsByTag("tr");
        Map<String, String> map = new HashMap<>();
        List<PricePerDistrict> list = new ArrayList<>();


        for (int i = 3; i < getHtmlTables.size(); i++) {
            getHtmlTables =  doc.getElementsByTag("tr");
            Element tr = getHtmlTables.get(i);
            Elements td =  tr.getElementsByTag("td");

            String[] sumarr = td.get(7).text().split(" ");
            String sum = sumarr[1] + sumarr[2];
            int price = Integer.parseInt(sum);
            PricePerDistrict pricePerDistrict = new PricePerDistrict(price, td.get(0).text());
            list.add(pricePerDistrict);

        }

        for (PricePerDistrict perDistrict : list) {
            System.out.println(perDistrict.toString());
        }

    }

    static void httpRequest(URL url, HttpURLConnection con) throws IOException {
        con.setRequestMethod("POST");
        int status = con.getResponseCode();
    }

    static String readResponse(HttpURLConnection con) throws IOException {

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}
