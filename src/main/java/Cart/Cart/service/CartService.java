package Cart.Cart.service;

import Cart.Cart.model.Cart;
import Cart.Cart.model.CartProduct;
import Cart.Cart.model.Product;
import Cart.Cart.rapository.CartProductRepository;
import Cart.Cart.rapository.CartRepository;
import Cart.Cart.rapository.ProductRepository;
import Cart.Cart.response.Response;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {
    private final Response response = new Response();
    private final CartRepository cartRepository;

    private final ProductRepository productRepository;
    private final CartProductRepository cartProductRepository;
    public ResponseEntity<?> showCart(Long cartId) {

        try {

            Cart cart = new Cart();
            cart = cartRepository.findById(cartId).get();
            if(cart.getId() == null){
                response.setMessage("Cart not found!");
                response.setStatus("BAD_REQUEST");
                response.setErrorData(null);
                response.setSuccessData(null);
                return ResponseEntity.badRequest().body(response);
            }else{
                response.setMessage("Cart Found");
                response.setStatus("GOOD_REQUEST");
                response.setErrorData(null);
                response.setSuccessData(cart);

                return ResponseEntity.accepted().body(response);

            }
        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setErrorData(e.getCause());
            response.setSuccessData(null);
            return ResponseEntity.badRequest().body(response);
        }

    }

    public ResponseEntity<?> addProduct(Long cartId, Long productId, Integer quantity) {
        CartProduct cartProduct = new CartProduct();
        Cart cart = new Cart();
        try {
            cart = cartRepository.findById(cartId).get();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new Response("BAD_REQUEST", e.getMessage(), null,e.getCause()));
        }

        Product product = new Product();
        try {
            product = productRepository.findById(productId).get();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new Response("BAD_REQUEST", e.getMessage(), null,e.getCause()));
        }

        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(quantity);

        try{
            cartProductRepository.save(cartProduct);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new Response("BAD_REQUEST", e.getMessage(), null,e.getCause()));
        }

        return ResponseEntity.accepted().body(new Response("GOOD_REQUEST", "Product add to cart successfully", cartProduct, null));
    }
}
