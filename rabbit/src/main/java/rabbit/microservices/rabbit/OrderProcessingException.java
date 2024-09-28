package rabbit.microservices.rabbit;

public class OrderProcessingException extends RuntimeException {
    public OrderProcessingException(String message) {
        super(message);
    }
}
