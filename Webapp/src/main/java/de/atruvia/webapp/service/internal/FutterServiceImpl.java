package de.atruvia.webapp.service.internal;


import de.atruvia.webapp.service.event.SchweinErfasstEvent;
import de.atruvia.webapp.service.event.SchweinGeandertEvent;
import de.atruvia.webapp.service.model.Schwein;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FutterServiceImpl {

    public void futterBestellen() {
        System.out.println("Futter bestellen");
    }

    @EventListener
    public void handle(SchweinErfasstEvent s){
        futterBestellen();// Kafka call
    }

    @EventListener
    public void handle(SchweinGeandertEvent s){
        futterBestellen();
    }
}
