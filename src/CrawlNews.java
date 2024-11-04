import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

public class CrawlNews {
	public static void main(String[] args) throws IOException {
		try {
			URI uri = new URI("https://dantri.com.vn/the-gioi.htm");
			URL url = uri.toURL();
			Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
			scanner.useDelimiter("\\Z");
			String content = scanner.next();
			scanner.close();

//			URL url = new URL("https://dantri.com.vn/the-gioi.htm");
//			// Add SSL handling
//			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//			// Add User-Agent to mimic a browser
//			connection.setRequestProperty("User-Agent", "Mozilla/5.0");
//			// Set timeouts
//			connection.setConnectTimeout(5000);
//			connection.setReadTimeout(5000);

//			Scanner scanner = new Scanner(new InputStreamReader(connection.getInputStream(), "UTF-8"));
//			scanner.useDelimiter("\\Z");
//			String content = scanner.next();
//			scanner.close();

			content = content.replaceAll("\\n+", "");
			Pattern p = Pattern.compile("<a class=\"dt-text-black-mine\" href=\"/the-gioi/(.*?)\">(.*?)</a>");
			Matcher m = p.matcher(content);
			while (m.find()) {
				System.out.println(m.group(2));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
}
