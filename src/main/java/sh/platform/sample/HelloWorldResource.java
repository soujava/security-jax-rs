package sh.platform.sample;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("")
@RequestScoped
public class HelloWorldResource {

    @GET
    @RolesAllowed("ADMIN")
    @Produces("text/plain")
    public String doGet() {
        return "hello from Platform.sh";
    }
}