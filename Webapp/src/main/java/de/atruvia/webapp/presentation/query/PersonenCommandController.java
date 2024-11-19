package de.atruvia.webapp.presentation.query;


import de.atruvia.webapp.presentation.dto.PersonDto;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
public class PersonenCommandController {

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if(id.toString().endsWith("7"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void>  anlegen(@Valid @RequestBody PersonDto personDto, UriComponentsBuilder builder) {

        // Person speichern
        UriComponents uriComponents = builder.path("/v1/personen/{id}").buildAndExpand(personDto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<Void> aendern(@PathVariable UUID id, @Valid @RequestBody PersonDto personDto) {

        if(! id.equals( personDto.getId())) throw new IllegalArgumentException("Upps");
        // Person update
        if(id.toString().endsWith("7"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().build();
    }

}
