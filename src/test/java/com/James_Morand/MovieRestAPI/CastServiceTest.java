package com.James_Morand.MovieRestAPI;

import com.James_Morand.MovieRestAPI.Exception.DuplicateResourceException;
import com.James_Morand.MovieRestAPI.Exception.ResourceNotFoundException;
import com.James_Morand.MovieRestAPI.Movie.Actor;
import com.James_Morand.MovieRestAPI.Movie.Cast;
import com.James_Morand.MovieRestAPI.Repository.ActorRepository;
import com.James_Morand.MovieRestAPI.Repository.CastRepository;
import com.James_Morand.MovieRestAPI.Service.CastService;
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
class CastServiceTest {

    @Mock
    private CastRepository castRepository;

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private CastService castService;

    private Cast cast;
    private Actor actor;

    @BeforeEach
    void setUp() {
        actor = new Actor(1, "Robert Downey Jr.", 58, new ArrayList<>());
        cast  = new Cast(1, new ArrayList<>(), null);
    }


    @Test
    void getAllCasts_returnsList() {
        when(castRepository.findAll()).thenReturn(List.of(cast));

        List<Cast> result = castService.getAllCast();

        assertThat(result).hasSize(1).contains(cast);
        verify(castRepository).findAll();
    }


    @Test
    void getCast_found_returnsCast() {
        when(castRepository.findById(1)).thenReturn(Optional.of(cast));

        Cast result = castService.getCast(1);

        assertThat(result).isEqualTo(cast);
    }

    @Test
    void getCast_notFound_throwsResourceNotFoundException() {
        when(castRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> castService.getCast(99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }


    @Test
    void addCast_resetsIdAndSaves() {
        Cast input = new Cast(99, new ArrayList<>(), null);
        Cast saved = new Cast(1,  new ArrayList<>(), null);
        when(castRepository.save(any())).thenReturn(saved);

        Cast result = castService.addCast(input);

        assertThat(input.getId()).isEqualTo(0);
        assertThat(result).isEqualTo(saved);
    }


    @Test
    void updateCast_found_updatesActorList() {
        Actor newActor = new Actor(2, "Chris Evans", 42, new ArrayList<>());
        Cast updated   = new Cast(0, new ArrayList<>(List.of(newActor)), null);

        when(castRepository.findById(1)).thenReturn(Optional.of(cast));
        when(castRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Cast result = castService.updateCast(1, updated);

        assertThat(result.getActors()).containsExactly(newActor);
    }

    @Test
    void updateCast_notFound_throwsResourceNotFoundException() {
        when(castRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> castService.updateCast(99, cast))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }


    @Test
    void deleteCast_found_deletesCast() {
        when(castRepository.findById(1)).thenReturn(Optional.of(cast));

        castService.deleteCast(1);

        verify(castRepository).delete(cast);
    }

    @Test
    void deleteCast_notFound_throwsResourceNotFoundException() {
        when(castRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> castService.deleteCast(99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");

        verify(castRepository, never()).delete(any());
    }

    // ── addActorToCast ────────────────────────────────────────────────────────

    @Test
    void addActorToCast_newActor_addsAndSaves() {
        when(castRepository.findById(1)).thenReturn(Optional.of(cast));
        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));
        when(castRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Cast result = castService.addActorToCast(1, 1);

        assertThat(result.getActors()).containsExactly(actor);
        verify(castRepository).save(cast);
    }

    @Test
    void addActorToCast_actorAlreadyInCast_throwsDuplicateResourceException() {
        cast.getActors().add(actor); // actor already in cast
        when(castRepository.findById(1)).thenReturn(Optional.of(cast));
        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));

        assertThatThrownBy(() -> castService.addActorToCast(1, 1))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("Robert Downey Jr.")
                .hasMessageContaining("cast id: 1");

        verify(castRepository, never()).save(any());
    }

    @Test
    void addActorToCast_castNotFound_throwsResourceNotFoundException() {
        when(castRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> castService.addActorToCast(99, 1))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void addActorToCast_actorNotFound_throwsResourceNotFoundException() {
        when(castRepository.findById(1)).thenReturn(Optional.of(cast));
        when(actorRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> castService.addActorToCast(1, 99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    // ── removeActorFromCast ───────────────────────────────────────────────────

    @Test
    void removeActorFromCast_actorInCast_removesAndSaves() {
        cast.getActors().add(actor);
        when(castRepository.findById(1)).thenReturn(Optional.of(cast));
        when(actorRepository.findById(1)).thenReturn(Optional.of(actor));
        when(castRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Cast result = castService.removeActorFromCast(1, 1);

        assertThat(result.getActors()).doesNotContain(actor);
        verify(castRepository).save(cast);
    }

    @Test
    void removeActorFromCast_castNotFound_throwsResourceNotFoundException() {
        when(castRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> castService.removeActorFromCast(99, 1))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    @Test
    void removeActorFromCast_actorNotFound_throwsResourceNotFoundException() {
        when(castRepository.findById(1)).thenReturn(Optional.of(cast));
        when(actorRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> castService.removeActorFromCast(1, 99))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }
}
