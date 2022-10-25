package Cart.Cart.service;

import Cart.Cart.dto.CartDto;
import Cart.Cart.dto.ProductDto;
import Cart.Cart.model.*;
import Cart.Cart.rapository.*;
import Cart.Cart.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ShopRepository shopRepository;

    private final CartRepository cartRepository;

    private final CustomerRepository customerRepository;

    private final CartProductRepository cartProductRepository;

    private final Response response = new Response();

    /*public ProductService(ProductRepository productRepository,
                          ShopRepository shopRepository,
                          CartRepository cartRepository,
                          CustomerRepository customerRepository,
                          CartProductRepository cartProductRepository) {

        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.cartProductRepository = cartProductRepository;
    }*/

    public ResponseEntity<?> addProduct(ProductDto productDto){
        Product product = new Product();

        product.setProductName(productDto.getProductNameDto());
        product.setProductDetails(productDto.getProductDetailsDto());
        product.setPrice(productDto.getPriceDto());
        product.setQuantity(productDto.getQuantityDto());
        product.setRating(productDto.getRatingDto());
        product.setProductStatus('1');

        Shop shop = new Shop();
        shop = shopRepository.findById((long) productDto.getShopIdDto()).get();

        if(shop.getId() < 1){
            response.setMessage("Shop not found!");
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(product);
            return ResponseEntity.badRequest().body(response);

        }else{
            product.setShop(shop);
            //product.setShop(shop.get());
            try{
                productRepository.save(product);
            }catch(Exception e){
                e.printStackTrace();
                response.setMessage(e.getMessage());
                response.setStatus("BAD_REQUEST");
                response.setSuccessData(null);
                response.setErrorData(product);
                return ResponseEntity.badRequest().body(response);
            }

            response.setMessage("Product add successfully");
            response.setStatus("GOOD_REQUEST");
            response.setSuccessData(product);
            response.setErrorData(null);
            return ResponseEntity.accepted().body(response);
        }

    }

    public ResponseEntity<?> getProduct(Long productId) {
        try{
            Optional<Product> product =  productRepository.findById(productId);
            if(product.isEmpty()){
                response.setMessage("Product Not Found");
                response.setStatus("BAD_REQUEST");
                response.setSuccessData(null);
                response.setErrorData(null);
                return ResponseEntity.badRequest().body(response);
            }else{
                response.setMessage("Product Found!");
                response.setStatus("GOOD_REQUEST");
                response.setSuccessData(product);
                response.setErrorData(null);
                return ResponseEntity.accepted().body(response);
            }
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    public ResponseEntity<?> deleteProduct(Long productId) {
        try{
            productRepository.deleteById(productId);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response.setMessage("Product Delete Successfully!");
        response.setStatus("GOOD_REQUEST");
        response.setSuccessData(null);
        response.setErrorData(null);
        return ResponseEntity.accepted().body(response);
    }

    public ResponseEntity<?> updateProduct(ProductDto productDto, Long productId) {
        Product product = productRepository.findById(productId).get();

        if(productDto.getProductNameDto() != null){
            product.setProductName(product.getProductName());
        }
        if(productDto.getProductDetailsDto() != null){
            product.setProductDetails(productDto.getProductDetailsDto());
        }
        if(productDto.getRatingDto() >= 0){
            product.setRating(productDto.getRatingDto());
        }
        if(productDto.getPriceDto() >= 0){
            product.setPrice(productDto.getPriceDto());
        }
        if(productDto.getQuantityDto() >= 0){
            product.setQuantity(productDto.getQuantityDto());
        }
        if(productDto.getShopIdDto() > 0){
            Optional<Shop> shop = shopRepository.findById((long) productDto.getShopIdDto());
            if(shop.isEmpty()){
                response.setMessage("Shop not found!");
                response.setStatus("BAD_REQUEST");
                response.setSuccessData(null);
                response.setErrorData(productDto);
                return ResponseEntity.badRequest().body(response);

            }else{
                product.setShop(shop.get());
            }

        }

        try{
            productRepository.save(product);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(null);
            return ResponseEntity.badRequest().body(response);
        }

        response.setMessage("Product update Successfully!");
        response.setStatus("GOOD_REQUEST");
        response.setSuccessData(productDto);
        response.setErrorData(null);
        return ResponseEntity.accepted().body(response);
    }

    public ResponseEntity<?> addProductToCart(Long productId, CartDto cartDto) {

        Cart cart = new Cart();

        cart.setDeliveryAddress(cartDto.getDeliveryAddressDto());
        cart.setCreateDate(LocalDateTime.now());
        cart.setStatus('1');

        Customer customer = customerRepository.findById(cartDto.getCustomerIdDto()).get();

        cart.setCustomer(customer);

        Cart newCart = new Cart();

        try{
            newCart = cartRepository.save(cart);
            /*return ResponseEntity.accepted().body(new Response("GOOD_REQUEST",
                    "Successfully added in cart",
                    cartDto,
                    null));*/
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new Response("BAD_REQUEST", e.getMessage(), null,e.getCause()));
        }

        Product product = productRepository.findById(cartDto.getProductIdDto()).get();

        CartProduct cartProduct = new CartProduct();

        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(cartDto.getQuantityDto());

        try{
            cartProductRepository.save(cartProduct);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new Response("BAD_REQUEST", e.getMessage(), null,e.getCause()));
        }

        return ResponseEntity.accepted().body(new Response("GOOD_REQUEST",
                                                        "Successfully added in cart",
                                                        cartDto,
                                                        null));

    }
}
