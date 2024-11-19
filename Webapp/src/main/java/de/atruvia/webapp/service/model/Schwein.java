package de.atruvia.webapp.service.model;

import jakarta.persistence.Column;
import lombok.*;

import java.util.UUID;

@Data
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schwein {

    private UUID id;
    private String name;
    private int gewicht;

    public void fuettern() {
        setGewicht(getGewicht() +1);
    }
}
