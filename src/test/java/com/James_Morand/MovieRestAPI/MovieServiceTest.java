package com.James_Morand.MovieRestAPI;

import com.James_Morand.MovieRestAPI.Exception.ResourceNotFoundException;
import com.James_Morand.MovieRestAPI.Movie.Cast;
import com.James_Morand.MovieRestAPI.Movie.Movie;
import com.James_Morand.MovieRestAPI.Repository.MovieRepository;
import com.James_Morand.MovieRestAPI.Service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie(1, "Avengers: Age of Ultron", 141, "2015-05-01", 495.2, null);
    }


    @Test
    void getAllMovies_returnsList() {
        when(movieRepository.findAll()).thenReturn(List.of(movie));

        List<Movie> result = movieService.getAllMovies();

        assertThat(result).hasSize(1).contains(movie);
        verify(movieRepository).findAll();
    }

    @Test
    void getAllMovies_emptyList_returnsEmpty() {
        when(movieRepository.findAll()).thenReturn(List.of());

        assertThat(movieService.getAllMovies()).isEmpty();
    }


    @Test
    void getMovie_found_returnsMovie() {
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        Movie result = movieService.getMovie(1);

        assertThat(result).isEqualTo(movie);
    }

    @Test
    void getMovie_notFound_throwsResourceNotFoundException() {
        when(movieRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> movieService.getMovie(99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void addMovie_resetsIdAndSaves() {
        Movie input = new Movie(99, "New Movie", 120, "2024-01-01", 100.0, null);
        Movie saved = new Movie(1,  "New Movie", 120, "2024-01-01", 100.0, null);
        when(movieRepository.save(any(Movie.class))).thenReturn(saved);

        Movie result = movieService.addMovie(input);

        assertThat(input.getId()).isEqualTo(0); // id must be reset before save
        assertThat(result).isEqualTo(saved);
        verify(movieRepository).save(input);
    }

    @Test
    void updateMovie_found_updatesFields() {
        Movie updated = new Movie(0, "Updated Name", 200, "2025-01-01", 999.9, null);
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        when(movieRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Movie result = movieService.updateMovie(1, updated);

        assertThat(result.getName()).isEqualTo("Updated Name");
        assertThat(result.getMovieLength()).isEqualTo(200);
        assertThat(result.getReleaseDate()).isEqualTo("2025-01-01");
        assertThat(result.getCostToMake()).isEqualTo(999.9);
    }

    @Test
    void updateMovie_notFound_throwsResourceNotFoundException() {
        when(movieRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> movieService.updateMovie(99, movie))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void deleteMovie_found_deletesMovie() {
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        movieService.deleteMovie(1);

        verify(movieRepository).delete(movie);
    }

    @Test
    void deleteMovie_notFound_throwsResourceNotFoundException() {
        when(movieRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> movieService.deleteMovie(99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");

        verify(movieRepository, never()).delete(any());
    }
}
