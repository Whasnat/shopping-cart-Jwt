package Cart.Cart.controller;

import Cart.Cart.dto.ShopDto;
import Cart.Cart.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/create-shop")
    public ResponseEntity<?> createShop(@RequestBody ShopDto shopDto){
        return shopService.createShop(shopDto);
    }

    @GetMapping("/get-shop/{shopId}")
    public ResponseEntity<?> getShop(@PathVariable Long shopId){
        return shopService.getShop(shopId);
    }

    @DeleteMapping("/delete-shop/{shopId}")
    public ResponseEntity<?> deleteShop(@PathVariable Long shopId){
        return shopService.deleteShop(shopId);
    }

    @PutMapping("/update-shop/{shopId}")
    public ResponseEntity<?> updateShop(@PathVariable Long shopId,
                                        @RequestBody ShopDto shopDto){
        return shopService.updateShop(shopDto, shopId);
    }

}
