package com.asierg.wsclient.ws;

import com.asierg.wsclient.ws.generated.Greeting;
import com.asierg.wsclient.ws.generated.ObjectFactory;
import com.asierg.wsclient.ws.generated.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
@Slf4j
public class HelloWorldClient {

    private final WebServiceTemplate webServiceTemplate;

    @Autowired
    public HelloWorldClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public String sayHello(String firstName, String lastName) {
        ObjectFactory factory = new ObjectFactory();
        Person person = factory.createPerson();

        person.setFirstName(firstName);
        person.setLastName(lastName);

        log.info("Client sending person[firstName={},lastName={}]", person.getFirstName(),
                person.getLastName());

        Greeting greeting = (Greeting) webServiceTemplate.marshalSendAndReceive(person);

        log.info("Client received greeting='{}'", greeting.getGreeting());
        return greeting.getGreeting();
    }

}
