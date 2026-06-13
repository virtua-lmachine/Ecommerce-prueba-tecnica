package sales.ecommerce.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad que representa a un usuario del sistema de comercio electronico.
 * Almacena la informacion basica de autenticacion y registro del usuario.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    /**
     * Identificador unico del usuario en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de usuario unico para iniciar sesion.
     */
    @Column(nullable = false, unique = true, length = 60)
    private String username;

    /**
     * Correo electronico unico del usuario.
     */
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    /**
     * Contrasena del usuario cifrada con BCrypt.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Fecha y hora en que el usuario se registro en el sistema.
     */
    @Column(nullable = false)
    private LocalDateTime fechaRegistro;
}
