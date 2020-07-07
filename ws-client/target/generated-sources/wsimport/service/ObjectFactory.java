
package service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the service package. 
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

    private final static QName _FindByIdWs_QNAME = new QName("http://service/", "findByIdWs");
    private final static QName _FindAllWsResponse_QNAME = new QName("http://service/", "findAllWsResponse");
    private final static QName _FindByIdWsResponse_QNAME = new QName("http://service/", "findByIdWsResponse");
    private final static QName _FindAllWs_QNAME = new QName("http://service/", "findAllWs");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindByIdWsResponse }
     * 
     */
    public FindByIdWsResponse createFindByIdWsResponse() {
        return new FindByIdWsResponse();
    }

    /**
     * Create an instance of {@link FindAllWsResponse }
     * 
     */
    public FindAllWsResponse createFindAllWsResponse() {
        return new FindAllWsResponse();
    }

    /**
     * Create an instance of {@link FindByIdWs }
     * 
     */
    public FindByIdWs createFindByIdWs() {
        return new FindByIdWs();
    }

    /**
     * Create an instance of {@link FindAllWs }
     * 
     */
    public FindAllWs createFindAllWs() {
        return new FindAllWs();
    }

    /**
     * Create an instance of {@link ToDoReprWs }
     * 
     */
    public ToDoReprWs createToDoReprWs() {
        return new ToDoReprWs();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByIdWs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "findByIdWs")
    public JAXBElement<FindByIdWs> createFindByIdWs(FindByIdWs value) {
        return new JAXBElement<FindByIdWs>(_FindByIdWs_QNAME, FindByIdWs.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllWsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "findAllWsResponse")
    public JAXBElement<FindAllWsResponse> createFindAllWsResponse(FindAllWsResponse value) {
        return new JAXBElement<FindAllWsResponse>(_FindAllWsResponse_QNAME, FindAllWsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByIdWsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "findByIdWsResponse")
    public JAXBElement<FindByIdWsResponse> createFindByIdWsResponse(FindByIdWsResponse value) {
        return new JAXBElement<FindByIdWsResponse>(_FindByIdWsResponse_QNAME, FindByIdWsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllWs }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "findAllWs")
    public JAXBElement<FindAllWs> createFindAllWs(FindAllWs value) {
        return new JAXBElement<FindAllWs>(_FindAllWs_QNAME, FindAllWs.class, null, value);
    }

}
