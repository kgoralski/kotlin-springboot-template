package pl.goralski.config

import com.fasterxml.jackson.module.kotlin.KotlinModule
import pl.goralski.web.BookWebApiImpl
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.server.ServerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import pl.goralski.web.BookHateoasApiImpl
import javax.ws.rs.ApplicationPath

@Configuration
@ApplicationPath("/rest")
open class JerseyConfig : ResourceConfig {

    constructor() {
        register(BookWebApiImpl::class.java)
                .register(BookHateoasApiImpl::class.java)
                .property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }

    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilder
            = Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule())
}