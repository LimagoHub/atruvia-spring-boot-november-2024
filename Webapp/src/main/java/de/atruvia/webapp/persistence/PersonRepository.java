package de.atruvia.webapp.persistence;

import de.atruvia.webapp.persistence.entity.PersonEntity;
import de.atruvia.webapp.persistence.entity.TinyPerson;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRepository extends CrudRepository<PersonEntity, UUID>, CustomPersonRepository {

    // partielles Update daher HTTP-Verb PATCH
    @Query("update PersonEntity p set  p.vorname=:vorname  where p.id=:id")
    void updateVorname(UUID id, String vorname);

    Iterable<PersonEntity> findByNachname(String nachname);
    Iterable<PersonEntity> findByVorname(String vorname);

    @Query("select p.id, p.nachname from PersonEntity p")
    Iterable<Object[]> herbert();

    @Query("select new de.atruvia.webapp.persistence.entity.TinyPerson(p.id, p.nachname) from PersonEntity  p")
    Iterable<TinyPerson> fritz();
}
