package ru.kpfu.itis.summaryservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.kpfu.itis.summaryservice.dto.SummaryDTO;

/**
 * @author Roman Leontev
 * 22:10 10.11.2021
 * student ITIS
 */

public interface SummaryService {

    Page<SummaryDTO> getAll(Pageable pageable);

    SummaryDTO addSummary(SummaryDTO summaryDTO);

    void deleteById(Long summaryId);

    SummaryDTO updateSummary(SummaryDTO summaryDTO);

    SummaryDTO getById(Long summaryId);
}
