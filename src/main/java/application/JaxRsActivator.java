package application;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
@OpenAPIDefinition(info = @Info(title = "Base API",
        description = "Provides access to the API operations",
        version = "1.0-SNAPSHOT"),
        servers = @Server(url = "http://{host}:{port}/{context-root}",
                variables = { @ServerVariable(name = "host",
                        defaultValue = "localhost"), @ServerVariable(name = "port",
                        defaultValue = "8080"), @ServerVariable(name = "context-root",
                        defaultValue = "todo-service-1.0-SNAPSHOT") }),
        tags = { @Tag(name = "Todos") })
public class JaxRsActivator extends Application {
}