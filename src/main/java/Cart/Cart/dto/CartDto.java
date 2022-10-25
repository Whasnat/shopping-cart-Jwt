package Cart.Cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private String deliveryAddressDto;
    private Long productIdDto;
    private Integer quantityDto;
    private Long customerIdDto;
}
