
package com.aniuska.jflow.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reclamacionesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reclamacionesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mensaje" type="{http://webservice.webicoden.edenorte.com/}mensaje" minOccurs="0"/>
 *         &lt;element name="reclamaciones" type="{http://webservice.webicoden.edenorte.com/}reclamacion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reclamacionesResponse", propOrder = {
    "mensaje",
    "reclamaciones"
})
public class ReclamacionesResponse {

    protected Mensaje mensaje;
    @XmlElement(nillable = true)
    protected List<Reclamacion> reclamaciones;

    /**
     * Gets the value of the mensaje property.
     * 
     * @return
     *     possible object is
     *     {@link Mensaje }
     *     
     */
    public Mensaje getMensaje() {
        return mensaje;
    }

    /**
     * Sets the value of the mensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mensaje }
     *     
     */
    public void setMensaje(Mensaje value) {
        this.mensaje = value;
    }

    /**
     * Gets the value of the reclamaciones property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reclamaciones property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReclamaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reclamacion }
     * 
     * 
     */
    public List<Reclamacion> getReclamaciones() {
        if (reclamaciones == null) {
            reclamaciones = new ArrayList<Reclamacion>();
        }
        return this.reclamaciones;
    }

}
