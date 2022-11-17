import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> linkList = new ArrayList<>();
        linkList.add("https://www.bbc.com/");
        linkList.add("https://www.google.com/");
        linkList.add("https://www.facebook.com/");

        linkList.stream().parallel().forEach(link -> System.out.println(getWebContent(link)));
    }

    private static String getWebContent(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String encoding = connection.getContentEncoding();

            InputStream inputStream = connection.getInputStream();
            String result = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining());
            //lines: to read each line that we are receiving
            //Joining(): to save all in a string
            return result;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "";
    }
}