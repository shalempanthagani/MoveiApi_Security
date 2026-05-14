package com.antra.movieapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/movies")
    public ResponseEntity<?> getAllMovies() {
        try {
            List<Movie> movies = movieService.getAllMovies();
            return ResponseEntity.ok(Map.of(
                    "total", movies.size(),
                    "data",  movies));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/api/movies/", params = "page")
    public ResponseEntity<?> searchMovies(@RequestParam Integer page) {
        MovieApiResponse response = movieService.searchMovies(page);

        if (response == null)
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "failed to get the data"));

        return ResponseEntity.ok(Map.of(
                "page",       response.getPage(),
                "totalPages", response.getTotal_pages(),
                "count",      response.getData() != null ? response.getData().size() : 0,
                "data",       response.getData()));
    }
}
