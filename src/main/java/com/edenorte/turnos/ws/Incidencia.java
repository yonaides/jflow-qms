
package com.edenorte.turnos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for incidencia complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="incidencia">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="INC_DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="INC_FECHA_GENERACION" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="INC_FECHA_SOLUCION" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="INC_OFICINA_RECEPTORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="INC_OPERADOR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NIC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NOMBRE_DISTRIBUIDORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "incidencia", propOrder = {
    "incdescripcion",
    "incfechageneracion",
    "incfechasolucion",
    "incoficinareceptora",
    "incoperador",
    "nic",
    "nombredistribuidora"
})
public class Incidencia {

    @XmlElement(name = "INC_DESCRIPCION")
    protected String incdescripcion;
    @XmlElement(name = "INC_FECHA_GENERACION")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar incfechageneracion;
    @XmlElement(name = "INC_FECHA_SOLUCION")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar incfechasolucion;
    @XmlElement(name = "INC_OFICINA_RECEPTORA")
    protected String incoficinareceptora;
    @XmlElement(name = "INC_OPERADOR")
    protected String incoperador;
    @XmlElement(name = "NIC")
    protected String nic;
    @XmlElement(name = "NOMBRE_DISTRIBUIDORA")
    protected String nombredistribuidora;

    /**
     * Gets the value of the incdescripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCDESCRIPCION() {
        return incdescripcion;
    }

    /**
     * Sets the value of the incdescripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCDESCRIPCION(String value) {
        this.incdescripcion = value;
    }

    /**
     * Gets the value of the incfechageneracion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getINCFECHAGENERACION() {
        return incfechageneracion;
    }

    /**
     * Sets the value of the incfechageneracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setINCFECHAGENERACION(XMLGregorianCalendar value) {
        this.incfechageneracion = value;
    }

    /**
     * Gets the value of the incfechasolucion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getINCFECHASOLUCION() {
        return incfechasolucion;
    }

    /**
     * Sets the value of the incfechasolucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setINCFECHASOLUCION(XMLGregorianCalendar value) {
        this.incfechasolucion = value;
    }

    /**
     * Gets the value of the incoficinareceptora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCOFICINARECEPTORA() {
        return incoficinareceptora;
    }

    /**
     * Sets the value of the incoficinareceptora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCOFICINARECEPTORA(String value) {
        this.incoficinareceptora = value;
    }

    /**
     * Gets the value of the incoperador property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getINCOPERADOR() {
        return incoperador;
    }

    /**
     * Sets the value of the incoperador property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setINCOPERADOR(String value) {
        this.incoperador = value;
    }

    /**
     * Gets the value of the nic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNIC() {
        return nic;
    }

    /**
     * Sets the value of the nic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNIC(String value) {
        this.nic = value;
    }

    /**
     * Gets the value of the nombredistribuidora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNOMBREDISTRIBUIDORA() {
        return nombredistribuidora;
    }

    /**
     * Sets the value of the nombredistribuidora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNOMBREDISTRIBUIDORA(String value) {
        this.nombredistribuidora = value;
    }

}
