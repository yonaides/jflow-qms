
package com.edenorte.turnos.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.edenorte.turnos.ws package. 
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

    private final static QName _GetDatosClienteResponse_QNAME = new QName("http://webservice.webicoden.edenorte.com/", "getDatosClienteResponse");
    private final static QName _GetIncidencias_QNAME = new QName("http://webservice.webicoden.edenorte.com/", "getIncidencias");
    private final static QName _GetDatosCliente_QNAME = new QName("http://webservice.webicoden.edenorte.com/", "getDatosCliente");
    private final static QName _GetIncidenciasResponse_QNAME = new QName("http://webservice.webicoden.edenorte.com/", "getIncidenciasResponse");
    private final static QName _GetReclamaciones_QNAME = new QName("http://webservice.webicoden.edenorte.com/", "getReclamaciones");
    private final static QName _GetOrdenesServicio_QNAME = new QName("http://webservice.webicoden.edenorte.com/", "getOrdenesServicio");
    private final static QName _GetOrdenesServicioResponse_QNAME = new QName("http://webservice.webicoden.edenorte.com/", "getOrdenesServicioResponse");
    private final static QName _GetReclamacionesResponse_QNAME = new QName("http://webservice.webicoden.edenorte.com/", "getReclamacionesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.edenorte.turnos.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetOrdenesServicio }
     * 
     */
    public GetOrdenesServicio createGetOrdenesServicio() {
        return new GetOrdenesServicio();
    }

    /**
     * Create an instance of {@link GetOrdenesServicioResponse }
     * 
     */
    public GetOrdenesServicioResponse createGetOrdenesServicioResponse() {
        return new GetOrdenesServicioResponse();
    }

    /**
     * Create an instance of {@link GetReclamacionesResponse }
     * 
     */
    public GetReclamacionesResponse createGetReclamacionesResponse() {
        return new GetReclamacionesResponse();
    }

    /**
     * Create an instance of {@link GetReclamaciones }
     * 
     */
    public GetReclamaciones createGetReclamaciones() {
        return new GetReclamaciones();
    }

    /**
     * Create an instance of {@link GetDatosCliente }
     * 
     */
    public GetDatosCliente createGetDatosCliente() {
        return new GetDatosCliente();
    }

    /**
     * Create an instance of {@link GetIncidenciasResponse }
     * 
     */
    public GetIncidenciasResponse createGetIncidenciasResponse() {
        return new GetIncidenciasResponse();
    }

    /**
     * Create an instance of {@link GetDatosClienteResponse }
     * 
     */
    public GetDatosClienteResponse createGetDatosClienteResponse() {
        return new GetDatosClienteResponse();
    }

    /**
     * Create an instance of {@link GetIncidencias }
     * 
     */
    public GetIncidencias createGetIncidencias() {
        return new GetIncidencias();
    }

    /**
     * Create an instance of {@link OrdenesServicioResponse }
     * 
     */
    public OrdenesServicioResponse createOrdenesServicioResponse() {
        return new OrdenesServicioResponse();
    }

    /**
     * Create an instance of {@link IncidenciasResponse }
     * 
     */
    public IncidenciasResponse createIncidenciasResponse() {
        return new IncidenciasResponse();
    }

    /**
     * Create an instance of {@link Incidencia }
     * 
     */
    public Incidencia createIncidencia() {
        return new Incidencia();
    }

    /**
     * Create an instance of {@link OrdenServicio }
     * 
     */
    public OrdenServicio createOrdenServicio() {
        return new OrdenServicio();
    }

    /**
     * Create an instance of {@link ReclamacionesResponse }
     * 
     */
    public ReclamacionesResponse createReclamacionesResponse() {
        return new ReclamacionesResponse();
    }

    /**
     * Create an instance of {@link DatosCliente }
     * 
     */
    public DatosCliente createDatosCliente() {
        return new DatosCliente();
    }

    /**
     * Create an instance of {@link Reclamacion }
     * 
     */
    public Reclamacion createReclamacion() {
        return new Reclamacion();
    }

    /**
     * Create an instance of {@link Mensaje }
     * 
     */
    public Mensaje createMensaje() {
        return new Mensaje();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDatosClienteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webicoden.edenorte.com/", name = "getDatosClienteResponse")
    public JAXBElement<GetDatosClienteResponse> createGetDatosClienteResponse(GetDatosClienteResponse value) {
        return new JAXBElement<GetDatosClienteResponse>(_GetDatosClienteResponse_QNAME, GetDatosClienteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIncidencias }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webicoden.edenorte.com/", name = "getIncidencias")
    public JAXBElement<GetIncidencias> createGetIncidencias(GetIncidencias value) {
        return new JAXBElement<GetIncidencias>(_GetIncidencias_QNAME, GetIncidencias.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDatosCliente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webicoden.edenorte.com/", name = "getDatosCliente")
    public JAXBElement<GetDatosCliente> createGetDatosCliente(GetDatosCliente value) {
        return new JAXBElement<GetDatosCliente>(_GetDatosCliente_QNAME, GetDatosCliente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetIncidenciasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webicoden.edenorte.com/", name = "getIncidenciasResponse")
    public JAXBElement<GetIncidenciasResponse> createGetIncidenciasResponse(GetIncidenciasResponse value) {
        return new JAXBElement<GetIncidenciasResponse>(_GetIncidenciasResponse_QNAME, GetIncidenciasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReclamaciones }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webicoden.edenorte.com/", name = "getReclamaciones")
    public JAXBElement<GetReclamaciones> createGetReclamaciones(GetReclamaciones value) {
        return new JAXBElement<GetReclamaciones>(_GetReclamaciones_QNAME, GetReclamaciones.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrdenesServicio }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webicoden.edenorte.com/", name = "getOrdenesServicio")
    public JAXBElement<GetOrdenesServicio> createGetOrdenesServicio(GetOrdenesServicio value) {
        return new JAXBElement<GetOrdenesServicio>(_GetOrdenesServicio_QNAME, GetOrdenesServicio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrdenesServicioResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webicoden.edenorte.com/", name = "getOrdenesServicioResponse")
    public JAXBElement<GetOrdenesServicioResponse> createGetOrdenesServicioResponse(GetOrdenesServicioResponse value) {
        return new JAXBElement<GetOrdenesServicioResponse>(_GetOrdenesServicioResponse_QNAME, GetOrdenesServicioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetReclamacionesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webicoden.edenorte.com/", name = "getReclamacionesResponse")
    public JAXBElement<GetReclamacionesResponse> createGetReclamacionesResponse(GetReclamacionesResponse value) {
        return new JAXBElement<GetReclamacionesResponse>(_GetReclamacionesResponse_QNAME, GetReclamacionesResponse.class, null, value);
    }

}
