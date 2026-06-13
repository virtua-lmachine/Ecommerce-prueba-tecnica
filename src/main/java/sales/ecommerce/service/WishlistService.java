package sales.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sales.ecommerce.dto.WishlistAddRequest;
import sales.ecommerce.dto.WishlistResponse;
import sales.ecommerce.dto.WishlistUpdateRequest;
import sales.ecommerce.entity.Product;
import sales.ecommerce.entity.User;
import sales.ecommerce.entity.Wishlist;
import sales.ecommerce.exception.BadRequestException;
import sales.ecommerce.exception.ResourceNotFoundException;
import sales.ecommerce.mapper.WishlistMapper;
import sales.ecommerce.repository.ProductRepository;
import sales.ecommerce.repository.WishlistRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Servicio que administra la lista de deseos de los usuarios.
 */
@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;

    /**
     * Crea el servicio de lista de deseos.
     */
    public WishlistService(WishlistRepository wishlistRepository, ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
    }

    /**
     * Obtiene la lista de deseos asociada a un usuario.
     */
    @Transactional(readOnly = true)
    public List<WishlistResponse> findByUser(User user) {
        return wishlistRepository.findByUsuarioId(user.getId())
                .stream()
                .map(WishlistMapper::toResponse)
                .toList();
    }

    /**
     * Agrega un producto a la lista de deseos del usuario.
     */
    @Transactional
    public WishlistResponse add(User user, WishlistAddRequest request) {
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        if (product.getCantidadStock() <= 0) {
            throw new BadRequestException("No se puede agregar un producto sin stock");
        }
        if (wishlistRepository.existsByUsuarioIdAndProductoId(user.getId(), product.getId())) {
            throw new BadRequestException("El producto ya esta en la lista de deseos");
        }

        Wishlist wishlist = Wishlist.builder()
                .usuario(user)
                .producto(product)
                .cantidadDeseada(request.cantidadDeseada())
                .fechaAgregado(LocalDateTime.now())
                .build();

        return WishlistMapper.toResponse(wishlistRepository.save(wishlist));
    }

    /**
     * Actualiza la cantidad deseada de un producto existente en la lista de deseos.
     */
    @Transactional
    public WishlistResponse updateQuantity(User user, Long productId, WishlistUpdateRequest request) {
        Wishlist wishlist = wishlistRepository.findByUsuarioIdAndProductoId(user.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado en la lista de deseos"));

        wishlist.setCantidadDeseada(request.cantidadDeseada());
        return WishlistMapper.toResponse(wishlistRepository.save(wishlist));
    }

    /**
     * Elimina un producto de la lista de deseos del usuario.
     */
    @Transactional
    public void delete(User user, Long productId) {
        Wishlist wishlist = wishlistRepository.findByUsuarioIdAndProductoId(user.getId(), productId)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado en la lista de deseos"));
        wishlistRepository.delete(wishlist);
    }
}
