
package com.aniuska.jflow.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for datosCliente complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="datosCliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apellidos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cedula_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_local" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_suministro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_tarifa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigo_tipo_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="correo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_sumnistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desc_tipo_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descripcion_tarifa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="direccion_suministro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="estado_sumninistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fecha_alta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fecha_baja" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="mensaje" type="{http://webservice.webicoden.edenorte.com/}mensaje" minOccurs="0"/>
 *         &lt;element name="nic" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nif" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nis" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nombre_calle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre_cliente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre_distribuidora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre_finca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre_localidad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre_municipio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre_provincia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre_seccion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numero_casa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referencia_finca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telefonos" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipo_finca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "datosCliente", propOrder = {
    "apellidos",
    "cedulaCliente",
    "codigoCalle",
    "codigoLocal",
    "codigoSuministro",
    "codigoTarifa",
    "codigoTipoCliente",
    "correo",
    "descSumnistro",
    "descTipoCliente",
    "descripcionTarifa",
    "direccionSuministro",
    "estadoSumninistro",
    "fechaAlta",
    "fechaBaja",
    "mensaje",
    "nic",
    "nif",
    "nis",
    "nombreCalle",
    "nombreCliente",
    "nombreDistribuidora",
    "nombreFinca",
    "nombreLocalidad",
    "nombreMunicipio",
    "nombreProvincia",
    "nombreSeccion",
    "numeroCasa",
    "referenciaFinca",
    "telefonos",
    "tipoFinca"
})
public class DatosCliente {

    protected String apellidos;
    @XmlElement(name = "cedula_cliente")
    protected String cedulaCliente;
    @XmlElement(name = "codigo_calle")
    protected String codigoCalle;
    @XmlElement(name = "codigo_local")
    protected String codigoLocal;
    @XmlElement(name = "codigo_suministro")
    protected String codigoSuministro;
    @XmlElement(name = "codigo_tarifa")
    protected String codigoTarifa;
    @XmlElement(name = "codigo_tipo_cliente")
    protected String codigoTipoCliente;
    protected String correo;
    @XmlElement(name = "desc_sumnistro")
    protected String descSumnistro;
    @XmlElement(name = "desc_tipo_cliente")
    protected String descTipoCliente;
    @XmlElement(name = "descripcion_tarifa")
    protected String descripcionTarifa;
    @XmlElement(name = "direccion_suministro")
    protected String direccionSuministro;
    @XmlElement(name = "estado_sumninistro")
    protected String estadoSumninistro;
    @XmlElement(name = "fecha_alta")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAlta;
    @XmlElement(name = "fecha_baja")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaBaja;
    protected Mensaje mensaje;
    protected Integer nic;
    protected Integer nif;
    protected Integer nis;
    @XmlElement(name = "nombre_calle")
    protected String nombreCalle;
    @XmlElement(name = "nombre_cliente")
    protected String nombreCliente;
    @XmlElement(name = "nombre_distribuidora")
    protected String nombreDistribuidora;
    @XmlElement(name = "nombre_finca")
    protected String nombreFinca;
    @XmlElement(name = "nombre_localidad")
    protected String nombreLocalidad;
    @XmlElement(name = "nombre_municipio")
    protected String nombreMunicipio;
    @XmlElement(name = "nombre_provincia")
    protected String nombreProvincia;
    @XmlElement(name = "nombre_seccion")
    protected String nombreSeccion;
    @XmlElement(name = "numero_casa")
    protected String numeroCasa;
    @XmlElement(name = "referencia_finca")
    protected String referenciaFinca;
    protected String telefonos;
    @XmlElement(name = "tipo_finca")
    protected String tipoFinca;

    /**
     * Gets the value of the apellidos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Sets the value of the apellidos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidos(String value) {
        this.apellidos = value;
    }

    /**
     * Gets the value of the cedulaCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCedulaCliente() {
        return cedulaCliente;
    }

    /**
     * Sets the value of the cedulaCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCedulaCliente(String value) {
        this.cedulaCliente = value;
    }

    /**
     * Gets the value of the codigoCalle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCalle() {
        return codigoCalle;
    }

    /**
     * Sets the value of the codigoCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCalle(String value) {
        this.codigoCalle = value;
    }

    /**
     * Gets the value of the codigoLocal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoLocal() {
        return codigoLocal;
    }

    /**
     * Sets the value of the codigoLocal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoLocal(String value) {
        this.codigoLocal = value;
    }

    /**
     * Gets the value of the codigoSuministro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoSuministro() {
        return codigoSuministro;
    }

    /**
     * Sets the value of the codigoSuministro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoSuministro(String value) {
        this.codigoSuministro = value;
    }

    /**
     * Gets the value of the codigoTarifa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTarifa() {
        return codigoTarifa;
    }

    /**
     * Sets the value of the codigoTarifa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTarifa(String value) {
        this.codigoTarifa = value;
    }

    /**
     * Gets the value of the codigoTipoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoTipoCliente() {
        return codigoTipoCliente;
    }

    /**
     * Sets the value of the codigoTipoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoTipoCliente(String value) {
        this.codigoTipoCliente = value;
    }

    /**
     * Gets the value of the correo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Sets the value of the correo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorreo(String value) {
        this.correo = value;
    }

    /**
     * Gets the value of the descSumnistro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescSumnistro() {
        return descSumnistro;
    }

    /**
     * Sets the value of the descSumnistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescSumnistro(String value) {
        this.descSumnistro = value;
    }

    /**
     * Gets the value of the descTipoCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescTipoCliente() {
        return descTipoCliente;
    }

    /**
     * Sets the value of the descTipoCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescTipoCliente(String value) {
        this.descTipoCliente = value;
    }

    /**
     * Gets the value of the descripcionTarifa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionTarifa() {
        return descripcionTarifa;
    }

    /**
     * Sets the value of the descripcionTarifa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionTarifa(String value) {
        this.descripcionTarifa = value;
    }

    /**
     * Gets the value of the direccionSuministro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDireccionSuministro() {
        return direccionSuministro;
    }

    /**
     * Sets the value of the direccionSuministro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDireccionSuministro(String value) {
        this.direccionSuministro = value;
    }

    /**
     * Gets the value of the estadoSumninistro property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoSumninistro() {
        return estadoSumninistro;
    }

    /**
     * Sets the value of the estadoSumninistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoSumninistro(String value) {
        this.estadoSumninistro = value;
    }

    /**
     * Gets the value of the fechaAlta property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Sets the value of the fechaAlta property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAlta(XMLGregorianCalendar value) {
        this.fechaAlta = value;
    }

    /**
     * Gets the value of the fechaBaja property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Sets the value of the fechaBaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaBaja(XMLGregorianCalendar value) {
        this.fechaBaja = value;
    }

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
     * Gets the value of the nic property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNic() {
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
    public void setNic(Integer value) {
        this.nic = value;
    }

    /**
     * Gets the value of the nif property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNif() {
        return nif;
    }

    /**
     * Sets the value of the nif property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNif(Integer value) {
        this.nif = value;
    }

    /**
     * Gets the value of the nis property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNis() {
        return nis;
    }

    /**
     * Sets the value of the nis property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNis(Integer value) {
        this.nis = value;
    }

    /**
     * Gets the value of the nombreCalle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCalle() {
        return nombreCalle;
    }

    /**
     * Sets the value of the nombreCalle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCalle(String value) {
        this.nombreCalle = value;
    }

    /**
     * Gets the value of the nombreCliente property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * Sets the value of the nombreCliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreCliente(String value) {
        this.nombreCliente = value;
    }

    /**
     * Gets the value of the nombreDistribuidora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreDistribuidora() {
        return nombreDistribuidora;
    }

    /**
     * Sets the value of the nombreDistribuidora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreDistribuidora(String value) {
        this.nombreDistribuidora = value;
    }

    /**
     * Gets the value of the nombreFinca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreFinca() {
        return nombreFinca;
    }

    /**
     * Sets the value of the nombreFinca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreFinca(String value) {
        this.nombreFinca = value;
    }

    /**
     * Gets the value of the nombreLocalidad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    /**
     * Sets the value of the nombreLocalidad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreLocalidad(String value) {
        this.nombreLocalidad = value;
    }

    /**
     * Gets the value of the nombreMunicipio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    /**
     * Sets the value of the nombreMunicipio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreMunicipio(String value) {
        this.nombreMunicipio = value;
    }

    /**
     * Gets the value of the nombreProvincia property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreProvincia() {
        return nombreProvincia;
    }

    /**
     * Sets the value of the nombreProvincia property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreProvincia(String value) {
        this.nombreProvincia = value;
    }

    /**
     * Gets the value of the nombreSeccion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreSeccion() {
        return nombreSeccion;
    }

    /**
     * Sets the value of the nombreSeccion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreSeccion(String value) {
        this.nombreSeccion = value;
    }

    /**
     * Gets the value of the numeroCasa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCasa() {
        return numeroCasa;
    }

    /**
     * Sets the value of the numeroCasa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCasa(String value) {
        this.numeroCasa = value;
    }

    /**
     * Gets the value of the referenciaFinca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenciaFinca() {
        return referenciaFinca;
    }

    /**
     * Sets the value of the referenciaFinca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenciaFinca(String value) {
        this.referenciaFinca = value;
    }

    /**
     * Gets the value of the telefonos property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelefonos() {
        return telefonos;
    }

    /**
     * Sets the value of the telefonos property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelefonos(String value) {
        this.telefonos = value;
    }

    /**
     * Gets the value of the tipoFinca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoFinca() {
        return tipoFinca;
    }

    /**
     * Sets the value of the tipoFinca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoFinca(String value) {
        this.tipoFinca = value;
    }

}
