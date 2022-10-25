package Cart.Cart.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDto {
    private String customerNameDto;

    private String customerPasswordDto;

    private String customerEmailDto;

    private String customerContactDto;

    private String customerAddressDto;

    private LocalDate customerDobDto;
}
