package de.atruvia.webapp.presentation.v1;

import de.atruvia.webapp.presentation.dto.PersonDto;
import de.atruvia.webapp.service.PersonService;
import de.atruvia.webapp.service.PersonenServiceException;
import de.atruvia.webapp.service.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class PersonenCommandControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PersonService personenServiceMock;

    @Test
    void delete1() throws PersonenServiceException {
        final UUID uuid = UUID.randomUUID();
        when(personenServiceMock.loeschen(any())).thenReturn(true);
        var entity = restTemplate.exchange("/v1/personen/" + uuid.toString(), HttpMethod.DELETE, null, Void.class);
        verify(personenServiceMock,times(1)).loeschen(uuid);
        assertEquals(HttpStatus.OK, entity.getStatusCode() );
    }

    @Test
    void delete2() throws PersonenServiceException {
        final UUID uuid = UUID.randomUUID();
        when(personenServiceMock.loeschen(any())).thenReturn(false);
        var entity = restTemplate.exchange("/v1/personen/" + uuid.toString(), HttpMethod.DELETE, null, Void.class);
        verify(personenServiceMock).loeschen(uuid);
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode() );
    }

    @Test
    void post() throws PersonenServiceException {
        final UUID uuid = UUID.randomUUID();
        final PersonDto personDtoToSend = PersonDto.builder().id(uuid).vorname("John").nachname("Doe").build();
        final HttpEntity<PersonDto> request = new HttpEntity<>(personDtoToSend);

        final Person personToVerify = Person.builder().id(uuid).vorname("John").nachname("Doe").build();

        doNothing().when(personenServiceMock).speichern(any());

        var entity = restTemplate.exchange("/v1/personen", HttpMethod.POST, request, Void.class);
        verify(personenServiceMock).speichern(personToVerify);
        assertTrue(entity.getHeaders().getLocation().getPath().endsWith("/v1/personen/" + uuid.toString()));
        assertEquals(HttpStatus.CREATED, entity.getStatusCode() );
    }

}