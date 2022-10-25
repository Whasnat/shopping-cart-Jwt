package Cart.Cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartProductDto {
    private Integer quantity;
    private Long cartId;
    private Long productId;
}
