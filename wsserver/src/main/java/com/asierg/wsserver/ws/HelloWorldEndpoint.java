package com.asierg.wsserver.ws;

import com.asierg.wsserver.ws.generated.Greeting;
import com.asierg.wsserver.ws.generated.ObjectFactory;
import com.asierg.wsserver.ws.generated.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Slf4j
public class HelloWorldEndpoint {

  public static final String NAMESPACE = "http://asierg.com/types/helloworld";
  public static final String HELLOWORLD_REQUEST_LOCAL_PART = "person";

  @PayloadRoot(namespace = NAMESPACE, localPart = HELLOWORLD_REQUEST_LOCAL_PART)
  @ResponsePayload
  public Greeting sayHello(@RequestPayload Person personRequest) {
    log.info(
        "Endpoint received person[firstName={},lastName={}]",
        personRequest.getFirstName(),
        personRequest.getLastName());

    String greeting =
        "Kaixo " + personRequest.getFirstName() + " " + personRequest.getLastName() + "!";

    ObjectFactory factory = new ObjectFactory();
    Greeting response = factory.createGreeting();
    response.setGreeting(greeting);

    log.info("Endpoint sending greeting='{}'", response.getGreeting());
    return response;
  }
}
