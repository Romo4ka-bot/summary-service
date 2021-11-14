package ru.kpfu.itis.summaryservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.kpfu.itis.summaryservice.entity.Summary;

/**
 * @author Roman Leontev
 * 21:59 10.11.2021
 * student ITIS
 */

public interface SummaryRepository extends MongoRepository<Summary, Long> {

    Page<Summary> findAll(Pageable pageable);
}
