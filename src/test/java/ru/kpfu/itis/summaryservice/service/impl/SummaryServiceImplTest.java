package ru.kpfu.itis.summaryservice.service.impl;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kpfu.itis.summaryservice.dto.SummaryDTO;
import ru.kpfu.itis.summaryservice.entity.Summary;
import ru.kpfu.itis.summaryservice.repository.SummaryRepository;
import ru.kpfu.itis.summaryservice.service.CustomSequencesService;
import ru.kpfu.itis.summaryservice.service.SummaryService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SummaryServiceImpl.class)
@SpringBootTest
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("SummaryServiceImpl is working when")
class SummaryServiceImplTest {

    @Autowired
    private SummaryService summaryService;

    @MockBean
    private SummaryRepository summaryRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private CustomSequencesService customSequencesService;

    @DisplayName("getAll() is working")
    @Nested
    class ForGetAll {
        private Summary summary1 = Summary.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .aboutFavouriteMovie("My favourite movie is 'MIB'")
                .build();

        private Summary summary2 = Summary.builder()
                .id(2L)
                .firstName("Egor")
                .lastName("Kreed")
                .aboutFavouriteMovie("My favourite movie is 'Seven Pounds'")
                .build();

        private SummaryDTO summaryDTO1 = SummaryDTO.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .aboutFavouriteMovie("My favourite movie is 'MIB'")
                .build();

        private SummaryDTO summaryDTO2 = SummaryDTO.builder()
                .id(2L)
                .firstName("Egor")
                .lastName("Kreed")
                .aboutFavouriteMovie("My favourite movie is 'Seven Pounds'")
                .build();

        @BeforeEach
        void setUp() {
            Mockito.when(modelMapper.map(summary1, SummaryDTO.class)).thenReturn(summaryDTO1);
            Mockito.when(modelMapper.map(summary2, SummaryDTO.class)).thenReturn(summaryDTO2);
        }

        @Test
        void on_positive_range_return_page_of_SummaryDTO() {
            Pageable earlySummariesPageable = PageRequest.of(0, 2, Sort.Direction.ASC, "id");

            List<Summary> earlySummaries = Arrays.asList(
                    summary1,
                    summary2
            );

            Page<Summary> earlySummariesPage = new PageImpl(earlySummaries, earlySummariesPageable, earlySummaries.size());

            List<SummaryDTO> earlySummaryDTOs = Arrays.asList(
                    summaryDTO1,
                    summaryDTO2
            );

            Page<SummaryDTO> earlySummaryDTOsPage = new PageImpl(earlySummaryDTOs, earlySummariesPageable, earlySummaryDTOs.size());

            Mockito.when(summaryRepository.findAll(earlySummariesPageable)).thenReturn(earlySummariesPage);

            assertNotNull(summaryService.getAll(earlySummariesPageable));
            assertEquals(earlySummaryDTOsPage, summaryService.getAll(earlySummariesPageable));

            Mockito.verify(summaryRepository, times(2)).findAll(earlySummariesPageable);
            Mockito.verify(modelMapper, times(2)).map(summary1, SummaryDTO.class);
            Mockito.verify(modelMapper, times(2)).map(summary2, SummaryDTO.class);
        }

        @Test
        void on_negative_range_return_page_of_SummaryDTO() {
            Pageable earlySummariesPageable = PageRequest.of(10, 2, Sort.Direction.ASC, "id");

            List<Summary> earlySummaries = new ArrayList<>();

            Page<Summary> earlySummariesPage = new PageImpl(earlySummaries, earlySummariesPageable, earlySummaries.size());

            List<SummaryDTO> earlySummaryDTOs = new ArrayList<>();

            Page<SummaryDTO> earlySummaryDTOsPage = new PageImpl(earlySummaryDTOs, earlySummariesPageable, earlySummaryDTOs.size());

            Mockito.when(summaryRepository.findAll(earlySummariesPageable)).thenReturn(earlySummariesPage);

            assertNotNull(summaryService.getAll(earlySummariesPageable));
            assertEquals(earlySummaryDTOsPage, summaryService.getAll(earlySummariesPageable));

            Mockito.verify(summaryRepository, times(2)).findAll(earlySummariesPageable);
            Mockito.verify(modelMapper, times(0)).map(summary1, SummaryDTO.class);
            Mockito.verify(modelMapper, times(0)).map(summary2, SummaryDTO.class);
        }
    }

    @DisplayName("addSummary() is working")
    @Nested
    class ForAddSummary {

        private Summary summary = Summary.builder()
                .id(2L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .aboutFavouriteMovie("My favourite movie is 'MIB'")
                .build();

        private SummaryDTO summaryDTO = SummaryDTO.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .aboutFavouriteMovie("My favourite movie is 'MIB'")
                .build();

        @BeforeEach
        void setUp() {
            Mockito.when(modelMapper.map(summaryDTO, Summary.class)).thenReturn(summary);
            Mockito.when(customSequencesService.getNextSequence(Summary.class.getSimpleName())).thenReturn(2L);
            Mockito.when(summaryRepository.save(summary)).thenReturn(summary);
        }

        @Test
        void on_not_null_summaryDTO_return_summaryDTO_with_id() {

            SummaryDTO summaryDTOTest = SummaryDTO.builder()
                    .id(2L)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .aboutFavouriteMovie("My favourite movie is 'MIB'")
                    .build();

            Mockito.when(modelMapper.map(summary, SummaryDTO.class)).thenReturn(summaryDTOTest);

            assertNotNull(summaryService.addSummary(summaryDTO));
            assertEquals(summaryDTOTest, summaryService.addSummary(summaryDTO));
        }
    }


    @DisplayName("getById() is working")
    @Nested
    class ForGetById {

        private Summary summary = Summary.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .aboutFavouriteMovie("My favourite movie is 'MIB'")
                .build();

        private SummaryDTO summaryDTO = SummaryDTO.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .aboutFavouriteMovie("My favourite movie is 'MIB'")
                .build();

        @Test
        void on_not_null_summaryId_return_summaryDTO() {

            Long id = 1L;

            Mockito.when(modelMapper.map(summary, SummaryDTO.class)).thenReturn(summaryDTO);
            Mockito.when(summaryRepository.findById(id)).thenReturn(Optional.of(summary));


            assertNotNull(summaryService.getById(id));
            assertEquals(summaryDTO, summaryService.getById(id));
        }

        @Test
        void on_null_summaryId_throw_IllegalArgumentException() {

            Long id = null;

            Mockito.when(summaryRepository.findById(id)).thenReturn(Optional.empty());

            assertThrows(IllegalArgumentException.class, () -> summaryService.getById(id));
        }
    }
}