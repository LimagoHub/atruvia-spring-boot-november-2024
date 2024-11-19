package de.atruvia.webapp.demo;


import de.atruvia.webapp.persistence.PersonRepository;
import de.atruvia.webapp.persistence.entity.PersonEntity;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DBDemo {

    private final PersonRepository personRepository;

    @PostConstruct
    public void init() {
        personRepository.fritz().forEach(System.out::println);
    }
}
