package ru.kpfu.itis.summaryservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Roman Leontev
 * 21:37 10.11.2021
 * student ITIS
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "summary")
public class Summary {

    @Id
    private Long id;

    @Field(value = "first_name")
    private String firstName;
    @Field(value = "last_name")
    private String lastName;

    @Field(value = "about_favourite_movie")
    private String aboutFavouriteMovie;
}
