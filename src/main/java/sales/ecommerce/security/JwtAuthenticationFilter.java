package sales.ecommerce.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sales.ecommerce.dto.ErrorResponse;
import sales.ecommerce.entity.User;
import sales.ecommerce.repository.UserRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * Filtro de autenticacion JWT que intercepta las solicitudes HTTP.
 * Valida el token JWT en el header Authorization y establece el usuario
 * autenticado en el contexto de la solicitud.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Nombre del atributo donde se almacena el usuario autenticado en la solicitud.
     */
    public static final String CURRENT_USER_ATTRIBUTE = "currentUser";

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    /**
     * Crea el filtro de autenticacion JWT
     */
    public JwtAuthenticationFilter(
            JwtService jwtService,
            UserRepository userRepository,
            ObjectMapper objectMapper
    ) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.
                objectMapper = objectMapper;
    }

    /**
     * Determina si el filtro no debe aplicarse a la solicitud dada.
     * No aplica el filtro a las rutas de autenticacion (/api/auth/).
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/api/auth/");
    }

    /**
     * Ejecuta la logica de filtrado de la solicitud.
     * Extrae y valida el token JWT, busca el usuario y lo establece en el contexto.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String token = extractToken(request);
            if (token == null || !jwtService.isValid(token)) {
                writeUnauthorized(response, "Token JWT requerido o invalido");
                return;
            }

            String username = jwtService.extractUsername(token);
            User user = userRepository.findByUsername(username).orElse(null);
            if (user == null) {
                writeUnauthorized(response, "Usuario del token no existe");
                return;
            }

            UserContext.setCurrentUser(user);
            request.setAttribute(CURRENT_USER_ATTRIBUTE, user);
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }

    /**
     * Extrae el token JWT del header Authorization.
     */
    private String extractToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }
        return authorization.substring(7);
    }

    /**
     * Escribe una respuesta Unauthorized.
     */
    private void writeUnauthorized(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                message,
                Map.of()
        );
        objectMapper.writeValue(response.getWriter(), error);
    }
}
