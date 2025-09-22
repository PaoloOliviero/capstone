package paolooliviero.capstone.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import paolooliviero.capstone.entities.Utente;
import paolooliviero.capstone.exceptions.UnauthorizedException;
import paolooliviero.capstone.service.UtenteService;

import java.io.IOException;

public class SecurityFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;
    private final UtenteService utenteService;

    // ✅ Costruttore corretto
    public SecurityFilter(JWTTools jwtTools, UtenteService utenteService) {
        this.jwtTools = jwtTools;
        this.utenteService = utenteService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Token mancante o malformato");
        }

        String token = authHeader.replace("Bearer ", "");
        jwtTools.verifyToken(token);

        String utenteId = jwtTools.extractIdFromToken(token);
        Utente utente = utenteService.findById(Long.parseLong(utenteId));

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                utente, null, utente.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}

