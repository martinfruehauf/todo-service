package application;

import domain.Name;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

  // The java method will produce HTTP GET requests
  @GET
  public Response getNames() {
    List<Name> list = new ArrayList<>();
    list.add(new Name("Max"));
    list.add(new Name("Christian"));
    list.add(new Name("Martin"));
    return Response.ok().entity(list).build();
  }

}
