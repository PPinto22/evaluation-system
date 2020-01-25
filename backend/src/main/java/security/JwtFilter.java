package security;

import controller.ErrorMessages;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;
import wrapper.ErrorWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {

    private JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            this.warnInvalidToken(response, false);
            return;
        }

        final String token = authHeader.substring(7); // The part after "Bearer "

        try {
            final Claims claims = jwtService.getClaims(token);
            request.setAttribute("claims", claims);
        }
        catch (final ExpiredJwtException e){
            warnInvalidToken(response,true);
            return;
        }
        catch (final SignatureException | MalformedJwtException e) {
            warnInvalidToken(response,false);
            return;
        }

        chain.doFilter(req, res);
    }

    private void warnInvalidToken(HttpServletResponse response, boolean expired){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String message = expired?"Token expired":"Invalid token";
        try {
            response.getWriter().print(
                    "{\"message\":\""+message+"\"}"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}