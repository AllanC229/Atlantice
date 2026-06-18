package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class Filtre implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        
        

        // Pages accessibles sans connexion
        if (uri.endsWith("/Connexion") || uri.endsWith("/ControleurConnexion") || uri.endsWith("/ControleurDeconnexion")) {

            chain.doFilter(request, response);
            return;
            
        }

        HttpSession h = req.getSession(false);

        if (h == null || h.getAttribute("activeUser") == null) {
            resp.sendRedirect(req.getContextPath() + "/Connexion");
            return;
        }
        

     /*   resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0); */
        
        chain.doFilter(request, response);
    }


}