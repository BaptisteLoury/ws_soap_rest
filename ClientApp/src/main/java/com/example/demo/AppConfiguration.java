package com.example.demo;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

public class AppConfiguration {

  public static Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    // this package must match the package in the <generatePackage> specified in
    // pom.xml
    marshaller.setContextPath("com.train.booking.wsdl");
    return marshaller;
  }

  public static Client countryClient(Jaxb2Marshaller marshaller) {
    Client client = new Client();
    client.setDefaultUri("http://localhost:8181/train-booking/ws");
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }

}