package ru.kpfu.itis.summaryservice.service;

/**
 * @author Roman Leontev
 * 22:41 11.11.2021
 * student ITIS
 */

public interface CustomSequencesService {
    Long getNextSequence(String seqName);
}
