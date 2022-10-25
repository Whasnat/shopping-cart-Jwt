package Cart.Cart.service;

import Cart.Cart.dto.ShopDto;
import Cart.Cart.model.Shop;
import Cart.Cart.rapository.ShopRepository;
import Cart.Cart.response.Response;
import ch.qos.logback.core.encoder.EchoEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ShopService {

    private final ShopRepository shopRepository;

    private final Response response = new Response();

    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public ResponseEntity<?> createShop(ShopDto shopDto){
        Shop shop = new Shop();

        shop.setShopAddress(shopDto.getShopAddressDto());
        shop.setShopContact(shopDto.getShopContactDto());
        shop.setShopDetails(shopDto.getShopDetailsDto());
        shop.setShopEmail(shopDto.getShopEmailDto());
        shop.setShopName(shopDto.getShopNameDto());
        shop.setShopCreateDate(LocalDateTime.now());
        shop.setShopStatus('1');

        try{
            shopRepository.save(shop);
        }catch(Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(shop);
            return ResponseEntity.badRequest().body(response);
        }

        response.setMessage("Shop create successfully");
        response.setStatus("GOOD_REQUEST");
        response.setSuccessData(shop);
        response.setErrorData(null);
        return ResponseEntity.accepted().body(response);
    }

    public ResponseEntity<?> getShop(Long shopId) {
        try{
           Optional<Shop> shop =  shopRepository.findById(shopId);
           if(shop.isEmpty()){
               response.setMessage("Shop Not Found!");
               response.setStatus("BAD_REQUEST");
               response.setSuccessData(null);
               response.setErrorData(null);
               return ResponseEntity.badRequest().body(response);
           }else{
               response.setMessage("Shop found");
               response.setStatus("GOOD_REQUEST");
               response.setSuccessData(shop);
               response.setErrorData(null);
               return ResponseEntity.accepted().body(response);
           }
        }catch(Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    public ResponseEntity<?> deleteShop(Long shopId) {
        try{
            shopRepository.deleteById(shopId);
            response.setMessage("Shop found");
            response.setStatus("GOOD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(null);
            return ResponseEntity.accepted().body(response);

        }catch(Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    public ResponseEntity<?> updateShop(ShopDto shopDto, Long shopId) {
        Shop ss = shopRepository.findById(shopId).get();//TODO: clear the understanding of Optional to shop conversion
        if(shopDto.getShopNameDto() != null){
            ss.setShopName(shopDto.getShopNameDto());
        }
        if(shopDto.getShopAddressDto() != null){
            ss.setShopAddress(shopDto.getShopAddressDto());
        }
        if(shopDto.getShopContactDto()!= null){
            ss.setShopContact(shopDto.getShopContactDto());
        }
        if(shopDto.getShopDetailsDto() != null){
            ss.setShopDetails(shopDto.getShopDetailsDto());
        }
        if(shopDto.getShopEmailDto() != null){
            ss.setShopEmail(shopDto.getShopEmailDto());
        }

        try{
            shopRepository.save(ss);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(null);
            return ResponseEntity.badRequest().body(response);
        }
        response.setMessage("Shop updated Successfully");
        response.setStatus("GOOD_REQUEST");
        response.setSuccessData(shopDto);
        response.setErrorData(null);
        return ResponseEntity.accepted().body(response);

        /*String qry = new String();
        if(shopDto.getShopNameDto() != null){
            qry += " shop_name = '"+shopDto.getShopNameDto() + "', ";
        }
        if(shopDto.getShopAddressDto() != null){
            qry += " shop_address = '"+shopDto.getShopAddressDto()+ "', ";
        }
        if(shopDto.getShopContactDto()!= null){
            qry += " shop_contact = '"+shopDto.getShopContactDto()+ "', ";
        }
        if(shopDto.getShopDetailsDto() != null){
            qry += " shop_details = '"+shopDto.getShopDetailsDto()+ "', ";
        }
        if(shopDto.getShopEmailDto() != null){
            qry += " shop_email = '"+shopDto.getShopEmailDto()+ "', ";
        }

        qry += " shop_status = 1";*/

        /*int check = shopRepository.updateShop(qry, shopId);

        if(check == 0){
            response.setMessage("Shop Not Found!");
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(shopDto);
            return ResponseEntity.badRequest().body(response);
        }else{
            response.setMessage("Shop updated Successfully");
            response.setStatus("GOOD_REQUEST");
            response.setSuccessData(shopDto);
            response.setErrorData(null);
            return ResponseEntity.accepted().body(response);
        }*/
    }
}
