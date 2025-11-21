package com.trivalia.trivalia.exFirebaseConfig;
// package com.trivalia.trivalia.config;

// import com.google.firebase.auth.FirebaseAuth;
// import com.google.firebase.auth.FirebaseToken;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import java.io.IOException;
// import java.util.Collections;
// import java.util.List;

// @Component
// public class FirebaseTokenFilter extends OncePerRequestFilter {

//     @Override
//     protected void doFilterInternal(HttpServletRequest request,
//             HttpServletResponse response,
//             FilterChain filterChain)
//             throws ServletException, IOException {

//         String authHeader = request.getHeader("Authorization");

//         // Si no hay token, continúa (solo si quieres permitir endpoints públicos)
//         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//             filterChain.doFilter(request, response);
//             return;
//         }

//         String token = authHeader.substring(7); // eliminar "Bearer "

//         try {
//             FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
//             request.setAttribute("firebaseUser", decodedToken);

//             // Crear una Autentication válida para Spring Security
//             UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                     decodedToken.getUid(), // principal
//                     null // credenciales (no necesarias)
//                      List.of(new SimpleGrantedAuthority("USER"))
//             );

//             // Establecer en el contexto de seguridad
//             SecurityContextHolder.getContext().setAuthentication(authentication);

//             filterChain.doFilter(request, response);
//         } catch (Exception e) {
//             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//             response.getWriter().write("Invalid or expired token");
//         }
//     }
// }
