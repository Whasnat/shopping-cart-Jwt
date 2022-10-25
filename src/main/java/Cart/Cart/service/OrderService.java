package Cart.Cart.service;


import Cart.Cart.rapository.CartRepository;
import Cart.Cart.rapository.OrderRepository;
import Cart.Cart.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    private Response response = new Response();

    private CartRepository cartRepository;



    public ResponseEntity<?> showOrderList(){
        return ResponseEntity.badRequest().body(response);
    }




}
