package sh.platform.sample;

import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class CustomAuthentication implements HttpAuthenticationMechanism {

    @Override
    public AuthenticationStatus validateRequest(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMessageContext httpMsgContext)
            throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null) {
            return httpMsgContext.notifyContainerAboutLogin(new CustomPrincipal("user"),
                    new HashSet<>(Arrays.asList("ADMIN")));
        }
        return httpMsgContext.responseUnauthorized();
    }
}
