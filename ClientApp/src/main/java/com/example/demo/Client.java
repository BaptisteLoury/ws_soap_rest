package com.example.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.train.booking.wsdl.BookTrainRequest;
import com.train.booking.wsdl.BookTrainResponse;
import com.train.booking.wsdl.FetchTrainRequest;
import com.train.booking.wsdl.FetchTrainResponse;
import com.train.booking.wsdl.SeeBookingRequest;
import com.train.booking.wsdl.SeeBookingResponse;
import com.train.booking.wsdl.SignInRequest;
import com.train.booking.wsdl.SignInResponse;
import com.train.booking.wsdl.SignUpRequest;
import com.train.booking.wsdl.SignUpResponse;

public class Client extends WebServiceGatewaySupport {

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
    request.setBirthDate(date);

    SignUpResponse response = (SignUpResponse) soapCall("SignUpRequest", request);

    return response;
  }

  public SignInResponse signIn(String email, String password) {
    SignInRequest request = new SignInRequest();
    request.setEmail(email);
    request.setPassword(password);

    SignInResponse response = (SignInResponse) soapCall("SignInRequest", request);

    return response;
  }

  public FetchTrainResponse fetchTrain(String origin, String destination, String date, String hour, String isDepartureTime) {
    FetchTrainRequest request = new FetchTrainRequest();
    request.setOrigin(origin);
    request.setDestination(destination);


    FetchTrainResponse response = (FetchTrainResponse) soapCall("FetchTrainRequest", request);

    return response;
  }

  public BookTrainResponse bookTrain(int trainId, String nom, String prenom) {
    BookTrainRequest request = new BookTrainRequest();


    BookTrainResponse response = (BookTrainResponse) soapCall("BookTrainRequest", request);

    return response;
  }

  public SeeBookingResponse seeBooking(int trainId, String nom, String prenom) {
    SeeBookingRequest request = new SeeBookingRequest();


    SeeBookingResponse response = (SeeBookingResponse) soapCall("SeeBookingRequest", request);

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

  private Object soapCall(String request, Object payload) {
    return getWebServiceTemplate()
    .marshalSendAndReceive("http://localhost:8181/train-booking/ws/trainBooking",
        payload,
        new SoapActionCallback(
            "http://localhost/train/booking/"+request));
  }
}