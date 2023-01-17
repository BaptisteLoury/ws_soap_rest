package com.example.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  private static String dateFormat = "yyyy-MM-dd";

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

  public FetchTrainResponse fetchTrain(String origin, String destination, String date, String hour, boolean isDepartureTime) {
    FetchTrainRequest request = new FetchTrainRequest();
    request.setOrigin(origin);
    request.setDestination(destination);
    request.setTime(date+"_"+hour);
    request.setIsDepartureTime(isDepartureTime);


    FetchTrainResponse response = (FetchTrainResponse) soapCall("FetchTrainRequest", request);

    return response;
  }

  public BookTrainResponse bookTrain(String trainId, String token, String firstName, String lastName) {
    BookTrainRequest request = new BookTrainRequest();
    request.setTrainId(trainId);
    request.setUserToken(token);
    request.setFirstName(firstName);
    request.setLastName(lastName);


    BookTrainResponse response = (BookTrainResponse) soapCall("BookTrainRequest", request);

    return response;
  }

  public SeeBookingResponse seeBooking(String token) {
    SeeBookingRequest request = new SeeBookingRequest();
    request.setUserToken(token);


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

  public boolean isDateValid(String date) {
    boolean isValid = true;

    DateFormat format = new SimpleDateFormat(dateFormat);
    format.setLenient(false);

    try {
      format.parse(date);
    } catch (ParseException e) {
      isValid = false;
    }
    return isValid;
  }

  public boolean isTimeValid(String time) {
    boolean isValid = false;

    Pattern p = Pattern.compile("(?<hours>[0-9]{2}):(?<minutes>[0-9]{2})");
    Matcher m = p.matcher(time);
    if(m.matches()) {
      Integer hours = Integer.parseInt(m.group("hours"));
      Integer minutes = Integer.parseInt(m.group("minutes"));
      isValid = hours < 24 && minutes < 60;
    }

    return isValid;
  }
}