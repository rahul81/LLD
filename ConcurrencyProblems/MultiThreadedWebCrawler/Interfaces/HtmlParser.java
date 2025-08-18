
package ConcurrencyProblems.MultiThreadedWebCrawler.Interfaces;

import java.util.List;

public interface HtmlParser {

    public List<String> getUrls(String url);
}