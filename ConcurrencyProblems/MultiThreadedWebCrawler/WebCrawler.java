package ConcurrencyProblems.MultiThreadedWebCrawler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import ConcurrencyProblems.MultiThreadedWebCrawler.Interfaces.HtmlParser;

/**
 * Multi-threaded Web Crawler implementation using thread-safe collections
 * and executor service for concurrent URL processing
 */
public class WebCrawler implements HtmlParser {

    // Thread-safe queue for BFS traversal of URLs
    private final ConcurrentLinkedQueue<Integer> queue;
    // Thread-safe map to track visited nodes
    private final ConcurrentHashMap<Integer, AtomicBoolean> visited;
    // Starting URL for crawling
    private final String startUrl;
    // List of all URLs in the graph
    private final List<String> urls;
    // Adjacency list representation of URL graph
    private final ConcurrentHashMap<Integer, List<Integer>> nodeMap;
    // Thread-safe result collection
    private final CopyOnWriteArrayList<String> result;
    // Cached domain for quick comparison
    private final String startDomain;

    /**
     * Constructor initializes thread-safe data structures and builds adjacency graph
     * @param nodes Total number of URLs
     * @param edges Array of directed edges between URLs
     * @param startUrl Starting URL for crawling
     * @param urls List of all URLs
     */
    WebCrawler(int nodes, int[][] edges, String startUrl, List<String> urls) {
        this.queue = new ConcurrentLinkedQueue<>();
        this.visited = new ConcurrentHashMap<>();
        this.nodeMap = new ConcurrentHashMap<>();
        this.startUrl = startUrl;
        this.urls = urls;
        this.result = new CopyOnWriteArrayList<>();
        this.startDomain = extractDomain(startUrl);

        // Build adjacency list from edges - optimized with computeIfAbsent
        for (int[] edge : edges) {
            nodeMap.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }

        // Initialize visited map with atomic booleans for thread safety
        for (int i = 0; i < nodes; i++) {
            visited.put(i, new AtomicBoolean(false));
        }
    }

    /**
     * Simulates fetching URLs from a given URL and adds unvisited neighbors to queue
     * @param url The URL to process
     * @return List of newly discovered URLs
     */
    public List<String> getUrls(String url) {
        List<String> urlsList = new ArrayList<>();
        try {
            int urlIdx = this.urls.indexOf(url);
            List<Integer> subnodes = nodeMap.get(urlIdx);
            
            if (subnodes != null) {
                subnodes.forEach(n -> {
                    // Use compareAndSet for atomic visited check and update
                    if (visited.get(n).compareAndSet(false, true)) {
                        queue.offer(n);
                        urlsList.add(this.urls.get(n));
                    }
                });
            }
            
            // Simulate network delay
            Thread.sleep(15);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlsList;
    }

    /**
     * Main crawling method using BFS with thread pool for concurrent processing
     * Only crawls URLs from the same domain as start URL
     */
    public void crawl() throws InterruptedException {
        int startNode = urls.indexOf(startUrl);
        queue.offer(startNode);
        visited.get(startNode).set(true);
        result.add(startUrl);

        ExecutorService executor = Executors.newFixedThreadPool(5);

        while (!queue.isEmpty()) {
            List<Future<List<String>>> futures = new ArrayList<>();
            
            // Process all current queue items in parallel
            while (!queue.isEmpty()) {
                int currentNode = queue.poll();
                String currentUrl = urls.get(currentNode);

                // Domain check optimization - avoid repeated string splitting
                if (isSameDomain(currentUrl)) {
                    futures.add(executor.submit(() -> getUrls(currentUrl)));
                }
            }
            
            // Collect results from all futures
            for (Future<List<String>> future : futures) {
                try {
                    result.addAll(future.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        
        System.out.println("Final crawled URLs: " + result);
    }
    
    /**
     * Optimized domain extraction
     */
    private String extractDomain(String url) {
        return url.split("/")[2];
    }
    
    /**
     * Optimized domain comparison using cached start domain
     */
    private boolean isSameDomain(String url) {
        return extractDomain(url).equals(startDomain);
    }

    public static void main(String[] args) throws InterruptedException {

        List<String> urls = List.of("http://news.yahoo.com",
                "http://news.yahoo.com/news",
                "http://news.yahoo.com/news/topics/",
                "http://news.google.com",
                "http://news.yahoo.com/us");

        int[][] edges = { { 2, 0 }, { 2, 1 }, { 3, 2 }, { 3, 1 }, { 0, 4 } };

        int nodes = urls.size();

        String startUrl = "http://news.yahoo.com/news/topics/";

        long startTime = System.currentTimeMillis();
        WebCrawler webCrawler = new WebCrawler(nodes, edges, startUrl, urls);
        webCrawler.crawl();
        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + " ms");

    }

}
