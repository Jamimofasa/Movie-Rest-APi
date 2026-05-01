package com.James_Morand.MovieRestAPI;

import com.James_Morand.MovieRestAPI.Exception.DuplicateResourceException;
import com.James_Morand.MovieRestAPI.Exception.ResourceNotFoundException;
import com.James_Morand.MovieRestAPI.Movie.Actor;
import com.James_Morand.MovieRestAPI.Repository.ActorRepository;
import com.James_Morand.MovieRestAPI.Service.ActorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorService actorService;

    private Actor actor;

    @BeforeEach
    void setUp() {
        actor = new Actor(1, "Robert Downey Jr.", 58, new ArrayList<>());
    }


    @Test
    void getAllActors_returnsList() {
        when(actorRepository.findAll()).thenReturn(List.of(actor));

        List<Actor> result = actorService.getAllActors();

        assertThat(result).hasSize(1).contains(actor);
        verify(actorRepository).findAll();
    }

    @Test
    void getAllActors_empty_returnsEmpty() {
        when(actorRepository.findAll()).thenReturn(List.of());

        assertThat(actorService.getAllActors()).isEmpty();
    }


    @Test
    void getActor_found_returnsActor() {
        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));

        Actor result = actorService.getActor(1);

        assertThat(result).isEqualTo(actor);
    }

    @Test
    void getActor_notFound_throwsResourceNotFoundException() {
        when(actorRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> actorService.getActor(99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }


    @Test
    void addActor_newName_savesAndReturns() {
        when(actorRepository.existsByNameIgnoreCase("Robert Downey Jr.")).thenReturn(false);
        when(actorRepository.save(actor)).thenReturn(actor);

        Actor result = actorService.addActor(actor);

        assertThat(result).isEqualTo(actor);
        assertThat(actor.getId()).isEqualTo(0); // id must be reset before save
        verify(actorRepository).save(actor);
    }

    @Test
    void addActor_duplicateName_throwsDuplicateResourceException() {
        when(actorRepository.existsByNameIgnoreCase("Robert Downey Jr.")).thenReturn(true);

        assertThatThrownBy(() -> actorService.addActor(actor))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("Robert Downey Jr.");

        verify(actorRepository, never()).save(any());
    }

    @Test
    void addActor_duplicateNameDifferentCase_throwsDuplicateResourceException() {
        Actor lowerCase = new Actor(0, "robert downey jr.", 58, new ArrayList<>());
        when(actorRepository.existsByNameIgnoreCase("robert downey jr.")).thenReturn(true);

        assertThatThrownBy(() -> actorService.addActor(lowerCase))
                .isInstanceOf(DuplicateResourceException.class);

        verify(actorRepository, never()).save(any());
    }


    @Test
    void updateActor_found_updatesFields() {
        Actor updated = new Actor(0, "Tony Stark", 45, new ArrayList<>());
        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));
        when(actorRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Actor result = actorService.updateActor(1, updated);

        assertThat(result.getName()).isEqualTo("Tony Stark");
        assertThat(result.getAge()).isEqualTo(45);
    }

    @Test
    void updateActor_notFound_throwsResourceNotFoundException() {
        when(actorRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> actorService.updateActor(99, actor))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }


    @Test
    void deleteActor_found_deletesActor() {
        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));

        actorService.deleteActor(1);

        verify(actorRepository).delete(actor);
    }

    @Test
    void deleteActor_notFound_throwsResourceNotFoundException() {
        when(actorRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> actorService.deleteActor(99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");

        verify(actorRepository, never()).delete(any());
    }

    @Test
    void searchByName_returnsMatchingActors() {
        when(actorRepository.findByNameContainingIgnoreCase("robert"))
                .thenReturn(List.of(actor));

        List<Actor> result = actorService.searchByName("robert");

        assertThat(result).hasSize(1).contains(actor);
    }

    @Test
    void searchByName_noMatch_returnsEmpty() {
        when(actorRepository.findByNameContainingIgnoreCase("xyz")).thenReturn(List.of());

        assertThat(actorService.searchByName("xyz")).isEmpty();
    }
}
