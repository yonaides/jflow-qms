
package com.aniuska.jflow.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ordenServicio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ordenServicio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NIC" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="NOMBRE_DISTRIBUIDORA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OS_ACCION_REALIZADA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OS_ESTADO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OS_FECHA_ESTADO" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="OS_FECHA_ESTIMASOLUCION" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="OS_FECHA_GEN" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="OS_NUMERO" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="OS_OBSERVACIONES" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OS_OFICINA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OS_RESPONSABLE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OS_TIPO_CODIGO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OS_TIPO_DESCRIPCION" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OS_USUARIO_VISITA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ordenServicio", propOrder = {
    "nic",
    "nombredistribuidora",
    "osaccionrealizada",
    "osestado",
    "osfechaestado",
    "osfechaestimasolucion",
    "osfechagen",
    "osnumero",
    "osobservaciones",
    "osoficina",
    "osresponsable",
    "ostipocodigo",
    "ostipodescripcion",
    "osusuariovisita"
})
public class OrdenServicio {

    @XmlElement(name = "NIC")
    protected Integer nic;
    @XmlElement(name = "NOMBRE_DISTRIBUIDORA")
    protected String nombredistribuidora;
    @XmlElement(name = "OS_ACCION_REALIZADA")
    protected String osaccionrealizada;
    @XmlElement(name = "OS_ESTADO")
    protected String osestado;
    @XmlElement(name = "OS_FECHA_ESTADO")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar osfechaestado;
    @XmlElement(name = "OS_FECHA_ESTIMASOLUCION")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar osfechaestimasolucion;
    @XmlElement(name = "OS_FECHA_GEN")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar osfechagen;
    @XmlElement(name = "OS_NUMERO")
    protected Integer osnumero;
    @XmlElement(name = "OS_OBSERVACIONES")
    protected String osobservaciones;
    @XmlElement(name = "OS_OFICINA")
    protected String osoficina;
    @XmlElement(name = "OS_RESPONSABLE")
    protected String osresponsable;
    @XmlElement(name = "OS_TIPO_CODIGO")
    protected String ostipocodigo;
    @XmlElement(name = "OS_TIPO_DESCRIPCION")
    protected String ostipodescripcion;
    @XmlElement(name = "OS_USUARIO_VISITA")
    protected String osusuariovisita;

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
     * Gets the value of the osaccionrealizada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSACCIONREALIZADA() {
        return osaccionrealizada;
    }

    /**
     * Sets the value of the osaccionrealizada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSACCIONREALIZADA(String value) {
        this.osaccionrealizada = value;
    }

    /**
     * Gets the value of the osestado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSESTADO() {
        return osestado;
    }

    /**
     * Sets the value of the osestado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSESTADO(String value) {
        this.osestado = value;
    }

    /**
     * Gets the value of the osfechaestado property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOSFECHAESTADO() {
        return osfechaestado;
    }

    /**
     * Sets the value of the osfechaestado property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOSFECHAESTADO(XMLGregorianCalendar value) {
        this.osfechaestado = value;
    }

    /**
     * Gets the value of the osfechaestimasolucion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOSFECHAESTIMASOLUCION() {
        return osfechaestimasolucion;
    }

    /**
     * Sets the value of the osfechaestimasolucion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOSFECHAESTIMASOLUCION(XMLGregorianCalendar value) {
        this.osfechaestimasolucion = value;
    }

    /**
     * Gets the value of the osfechagen property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOSFECHAGEN() {
        return osfechagen;
    }

    /**
     * Sets the value of the osfechagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOSFECHAGEN(XMLGregorianCalendar value) {
        this.osfechagen = value;
    }

    /**
     * Gets the value of the osnumero property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOSNUMERO() {
        return osnumero;
    }

    /**
     * Sets the value of the osnumero property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOSNUMERO(Integer value) {
        this.osnumero = value;
    }

    /**
     * Gets the value of the osobservaciones property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSOBSERVACIONES() {
        return osobservaciones;
    }

    /**
     * Sets the value of the osobservaciones property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSOBSERVACIONES(String value) {
        this.osobservaciones = value;
    }

    /**
     * Gets the value of the osoficina property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSOFICINA() {
        return osoficina;
    }

    /**
     * Sets the value of the osoficina property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSOFICINA(String value) {
        this.osoficina = value;
    }

    /**
     * Gets the value of the osresponsable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSRESPONSABLE() {
        return osresponsable;
    }

    /**
     * Sets the value of the osresponsable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSRESPONSABLE(String value) {
        this.osresponsable = value;
    }

    /**
     * Gets the value of the ostipocodigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSTIPOCODIGO() {
        return ostipocodigo;
    }

    /**
     * Sets the value of the ostipocodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSTIPOCODIGO(String value) {
        this.ostipocodigo = value;
    }

    /**
     * Gets the value of the ostipodescripcion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSTIPODESCRIPCION() {
        return ostipodescripcion;
    }

    /**
     * Sets the value of the ostipodescripcion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSTIPODESCRIPCION(String value) {
        this.ostipodescripcion = value;
    }

    /**
     * Gets the value of the osusuariovisita property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSUSUARIOVISITA() {
        return osusuariovisita;
    }

    /**
     * Sets the value of the osusuariovisita property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSUSUARIOVISITA(String value) {
        this.osusuariovisita = value;
    }

}
