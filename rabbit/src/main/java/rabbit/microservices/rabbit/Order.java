package rabbit.microservices.rabbit;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name = "orders")
public class Order {
    @Id
    private String orderId;
    private String productName;
    private int quantity;
}
