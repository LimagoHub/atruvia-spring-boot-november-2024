package de.atruvia.webapp.presentation.query;

import de.atruvia.webapp.presentation.PersonDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/personen")
public class PersonQueryController {


    @Operation(summary = "Liefert eine Person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person gefunden",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDto.class)) }),
            @ApiResponse(responseCode = "400", description = "ungueltige ID",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person nicht gefunden",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "internal server error",
                    content = @Content)})


    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<PersonDto> gibMichPerson(@PathVariable UUID id) {
        if(id.toString().endsWith("7"))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(PersonDto.builder().id(id).vorname("John").nachname("doe").build());
    }

    @GetMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<PersonDto>> gibMichAlle(
            @RequestParam(required = false, defaultValue = "") String vorname,
            @RequestParam(required = false, defaultValue = "") String name
    ) {

        System.out.printf("Vorname = '%s', Nachname = '%s'\n", vorname, name);
        var list = List.of(
                PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("Doe").build()
                , PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("Wayne").build()
                , PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("Rambo").build()
                , PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("McClaine").build()
                , PersonDto.builder().id(UUID.randomUUID()).vorname("John").nachname("Wick").build()
                , PersonDto.builder().id(UUID.randomUUID()).vorname("John Boy").nachname("Walton").build()


        );
        return ResponseEntity.ok(list);
    }


}
