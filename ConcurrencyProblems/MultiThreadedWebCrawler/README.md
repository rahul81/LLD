# Multi-Threaded Web Crawler

## Problem Statement
Design a multi-threaded web crawler that can crawl URLs concurrently while ensuring thread safety and avoiding duplicate visits. The crawler should only crawl URLs within the same domain as the starting URL.

## Context
- **Input**: Starting URL, list of URLs, and directed edges representing links between URLs
- **Constraints**: 
  - Only crawl URLs from the same domain
  - Avoid visiting the same URL multiple times
  - Use multiple threads for concurrent processing
  - Ensure thread safety

## Solution Architecture

### Key Components
1. **Thread-Safe Data Structures**:
   - `ConcurrentLinkedQueue<Integer>` - BFS traversal queue
   - `ConcurrentHashMap<Integer, AtomicBoolean>` - Visited tracking
   - `CopyOnWriteArrayList<String>` - Result collection
   - `ConcurrentHashMap<Integer, List<Integer>>` - URL graph adjacency list

2. **Concurrency Management**:
   - `ExecutorService` with fixed thread pool (5 threads)
   - `Future<List<String>>` for async URL processing
   - `AtomicBoolean.compareAndSet()` for atomic visited checks

### Algorithm Flow
1. Initialize thread-safe collections and build URL graph
2. Add starting URL to queue and mark as visited
3. While queue is not empty:
   - Poll all current URLs from queue
   - Submit each URL for parallel processing (if same domain)
   - Each thread discovers new URLs and adds unvisited ones to queue
   - Collect all results from futures
4. Return all crawled URLs

### Optimizations
- **Domain Caching**: Pre-extract start domain to avoid repeated string splitting
- **Atomic Operations**: Use `compareAndSet()` for thread-safe visited updates
- **Batch Processing**: Process all queue items in parallel per iteration
- **Efficient Graph Building**: Use `computeIfAbsent()` for adjacency list construction

### Time Complexity
- **Sequential**: O(V + E) where V = URLs, E = edges
- **Parallel**: O((V + E) / T) where T = thread count (theoretical speedup)

### Example Usage
```java
List<String> urls = List.of(
    "http://news.yahoo.com",
    "http://news.yahoo.com/news", 
    "http://news.yahoo.com/news/topics/",
    "http://news.google.com",
    "http://news.yahoo.com/us"
);

int[][] edges = {{2,0},{2,1},{3,2},{3,1},{0,4}};
String startUrl = "http://news.yahoo.com/news/topics/";

WebCrawler crawler = new WebCrawler(urls.size(), edges, startUrl, urls);
crawler.crawl();
```

### Output
- Crawls only Yahoo News URLs (same domain)
- Skips Google URL (different domain)
- Returns: `[http://news.yahoo.com/news/topics/, http://news.yahoo.com, http://news.yahoo.com/news, http://news.yahoo.com/us]`