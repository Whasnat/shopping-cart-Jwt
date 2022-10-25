package Cart.Cart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "payments")
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private Character type;
    private  Character status;
    private Date created;
    private Long customerId;

    @OneToOne
    private Order order;

    @OneToOne
    private Invoice invoice;
}