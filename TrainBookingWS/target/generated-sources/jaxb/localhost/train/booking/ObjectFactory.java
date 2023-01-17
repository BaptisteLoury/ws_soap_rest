//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.2 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2023.01.17 à 06:27:11 PM CET 
//


package localhost.train.booking;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the localhost.train.booking package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: localhost.train.booking
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SignUpRequest }
     * 
     */
    public SignUpRequest createSignUpRequest() {
        return new SignUpRequest();
    }

    /**
     * Create an instance of {@link SignUpResponse }
     * 
     */
    public SignUpResponse createSignUpResponse() {
        return new SignUpResponse();
    }

    /**
     * Create an instance of {@link SignInRequest }
     * 
     */
    public SignInRequest createSignInRequest() {
        return new SignInRequest();
    }

    /**
     * Create an instance of {@link SignInResponse }
     * 
     */
    public SignInResponse createSignInResponse() {
        return new SignInResponse();
    }

    /**
     * Create an instance of {@link FetchTrainRequest }
     * 
     */
    public FetchTrainRequest createFetchTrainRequest() {
        return new FetchTrainRequest();
    }

    /**
     * Create an instance of {@link FetchTrainResponse }
     * 
     */
    public FetchTrainResponse createFetchTrainResponse() {
        return new FetchTrainResponse();
    }

    /**
     * Create an instance of {@link Train }
     * 
     */
    public Train createTrain() {
        return new Train();
    }

    /**
     * Create an instance of {@link BookTrainRequest }
     * 
     */
    public BookTrainRequest createBookTrainRequest() {
        return new BookTrainRequest();
    }

    /**
     * Create an instance of {@link BookTrainResponse }
     * 
     */
    public BookTrainResponse createBookTrainResponse() {
        return new BookTrainResponse();
    }

    /**
     * Create an instance of {@link SeeBookingRequest }
     * 
     */
    public SeeBookingRequest createSeeBookingRequest() {
        return new SeeBookingRequest();
    }

    /**
     * Create an instance of {@link SeeBookingResponse }
     * 
     */
    public SeeBookingResponse createSeeBookingResponse() {
        return new SeeBookingResponse();
    }

}
