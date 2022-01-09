package MicronautRabbitMQ;

import MicronautRabbitMQ.producer.Client;
import io.micronaut.runtime.Micronaut;
import io.vertx.core.json.JsonObject;

public class Application {

    public static void main(String[] args) {
        Client productClient;
        JsonObject message= new JsonObject();
        message.put("a",9*10);
        message.put("b",0);
        message.put("ope",":");
        productClient=Micronaut.run(Application.class, args).getBean(Client.class);
        productClient.send(message);
    }
}
