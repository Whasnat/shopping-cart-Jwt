package Cart.Cart.service;

import Cart.Cart.dto.CustomerDto;
import Cart.Cart.model.Customer;
import Cart.Cart.rapository.CustomerRepository;
import Cart.Cart.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final Response response = new Response();

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ResponseEntity<?> getCustomerById(Long id) {
        try{
            Optional<Customer> customerReturn = customerRepository.findById(id);

            if(customerReturn.isEmpty()){
                response.setMessage("BAD_REQUEST");
                response.setStatus("Customer Not Found");
                response.setSuccessData(null);
                response.setErrorData(null);
                return ResponseEntity.badRequest().body(response);
            }else{
                response.setMessage("Customer_Found");
                response.setStatus("GOOD_REQUEST");
                response.setSuccessData(customerReturn);
                response.setErrorData(null);
                return ResponseEntity.accepted().body(response);
            }
        }catch(Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    public ResponseEntity<?>  createCustomer(CustomerDto customerDto){
        Customer customer = new Customer();

        customer.setCustomerName(customerDto.getCustomerNameDto());
        customer.setPassword(customerDto.getCustomerPasswordDto());
        customer.setCustomerAddress(customerDto.getCustomerAddressDto());
        customer.setCustomerContact(customerDto.getCustomerContactDto());
        customer.setCustomerDob(customerDto.getCustomerDobDto());
        customer.setCustomerEmail(customerDto.getCustomerEmailDto());
        customer.setCustomerCreateDate(LocalDateTime.now());
        customer.setCustomerStatus("1");

        try{
            customerRepository.save(customer);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(customerDto);
            return ResponseEntity.badRequest().body(response);
        }

        response.setMessage("Customer create successfully");
        response.setStatus("GOOD_REQUEST");
        response.setSuccessData(customerDto);
        response.setErrorData(null);
        return ResponseEntity.accepted().body(response);


    }

    public ResponseEntity<?> deleteCustomer(Long customerId) {
        try{
            customerRepository.deleteById(customerId);

            response.setMessage("Customer Delete Successfully!");
            response.setStatus("GOOD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(null);

            return ResponseEntity.accepted().body(response);
        }catch(Exception e){
            response.setMessage("Customer deleted not successfully");
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }

    public ResponseEntity<?> updateCustomer(Long customerId, CustomerDto customerDto/*, String customerDob, String customerEmail, String customerContact, String customerAddress*/) {
        /*int check = customerRepository.updateCustomer(customerId,  customerName*//*, customerDob,  customerEmail, customerContact,customerAddress*//*);
        if(check == 0){
            response.setMessage("Customer updated Not Successfully");
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(null);

            return ResponseEntity.badRequest().body(response);
        }else{
            response.setMessage("Customer updated Successfully");
            response.setStatus("GOOD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(null);

            return ResponseEntity.accepted().body(response);
        }*/

        Customer customer = customerRepository.findById(customerId).get();

        if(customerDto.getCustomerNameDto() != null){
            customer.setCustomerName(customerDto.getCustomerNameDto());
        }
        if (customerDto.getCustomerAddressDto() != null) {
            customer.setCustomerAddress(customerDto.getCustomerAddressDto());
        }
        if (customerDto.getCustomerDobDto() != null) {
            customer.setCustomerDob(customerDto.getCustomerDobDto());
        }
        if (customerDto.getCustomerContactDto() != null) {
            customer.setCustomerContact(customerDto.getCustomerContactDto());
        }
        if (customerDto.getCustomerEmailDto() != null) {
            customer.setCustomerEmail(customerDto.getCustomerEmailDto());
        }
        try{
            customerRepository.save(customer);
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("BAD_REQUEST");
            response.setSuccessData(null);
            response.setErrorData(null);
            return ResponseEntity.badRequest().body(response);
        }
        response.setMessage("Customer updated Successfully");
        response.setStatus("GOOD_REQUEST");
        response.setSuccessData(customer);
        response.setErrorData(null);
        return ResponseEntity.accepted().body(response);
    }

    /*public ResponseEntity<?> loginCustomer(String email, String password) {
        Optional<Customer> customerReturn = customerRepository.findById(1L);
        if(!customerReturn.isPresent()){
            response.setMessage("Customer not found!");
            response.setStatus("204");
            response.setSuccessData(null);
            response.setErrorData("Email: "+email+"\n Password: "+password);

            return ResponseEntity.badRequest().body(response);
        }else{
            response.setMessage("Successfully Login");
            response.setStatus("200");
            response.setSuccessData(customerReturn);
            response.setErrorData(null);

            return ResponseEntity.accepted().body(response);
        }
    }*/
}
