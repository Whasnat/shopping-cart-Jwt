package Cart.Cart.dto;

import Cart.Cart.model.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productNameDto;

    private int quantityDto;

    private double priceDto;

    private String productDetailsDto;

    private double ratingDto;

    private int shopIdDto;
}
