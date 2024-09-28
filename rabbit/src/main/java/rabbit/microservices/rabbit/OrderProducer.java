package rabbit.microservices.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConfigurations rabbitMQConfigurations;

    public OrderProducer(RabbitTemplate rabbitTemplate, RabbitMQConfigurations rabbitMQConfigurations) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitMQConfigurations = rabbitMQConfigurations;
    }

    @PostMapping
    public String sendOrder(@RequestBody Order order) {
        if (order == null || order.getOrderId() == null) {
            throw new OrderProcessingException("Invalid Order Data");
        }
        rabbitTemplate.convertAndSend(rabbitMQConfigurations.orderExchange, rabbitMQConfigurations.orderRoutingKey, order);
        return "Order sent successfully";
    }
}
