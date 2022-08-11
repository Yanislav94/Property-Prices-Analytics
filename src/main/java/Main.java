import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {
    public static void main(String[] args) throws IOException {
        //URL url = new URL("https://www.mirela.bg/index.php?p=stats_list&price_type=1&type=1&etype=543&city_id=3&week=2022-08-08&month=2022-07-01");
        String url = "https://www.mirela.bg/index.php?p=stats_list&price_type=1&type=1&etype=543&city_id=3&week=2022-08-08&month=2022-07-01";

        Document doc = Jsoup.connect(url).get();
        Elements desktopOnly = doc.getElementsByClass("style4");

        Elements tableRow =  doc.getElementsByTag("tr");


        for (int i = 3; i < tableRow.size(); i++) {
            Element tr = tableRow.get(i);
            Elements td =  tr.getElementsByTag("td");
            System.out.printf("Квартал - %s%nСредна Цена - %s%n", td.get(0).text(), td.get(7).text());
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
