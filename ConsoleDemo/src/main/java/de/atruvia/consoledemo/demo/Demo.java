package de.atruvia.consoledemo.demo;


import de.atruvia.consoledemo.translator.Translator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Named;


//@Named
//@Component // Stellt die Klasse unter die Verwaltung von Spring
@Scope("singleton") // Default
@Lazy(false)
//@Scope("prototype") // Erzeugt bei Bedarf ein NEUES Objekt
@RequiredArgsConstructor
public class Demo {


    @Value("${Demo.message}")
    private final String message;

    //@Qualifier("upper")
    private final Translator translator;



    @PostConstruct
    public void init() {
        System.out.println(translator.translate("Post Construct"));
        System.out.println(message);
    }

    @PreDestroy
    public void dispose() {
        System.out.println(translator.translate("Pre Destroy"));
    }
}
