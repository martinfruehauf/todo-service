import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path ("/todos")
public class TodoResource {

    // The java method will produce HTTP GET requests
    @GET
    public Response ping() {
        return Response.ok().entity("Service online").build();
    }


    // The java method will
}
