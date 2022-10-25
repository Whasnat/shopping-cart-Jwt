package Cart.Cart.controller;

import Cart.Cart.dto.CustomerDto;
import Cart.Cart.response.Response;
import Cart.Cart.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    private final Response response = new Response();

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create-customer")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto){
        return customerService.createCustomer(customerDto);
    }

    /*@GetMapping("/login-customer")
    public ResponseEntity<?> loginCustomer(@RequestBody String email, String password){
        return customerService.loginCustomer(email, password);
    }*/

    @GetMapping(path = "/get-customer/{customerId}")
    public ResponseEntity<?> getCustomerById(@PathVariable("customerId") Long customerId){
        return customerService.getCustomerById(customerId);
    }

    @DeleteMapping(path = "delete-customer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") Long customerId){
        return customerService.deleteCustomer(customerId);
    }

    @PutMapping(path = "/update-customer/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable("customerId") Long customerId,
                                            @RequestBody CustomerDto customerDto
                                            /*@RequestParam(required = false) String customerDob,
                                            @RequestParam(required = false) String customerEmail,
                                            @RequestParam(required = false) String customerContact,
                                            @RequestParam(required = false) String customerAddress*/){
        return customerService.updateCustomer(customerId, customerDto/*, customerDob, customerEmail,customerContact, customerAddress*/);
    }

}
