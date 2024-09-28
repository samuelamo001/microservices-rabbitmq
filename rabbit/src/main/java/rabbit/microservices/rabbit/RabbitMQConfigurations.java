package rabbit.microservices.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfigurations {

    @Value("${spring.rabbitmq.queue}")
    public  String orderQueue;
    @Value("${spring.rabbitmq.exchange}")
    public String orderExchange;
    @Value("${spring.rabbitmq.routing-key}")
    public String orderRoutingKey;

    @Bean
    public Queue queue(){
        return new Queue(orderQueue,false);
    }
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(orderExchange);
    }
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(orderRoutingKey);
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
