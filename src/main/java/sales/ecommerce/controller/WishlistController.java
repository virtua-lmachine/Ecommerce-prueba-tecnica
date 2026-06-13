package sales.ecommerce.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sales.ecommerce.dto.WishlistAddRequest;
import sales.ecommerce.dto.WishlistResponse;
import sales.ecommerce.dto.WishlistUpdateRequest;
import sales.ecommerce.entity.User;
import sales.ecommerce.security.JwtAuthenticationFilter;
import sales.ecommerce.service.WishlistService;

import java.util.List;

/**
 * Controlador REST que administra la lista de deseos del usuario autenticado.
 */
@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    /**
     * Crea el controlador de lista de deseos.
     * wishlistService servicio con la logica de gestion de deseos
     */
    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    /**
     * Consulta los productos guardados en la lista de deseos del usuario actual.
     * currentUser usuario autenticado agregado por el filtro JWT
     */
    @GetMapping
    public List<WishlistResponse> findByCurrentUser(
            @RequestAttribute(JwtAuthenticationFilter.CURRENT_USER_ATTRIBUTE) User currentUser
    ) {
        return wishlistService.findByUser(currentUser);
    }

    /**
     * Agrega un producto a la lista de deseos del usuario actual.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WishlistResponse add(
            @RequestAttribute(JwtAuthenticationFilter.CURRENT_USER_ATTRIBUTE) User currentUser,
            @Valid @RequestBody WishlistAddRequest request
    ) {
        return wishlistService.add(currentUser, request);
    }

    /**
     * Actualiza la cantidad deseada de un producto en la lista del usuario actual
     */
    @PutMapping("/{productId}")
    public WishlistResponse updateQuantity(
            @RequestAttribute(JwtAuthenticationFilter.CURRENT_USER_ATTRIBUTE) User currentUser,
            @PathVariable Long productId,
            @Valid @RequestBody WishlistUpdateRequest request
    ) {
        return wishlistService.updateQuantity(currentUser, productId, request);
    }

    /**
     * Elimina un producto de la lista de deseos del usuario actual.
     */
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @RequestAttribute(JwtAuthenticationFilter.CURRENT_USER_ATTRIBUTE) User currentUser,
            @PathVariable Long productId
    ) {
        wishlistService.delete(currentUser, productId);
    }
}
