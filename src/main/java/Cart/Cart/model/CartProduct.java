package Cart.Cart.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "CartProduct")
@Table(name = "CartProduct")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProduct {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@SequenceGenerator(
            name = "cartProduct_sequence",
            sequenceName = "cartProduct_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cartProduct_sequence"
    )*/
    private Long id;

//    @OneToMany(mappedBy = "productCa", cascade = CascadeType.ALL)
//    private List<Product> productCa;
////    private Product product;
//
//    @OneToMany(mappedBy = "cartPro", cascade = CascadeType.ALL)
//    private List<Cart> cartPro;
    /*@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    private Cart cart;*/

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference
    private Cart cart;

    @Column(name = "quantity")
    private Integer quantity;
}
