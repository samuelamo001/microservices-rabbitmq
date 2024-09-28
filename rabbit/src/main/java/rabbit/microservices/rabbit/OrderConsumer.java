package rabbit.microservices.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final OrderRepository orderRepository;
    private final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    public OrderConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void processOrder(Order order) {
        logger.info("Order received and saving to database {}", order.toString());

        orderRepository.save(order);
    }
}
