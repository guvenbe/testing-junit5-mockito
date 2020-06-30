package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @DisplayName("Test Find all")
    @Test
    void findAll() {
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> foundList= service.findAll();
        verify(visitRepository).findAll();
        assertThat(foundList).isNotNull();
        assertThat(foundList).hasSize(1);
    }

    @Test
    void findById() {
        Visit visit = new Visit();
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit found = service.findById(1L);
        verify(visitRepository).findById(anyLong());
        assertThat(found).isNotNull();
    }

    @Test
    void save() {
        Visit visit = new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit= service.save(visit);

        verify(visitRepository, times(1)).save(any(Visit.class));
        assertThat(savedVisit).isNotNull();
    }

    @Test
    void delete() {
        Visit visit = new Visit();
        service.delete(visit);
        verify(visitRepository, times(1)).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository, times(1)).deleteById(anyLong());
    }


}