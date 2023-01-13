package com.example.demo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.train.booking.wsdl.SignInRequest;
import com.train.booking.wsdl.SignInResponse;
import com.train.booking.wsdl.SignUpRequest;
import com.train.booking.wsdl.SignUpResponse;

public class Client extends WebServiceGatewaySupport {

  private static final Logger log = LoggerFactory.getLogger(Client.class);

  private static String dateFormat = "dd/MM/yyyy";

  public Client() {
    Jaxb2Marshaller marshaller = AppConfiguration.marshaller();
    setDefaultUri("http://localhost:8181/train-booking/ws");
    setMarshaller(marshaller);
    setUnmarshaller(marshaller);
  }

  public SignUpResponse signUp(String email, String password, String lastName, String firstName, String birthDate) {
    SignUpRequest request = new SignUpRequest();
    request.setEmail(email);
    request.setPassword(password);
    request.setLastName(lastName);
    request.setFirstName(firstName);

    XMLGregorianCalendar date = stringToGregorianCal(birthDate);
    log.info(email+" "+password+" "+lastName+" "+firstName+" "+birthDate);
    request.setBirthDate(date);

    SignUpResponse response = (SignUpResponse) getWebServiceTemplate()
        .marshalSendAndReceive("http://localhost:8181/train-booking/ws/trainBooking",
            request,
            new SoapActionCallback(
                "http://localhost/train/booking/SignUpRequest"));

    return response;
  }

  public SignInResponse signIn(String email, String password) {
    SignInRequest request = new SignInRequest();

    SignInResponse response = (SignInResponse) getWebServiceTemplate()
        .marshalSendAndReceive("http://localhost:8181/train-booking/ws/trainBooking",
            request,
            new SoapActionCallback(
                "http://localhost/train/booking/SignInRequest"));

    return response;
  }

  private XMLGregorianCalendar stringToGregorianCal(String dateString) {
    XMLGregorianCalendar gregCal = null;
    GregorianCalendar cal = new GregorianCalendar();

    DateFormat format = new SimpleDateFormat(dateFormat);
    try {
      Date date = format.parse(dateString);
      cal.setTime(date);
    } catch (ParseException e) {
      // do nothing
    }

    try {
      gregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    } catch (DatatypeConfigurationException e) {
    }
    return gregCal;
  }
}