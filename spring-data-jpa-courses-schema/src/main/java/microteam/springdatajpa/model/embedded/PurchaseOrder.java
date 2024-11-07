package microteam.springdatajpa.model.embedded;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase_order")
public class PurchaseOrder {

    @EmbeddedId
    private OrderId id;
    @Embedded
    private Address address;
    private String orderInfo;
    private String customerName;
}
