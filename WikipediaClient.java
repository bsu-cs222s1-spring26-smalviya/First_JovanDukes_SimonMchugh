package Wikipedia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WikipediaClient {

    public String fetchRevisions(String articleTitle) throws IOException {

        String encodedTitle = URLEncoder.encode(articleTitle, StandardCharsets.UTF_8);

        String apiUrl =
                "https://en.wikipedia.org/w/api.php" +
                        "?action=query" +
                        "&format=json" +
                        "&prop=revisions" +
                        "&rvlimit=15" +
                        "&rvprop=timestamp|user" +
                        "&titles=" + encodedTitle +
                        "&redirects";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty(
                "User-Agent",
                "Revision Reporter/0.1 (yourbsuusername@bsu.edu)"
        );

        int status = connection.getResponseCode();

        if (status != 200) {
            throw new IOException("Network error");
        }

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        return response.toString();
    }
}
