package sh.platform.sample;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("")
@RequestScoped
@DeclareRoles({"ADMIN"})  // You need to indicate all roles that are used by the app
public class HelloWorldResource {

    @GET
    @RolesAllowed("ADMIN")
    @Produces("text/plain")
    public String doGet() {
        return "hello from Platform.sh";
    }
}