package ru.kpfu.itis.summaryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Roman Leontev
 * 22:29 11.11.2021
 * student ITIS
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "custom_sequences")
public class CustomSequences {
    @Id
    private String id;
    private Long seq;
}
