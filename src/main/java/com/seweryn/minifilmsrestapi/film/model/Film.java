package com.seweryn.minifilmsrestapi.film.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("films")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Film {
    @Id
    private String id;
    private String film_name;
    private String film_type;
    private String film_year;
    private String film_link;
}
