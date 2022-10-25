package Cart.Cart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "cart")
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Customer customer;

    /*@ManyToOne
    private CartProduct cartPro;*/

    /*@ManyToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<Product> product;

    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<CartProduct> cartProducts = new ArrayList<>();*/

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<CartProduct> cartProductList;

    private String deliveryAddress;
    private LocalDateTime createDate;
    private char status;

    @ManyToOne
    private Order order;

}
