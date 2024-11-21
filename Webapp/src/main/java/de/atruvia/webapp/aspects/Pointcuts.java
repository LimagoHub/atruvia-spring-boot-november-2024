package de.atruvia.webapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {


    @Pointcut(value="execution(public * de.atruvia.webapp.presentation.v1.PersonQueryController.*(..))")
    public void personenQueryControllerMethods(){}

    @Pointcut(value="@within(org.springframework.stereotype.Service)")
    public void serviceMethods(){}

    @Pointcut("@within(de.atruvia.webapp.aspects.Dozent)")
    public void dozentMethods(){}

}
