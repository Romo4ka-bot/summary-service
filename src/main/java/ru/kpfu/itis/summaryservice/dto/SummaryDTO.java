package ru.kpfu.itis.summaryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Roman Leontev
 * 22:12 10.11.2021
 * student ITIS
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SummaryDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String aboutFavouriteMovie;
}
