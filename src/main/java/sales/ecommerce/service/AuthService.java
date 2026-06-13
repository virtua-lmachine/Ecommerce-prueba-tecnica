package sales.ecommerce.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.ecommerce.dto.AuthResponse;
import sales.ecommerce.dto.LoginRequest;
import sales.ecommerce.dto.RegisterRequest;
import sales.ecommerce.entity.User;
import sales.ecommerce.exception.BadRequestException;
import sales.ecommerce.exception.UnauthorizedException;
import sales.ecommerce.mapper.UserMapper;
import sales.ecommerce.repository.UserRepository;
import sales.ecommerce.security.JwtService;

import java.time.LocalDateTime;

/**
 * Servicio responsable del registro, inicio de sesion y generacion de respuestas autenticadas.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * Crea el servicio de autenticacion.
     */
    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    /**
     * Registra un nuevo usuario, valida que sus datos sean unicos y cifra su contrasena.
     */
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new BadRequestException("El username ya esta registrado");
        }
        if (userRepository.existsByEmail(request.email())) {
            throw new BadRequestException("El email ya esta registrado");
        }

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(BCrypt.hashpw(request.password(), BCrypt.gensalt()))
                .fechaRegistro(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);
        return buildAuthResponse(savedUser);
    }

    /**
     * Autentica un usuario con su nombre de usuario y contrasena.
     */
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UnauthorizedException("Credenciales invalidas"));

        if (!BCrypt.checkpw(request.password(), user.getPassword())) {
            throw new UnauthorizedException("Credenciales invalidas");
        }

        return buildAuthResponse(user);
    }

    /**
     * Construye la respuesta de autenticacion incluyendo token y datos publicos del usuario.
     */
    private AuthResponse buildAuthResponse(User user) {
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, "Bearer", UserMapper.toResponse(user));
    }
}
