package sh.platform.sample;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;

import static javax.security.enterprise.identitystore.CredentialValidationResult.Status.VALID;

@ApplicationScoped
public class CustomAuthentication implements HttpAuthenticationMechanism {

    @Inject
    private IdentityStoreHandler identityStoreHandler;

    @Override
    public AuthenticationStatus validateRequest(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMessageContext httpMsgContext)
            throws AuthenticationException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null) {
            // Delegate the {credentials in -> identity data out} function to
            // the Identity Store
            CredentialValidationResult result = identityStoreHandler.validate(
                    new UsernamePasswordCredential(username, password));

            if (result.getStatus() == VALID) {
                // Communicate the details of the authenticated user to the  container
                return httpMsgContext.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
            } else {
                return httpMsgContext.responseUnauthorized();
            }
        } else {
            return httpMsgContext.doNothing();  // Result in 401
        }
    }

}
