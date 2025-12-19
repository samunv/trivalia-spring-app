package com.trivalia.trivalia.config;

import com.trivalia.trivalia.config.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final Jwt jwt;
    private final UserDetailsService userDetailsService;

    public JwtFilter(Jwt jwt, UserDetailsService userDetailsService) {
        this.jwt = jwt;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        System.out.println(">>> REQ RECEIVED: " + request.getRequestURI());

        String token = extraerAccessJWTokenDeCookies(request);
        String uid = null;

        // Si no hay cookie con JWT, continuar sin autenticación
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            if (jwt.validarToken(token)) {
                uid = jwt.obtenerUid(token);

                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Cargar detalles y establecer el contexto
                    UserDetails userDetails = userDetailsService.loadUserByUsername(uid);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities()
                            );

                    authentication.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception e) {
            // JWT expirado o inválido: devolver 401
            System.out.println("JWT expirado o inválido: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String extraerAccessJWTokenDeCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            return null;
        }

        // Buscar la cookie de accessJWToken
        for (Cookie cookie : cookies) {
            if ("accessJWToken".equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }
}