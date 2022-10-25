package Cart.Cart.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response {
    private String status;
    private String message;
    private Object successData;
    private Object errorData;
}
