package sales.ecommerce.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sales.ecommerce.dto.AuthResponse;
import sales.ecommerce.dto.LoginRequest;
import sales.ecommerce.dto.RegisterRequest;
import sales.ecommerce.service.AuthService;

/**
 * Controlador REST encargado de exponer las operaciones de autenticacion.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Crea el controlador de autenticacion.
     * authService servicio que contiene la logica de registro e inicio de sesion
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registra un nuevo usuario y devuelve un token JWT para acceder a la API.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    /**
     * Valida las credenciales de un usuario existente.
     */
    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
