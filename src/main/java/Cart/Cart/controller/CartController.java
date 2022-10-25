package Cart.Cart.controller;

import Cart.Cart.model.CartProduct;
import Cart.Cart.model.Product;
import Cart.Cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/cart")
public class CartController {
    public final CartService cartService;

    @GetMapping("/show-cart/{cartId}")
    public ResponseEntity<?> showCart(@PathVariable("cartId") Long cartId){
        return cartService.showCart(cartId);
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody Long cartId, Long productId, Integer quantity){
        return cartService.addProduct(cartId,productId, quantity);
    }
}
