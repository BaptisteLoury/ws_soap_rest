//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.3.2 
// Voir <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2023.01.17 à 06:27:11 PM CET 
//


package localhost.train.booking;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="trainId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="userToken" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "trainId",
    "userToken"
})
@XmlRootElement(name = "bookTrainRequest")
public class BookTrainRequest {

    @XmlElement(required = true)
    protected String trainId;
    @XmlElement(required = true)
    protected String userToken;

    /**
     * Obtient la valeur de la propriété trainId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainId() {
        return trainId;
    }

    /**
     * Définit la valeur de la propriété trainId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainId(String value) {
        this.trainId = value;
    }

    /**
     * Obtient la valeur de la propriété userToken.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * Définit la valeur de la propriété userToken.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserToken(String value) {
        this.userToken = value;
    }

}
