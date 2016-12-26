package pl.goralski.client

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.proxy.WebResourceFactory;

open class GenericClient<T>(resourceURI: String, clazz: Class<T>) {

    protected var config: ClientConfig? = null

    protected var target: WebTarget? = null

    protected var resource: T? = null

    init {
        config = ClientConfig()
        val client = ClientBuilder.newClient(config)
        target = client.target(resourceURI)
        resource = WebResourceFactory.newResource(clazz, target)
    }

}