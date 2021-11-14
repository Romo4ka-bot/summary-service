package ru.kpfu.itis.summaryservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.summaryservice.dto.SummaryDTO;
import ru.kpfu.itis.summaryservice.entity.Summary;
import ru.kpfu.itis.summaryservice.repository.SummaryRepository;
import ru.kpfu.itis.summaryservice.service.CustomSequencesService;
import ru.kpfu.itis.summaryservice.service.SummaryService;

/**
 * @author Roman Leontev
 * 22:21 10.11.2021
 * student ITIS
 */

@Service
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository summaryRepository;

    private final ModelMapper modelMapper;

    private final CustomSequencesService customSequencesService;

    @Autowired
    public SummaryServiceImpl(SummaryRepository summaryRepository, ModelMapper modelMapper, CustomSequencesService customSequencesService) {
        this.summaryRepository = summaryRepository;
        this.modelMapper = modelMapper;
        this.customSequencesService = customSequencesService;
    }

    @Override
    public Page<SummaryDTO> getAll(Pageable pageable) {
        return summaryRepository.findAll(pageable)
                .map(summary -> modelMapper.map(summary, SummaryDTO.class));
    }

    @Override
    public SummaryDTO addSummary(SummaryDTO summaryDTO) {
        Summary summary = modelMapper.map(summaryDTO, Summary.class);
        Long id = customSequencesService.getNextSequence(Summary.class.getSimpleName());
        summary.setId(id);
        return modelMapper.map(summaryRepository.save(summary), SummaryDTO.class);
    }

    @Override
    public void deleteById(Long summaryId) {
        summaryRepository.deleteById(summaryId);
    }

    @Override
    public SummaryDTO updateSummary(SummaryDTO summaryDTO) {
        Summary summary = modelMapper.map(summaryDTO, Summary.class);
        return modelMapper.map(summaryRepository.save(summary), SummaryDTO.class);
    }

    @Override
    public SummaryDTO getById(Long summaryId) {
        return modelMapper.map(summaryRepository.findById(summaryId)
                .orElseThrow(IllegalArgumentException::new), SummaryDTO.class);
    }
}