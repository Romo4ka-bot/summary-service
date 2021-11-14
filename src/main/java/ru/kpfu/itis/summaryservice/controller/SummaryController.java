package ru.kpfu.itis.summaryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.summaryservice.dto.SummaryDTO;
import ru.kpfu.itis.summaryservice.service.SummaryService;

/**
 * @author Roman Leontev
 * 20:44 10.11.2021
 * student ITIS
 */

@RestController
public class SummaryController {

    private final SummaryService summaryService;

    @Autowired
    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<SummaryDTO>> getAllSummaryPage(@PageableDefault(size = 7, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(summaryService.getAll(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<SummaryDTO> addSummary(@RequestBody SummaryDTO summaryDTO) {
        return ResponseEntity.ok(summaryService.addSummary(summaryDTO));
    }

    @PostMapping("/delete/{summaryId}")
    public void deleteSummary(@PathVariable("summaryId") Long summaryId) {
        summaryService.deleteById(summaryId);
    }

    @PostMapping("/{summaryId}")
    public ResponseEntity<SummaryDTO> updateSummary(@PathVariable("summaryId") Long summaryId, @RequestBody SummaryDTO summaryDTO) {

        summaryDTO.setId(summaryId);

        return ResponseEntity.ok(summaryService.updateSummary(summaryDTO));
    }

    @GetMapping("/{summaryId}")
    public ResponseEntity<SummaryDTO> getUpdateSummaryFormPage(@PathVariable("summaryId") Long summaryId) {
        return ResponseEntity.ok(summaryService.getById(summaryId));
    }
}
