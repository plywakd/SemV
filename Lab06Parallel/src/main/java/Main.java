

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    public static String randomWikipedia = "https://en.wikipedia.org/wiki/Special:Random";
    public static String toFindWikipedia = "https://en.wikipedia.org/wiki/Poland";
    //\/wiki\/(.*\w)
    public static String wikiHrefToFind = "/Poland";
    public static String wikiHeader = "https://en.wikipedia.org/wiki";
    public static String foundLink = "";
    public static Set<String> newHrefs;
    public static Set<String> subHrefs = new HashSet<>();
    public static int rxLevels = 0;
    public static Instant start;

    public static String getHTML(String urlToRead) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception err) {
            System.out.println("error occured in getting html:" + err);
            result.append("");
        }
        return result.toString();
    }

    public static Set<String> getHrefs(String WikiPage) {
        String getHTMLResult = getHTML(WikiPage);
        //pattern to find groups of <a> tag where group 0 is whole, group 1 is first " sign and group 2 is href value
        Pattern p = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=([\"'])\\/wiki(.*?)\\1");
        Matcher m = p.matcher(getHTMLResult);
        Set<String> matches = new HashSet<>();
        while (m.find()) {
            matches.add(m.group(2));
        }
        return matches;
    }

    public static void lookHref(Set<String> hrefs) throws Exception {
        List<CompletableFuture<Boolean>> cfParseHref = hrefs.stream()
                .map(s -> CompletableFuture.supplyAsync(
                        () -> (s).equals(wikiHrefToFind)))
                .collect(Collectors.toList());

        CompletableFuture<List<Boolean>> results = CompletableFuture
                .allOf(cfParseHref.toArray(new CompletableFuture[0]))
                .thenApply(r -> cfParseHref.stream().map(CompletableFuture::join).collect(Collectors.toList()));

        Optional result = results.get().stream().filter(r -> r == true).findFirst();
        if (result.isPresent()) {
            foundLink = "found";
        } else {
            List<CompletableFuture<Set<String>>> cfGetSubHrefs = hrefs.stream()
                    .map(h -> CompletableFuture.supplyAsync(
                            () -> getHrefs(wikiHeader + h)
                    )).collect(Collectors.toList());

            CompletableFuture<List<Set<String>>> subHrefsResults = CompletableFuture
                    .allOf(cfGetSubHrefs.toArray(new CompletableFuture[0]))
                    .thenApply(s -> cfGetSubHrefs.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()));

            subHrefsResults.get().stream()
                    .filter(s -> !s.isEmpty() && !s.contains(".") && !s.contains(":") && !s.contains("&"))
                    .forEach(s -> subHrefs.addAll(s));
        }
    }

    public static void lookHrefRX(Set<String> hrefs) {
        rxLevels++;
        List<Observable<Boolean>> observers = hrefs.stream()
                .map(h -> Observable.fromCallable(
                        () -> (h).equals(wikiHrefToFind)))
                .collect(Collectors.toList());
        Observable<Boolean> result = Observable.zip(observers, objects -> Arrays.stream(objects)
                .collect(Collectors.toList()))
                .flatMap(res -> Observable.just(res.stream()
                        .anyMatch(b -> (boolean) b)));
        result.filter(b -> b)
                .subscribeOn(Schedulers.computation())
                .subscribe(b -> {
                    foundLink = "found";
                    Instant finish = Instant.now();
                    long timeElapsed = Duration.between(start, finish).getSeconds();
                    System.out.println("FOUND HREF: " + foundLink + " LEVELS : " + rxLevels + " TIME TAKEN : " + timeElapsed + "s");
                });
        result.filter(b -> !b).subscribeOn(Schedulers.computation()).subscribe(b -> {
            List<Observable<Set<String>>> subHrefsObservers = hrefs.stream()
                    .map(s -> Observable.just(getHrefs(wikiHeader + s))
                            .subscribeOn(Schedulers.computation()))
                    .collect(Collectors.toList());
            Observable.zip(subHrefsObservers, objects -> Arrays.stream(objects)
                    .collect(Collectors.toList())).map(res -> res.stream()
                    .map(s -> (Set<String>) s)
                    .reduce((s1, s2) -> {
                        Set<String> newSet = new HashSet<>();
                        newSet.addAll(s1);
                        newSet.addAll(s2);
                        return newSet;
                    })).subscribe(s -> s.ifPresent(s2 -> lookHrefRX(s2)));
        });
    }

    public static void main(String[] args) throws Exception {
//        int levels = 0;
        start = Instant.now();
        Set<String> hrefs = getHrefs(randomWikipedia);
        lookHrefRX(hrefs);
//        System.out.println("Links on wiki " + hrefs.toString());
//        foundLink = hrefs.stream().filter(link -> link.equals(wikiHrefToFind)).findFirst().orElse("");
        while (foundLink.equals("")) {
//            System.out.println("LEVEL: " + levels);
//            lookHref(hrefs);
//            levels++;
//            hrefs = new HashSet<>(subHrefs);
        }
//        Instant finish = Instant.now();
//        long timeElapsed = Duration.between(start, finish).getSeconds();
//        System.out.println("FOUND HREF: " + foundLink + " LEVELS : " + levels + " TIME TAKEN : " + timeElapsed + "s");


    }


}
