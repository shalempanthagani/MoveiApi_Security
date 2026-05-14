package com.antra.movieapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

@Service
public class MovieService {

    private static final String BASE_URL =
        "https://jsonmock.hackerrank.com/api/moviesdata/search/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ExecutorService executorService;

    public List<Movie> getAllMovies()
            throws InterruptedException, ExecutionException {

        String url = BASE_URL + "?page=1";
        MovieApiResponse first = restTemplate.getForObject(
                url, MovieApiResponse.class);

        if (first == null || first.getData() == null)
            return Collections.emptyList();

        int totalPages = first.getTotal_pages();
        List<Movie> all = Collections.synchronizedList(
                new ArrayList<>(first.getData()));

        List<Callable<List<Movie>>> tasks = new ArrayList<>();
        for (int p = 2; p <= totalPages; p++) {
            int page = p;
            tasks.add(() -> {
                MovieApiResponse res = restTemplate.getForObject(
                        BASE_URL + "?page=" + page, MovieApiResponse.class);
                return (res != null && res.getData() != null)
                        ? res.getData() : Collections.emptyList();
            });
        }

        for (Future<List<Movie>> f : executorService.invokeAll(tasks))
            all.addAll(f.get());

        return all;
    }

    public MovieApiResponse searchMovies(Integer page) {
        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl(BASE_URL);
        builder.queryParam("page", page != null ? page : 1);
        return restTemplate.getForObject(
                builder.toUriString(), MovieApiResponse.class);
    }
}
