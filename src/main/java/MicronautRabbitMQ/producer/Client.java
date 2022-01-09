package MicronautRabbitMQ.producer;

import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;
import io.vertx.core.json.JsonObject;

@RabbitClient
public interface Client {

    @Binding("abc")
    void send(JsonObject data);
}