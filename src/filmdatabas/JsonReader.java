package filmdatabas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

    private static String readAll(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int temp;
        while ((temp = reader.read()) != -1) {
            sb.append((char) temp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream input = new URL(url).openStream();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
            String jsonData = readAll(br);
            JSONObject jo = new JSONObject(jsonData);
            return jo;
        } finally {
            input.close();
        }
    }
}