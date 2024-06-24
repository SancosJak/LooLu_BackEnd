package loolu.loolu_backend.services;

import loolu.loolu_backend.dto.CartItemDto;
import loolu.loolu_backend.dto.CartProductDto;
import loolu.loolu_backend.dto.UpdateCartItemDto;
import loolu.loolu_backend.models.CartProduct;

import java.util.List;

public interface CartService {


    List<CartProductDto> getCartProductsDtoByCartId(Long cartId);
    CartItemDto addItemToCart(CartItemDto cartItem);

    List<CartItemDto> getCartItems();

    CartItemDto deleteItemFromCart(Long cartId, Long itemId);

    CartItemDto clearCart();

    CartItemDto updateCartItem(Long cartId, Long itemId, UpdateCartItemDto cartItem);

    List<CartProductDto> getCartProductsDtoByCartId(Long cartId);

}
