package Cart.Cart.controller;

import Cart.Cart.dto.CartDto;
import Cart.Cart.dto.ProductDto;
import Cart.Cart.model.Cart;
import Cart.Cart.service.ProductService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }
    @GetMapping("/get-product/{productId}")
    public ResponseEntity<?> getProduct(@PathVariable Long productId){
        return productService.getProduct(productId);
    }

    @DeleteMapping("/delete-product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        return productService.deleteProduct(productId);
    }
    @PutMapping("/update-product/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId,
                                           @RequestBody ProductDto productDto){
        return productService.updateProduct(productDto, productId);
    }

    @PostMapping("/add-product-to-cart/{productId}")
    public ResponseEntity<?> addProdcutToCart(@PathVariable Long productId,
                                              @RequestBody CartDto cartDto){
        return productService.addProductToCart(productId, cartDto);
    }
}
