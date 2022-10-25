package Cart.Cart.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "invoices")
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String details;
    private Date created;

//    @Column(name = product_id)
    private Long productId;

    @OneToOne
    private Payment payment;


}