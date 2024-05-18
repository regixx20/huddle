package myapp.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationSucessService implements AuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(getClass());


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("isLoggedIn", true);
        session.setAttribute("user", authentication.getName());
        logger.info("User logged in");
        // Redirection vers une page après la connexion
        response.sendRedirect("/dashboard");
    }
}
