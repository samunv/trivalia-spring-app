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

        final String authHeader = request.getHeader("Authorization");
        String token = null;
        String uid = null;

        // Extraer el Token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = authHeader.substring(7);


        // Validar Token
        if (jwt.validarToken(token)) {

            uid = jwt.obtenerUid(token);

            // Verificar que el contexto esté vacío (para evitar sobrescribir)
            if (SecurityContextHolder.getContext().getAuthentication() == null) {

                // Cargar detalles del usuario
                UserDetails userDetails = userDetailsService.loadUserByUsername(uid);

                // Crear el Token de Autenticación
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establecer la autenticación en el Contexto
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


        }

        filterChain.doFilter(request, response);
    }
}