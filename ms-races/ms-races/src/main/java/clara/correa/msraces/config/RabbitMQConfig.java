package clara.correa.msraces.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String RACE_QUEUE = "raceQueue";

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public RabbitAdmin rabbitAdmin() {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue raceQueue() {
        RabbitAdmin admin = rabbitAdmin();
        Queue raceQueue = new Queue(RACE_QUEUE, true);
        admin.declareQueue(raceQueue);
        return raceQueue;
    }
}