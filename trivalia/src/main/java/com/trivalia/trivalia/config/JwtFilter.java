package com.trivalia.trivalia.config;


import com.trivalia.trivalia.config.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
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
// ... otras importaciones

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
        final String authHeader = request.getHeader("Authorization");
        String token = null;
        String uid = null;

        // Extraer el Token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Si no hay encabezado o no es Bearer, se salta la autenticación y continúa la cadena
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7); // Extraer la parte del JWT después de Bearer

        if (token != null) {
            try {
                // Validar Token: Si falla, lanza ExpiredJwtException.
                if (jwt.validarToken(token)) {

                    uid = jwt.obtenerUid(token);

                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        // 2. Si es válido, cargar detalles y establecer el contexto
                        UserDetails userDetails = userDetailsService.loadUserByUsername(uid);
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
                // Controlar la excepción del JWT
            } catch (Exception e) {
                // Esto asegura que la excepción se capture, el contexto quede vacío,
                // y no se interrumpa la cadena principal con un error 500.
                System.out.println("JWT expirado o inválido: " + e.getMessage());
            }
        }

        // se activa el AuthenticationEntryPoint, devolviendo 401.
        filterChain.doFilter(request, response);
    }
}