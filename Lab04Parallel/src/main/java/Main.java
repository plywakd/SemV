

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public static List<String> getHrefs(String randomWikiPage) throws Exception {
        String getHTMLResult = getHTML(randomWikiPage);
        //pattern to find groups of <a> tag where group 0 is whole, group 1 is first " sign and group 2 is href value
        Pattern p = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1");
        Matcher m = p.matcher(getHTMLResult);
        List<String> matches = new ArrayList<>();
        while(m.find()){
            matches.add(m.group(2));
        }
        return matches;
    }

    public static void main(String[] args) throws Exception
    {
        String randomWikipedia = "https://en.wikipedia.org/wiki/Special:Random";
        String toFindWikipedia = "https://en.wikipedia.org/wiki/Poland";
        String wikiHrefToFind = "/wiki/Poland";

        String parsed = "";
        while(parsed.equals("")){
            List<String> matches = getHrefs(randomWikipedia);
            System.out.println("Matches " + matches.toString());
            parsed = matches.stream().filter(matched->matched.equals(wikiHrefToFind)).findFirst().orElse("");
            System.out.println("Check stream "+parsed);
        }
        System.out.println("FOUND HREF: "+ parsed);


    }
}
