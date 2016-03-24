
package com.edenorte.turnos.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for reclamacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reclamacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NIC" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NOMBRE_DISTRIBUIDORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RCM_CANT_REC" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="RCM_ESTADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RCM_FECHA_ESTADO" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="RCM_FECHA_ESTIMASOLUCION" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="RCM_FECHA_GEN" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="RCM_MEDIORECEPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RCM_NRO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RCM_OFICINA_RECEPTORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RCM_TIPO_CODIGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RCM_TIPO_DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reclamacion", propOrder = {
    "nic",
    "nombredistribuidora",
    "rcmcantrec",
    "rcmestado",
    "rcmfechaestado",
    "rcmfechaestimasolucion",
    "rcmfechagen",
    "rcmmediorecepcion",
    "rcmnro",
    "rcmoficinareceptora",
    "rcmtipocodigo",
    "rcmtipodescripcion"
})
public class Reclamacion {

    @XmlElement(name = "NIC")
    protected Integer nic;
    @XmlElement(name = "NOMBRE_DISTRIBUIDORA")
    protected String nombredistribuidora;
    @XmlElement(name = "RCM_CANT_REC")
    protected Integer rcmcantrec;
    @XmlElement(name = "RCM_ESTADO")
    protected String rcmestado;
    @XmlElement(name = "RCM_FECHA_ESTADO")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rcmfechaestado;
    @XmlElement(name = "RCM_FECHA_ESTIMASOLUCION")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rcmfechaestimasolucion;
    @XmlElement(name = "RCM_FECHA_GEN")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar rcmfechagen;
    @XmlElement(name = "RCM_MEDIORECEPCION")
    protected String rcmmediorecepcion;
    @XmlElement(name = "RCM_NRO")
    protected String rcmnro;
    @XmlElement(name = "RCM_OFICINA_RECEPTORA")
    protected String rcmoficinareceptora;
    @XmlElement(name = "RCM_TIPO_CODIGO")
    protected String rcmtipocodigo;
    @XmlElement(name = "RCM_TIPO_DESCRIPCION")
    protected String rcmtipodescripcion;

    /**
     * Gets the value of the nic property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNIC() {
        return nic;
    }

    /**
     * Sets the value of the nic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNIC(Integer value) {
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

    /**
     * Gets the value of the rcmcantrec property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRCMCANTREC() {
        return rcmcantrec;
    }

    /**
     * Sets the value of the rcmcantrec property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRCMCANTREC(Integer value) {
        this.rcmcantrec = value;
    }

    /**
     * Gets the value of the rcmestado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRCMESTADO() {
        return rcmestado;
    }

    /**
     * Sets the value of the rcmestado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRCMESTADO(String value) {
        this.rcmestado = value;
    }

    /**
     * Gets the value of the rcmfechaestado property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRCMFECHAESTADO() {
        return rcmfechaestado;
    }

    /**
     * Sets the value of the rcmfechaestado property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRCMFECHAESTADO(XMLGregorianCalendar value) {
        this.rcmfechaestado = value;
    }

    /**
     * Gets the value of the rcmfechaestimasolucion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRCMFECHAESTIMASOLUCION() {
        return rcmfechaestimasolucion;
    }

    /**
     * Sets the value of the rcmfechaestimasolucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRCMFECHAESTIMASOLUCION(XMLGregorianCalendar value) {
        this.rcmfechaestimasolucion = value;
    }

    /**
     * Gets the value of the rcmfechagen property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRCMFECHAGEN() {
        return rcmfechagen;
    }

    /**
     * Sets the value of the rcmfechagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRCMFECHAGEN(XMLGregorianCalendar value) {
        this.rcmfechagen = value;
    }

    /**
     * Gets the value of the rcmmediorecepcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRCMMEDIORECEPCION() {
        return rcmmediorecepcion;
    }

    /**
     * Sets the value of the rcmmediorecepcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRCMMEDIORECEPCION(String value) {
        this.rcmmediorecepcion = value;
    }

    /**
     * Gets the value of the rcmnro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRCMNRO() {
        return rcmnro;
    }

    /**
     * Sets the value of the rcmnro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRCMNRO(String value) {
        this.rcmnro = value;
    }

    /**
     * Gets the value of the rcmoficinareceptora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRCMOFICINARECEPTORA() {
        return rcmoficinareceptora;
    }

    /**
     * Sets the value of the rcmoficinareceptora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRCMOFICINARECEPTORA(String value) {
        this.rcmoficinareceptora = value;
    }

    /**
     * Gets the value of the rcmtipocodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRCMTIPOCODIGO() {
        return rcmtipocodigo;
    }

    /**
     * Sets the value of the rcmtipocodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRCMTIPOCODIGO(String value) {
        this.rcmtipocodigo = value;
    }

    /**
     * Gets the value of the rcmtipodescripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRCMTIPODESCRIPCION() {
        return rcmtipodescripcion;
    }

    /**
     * Sets the value of the rcmtipodescripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRCMTIPODESCRIPCION(String value) {
        this.rcmtipodescripcion = value;
    }

}
