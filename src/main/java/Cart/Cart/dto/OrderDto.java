package Cart.Cart.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
//
// @Id
// @GeneratedValue(GenerationType=GenerationType.AUTO);

 private String orderDetails;
 private String customerName;
 private String deliveryAddress;
 private Double totalPayable;
 private Integer quantity;
 private Character status;


}
