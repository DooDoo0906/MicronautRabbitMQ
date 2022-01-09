package MicronautRabbitMQ.consumer;

import MicronautRabbitMQ.ultiliities.calculator.Calculator;
import MicronautRabbitMQ.ultiliities.validation.Validation;
import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RabbitListener
public class Listener {

    List<String> messageLengths = Collections.synchronizedList(new ArrayList<>());

    @Queue("abc")
    public void receive(JsonObject message) {
        if (Validation.isNumeric(String.valueOf(message.getValue("a"))) && Validation.isNumeric(String.valueOf(message.getValue("b")))) {
            switch (Validation.validateOpe(message.getDouble("b"), message.getString("ope"))) {
                case 1:
                    double result = Calculator.operation(message.getDouble("a"), message.getDouble("b"), message.getString("ope"));
                    System.out.println(" Value: \n" + message.encodePrettily());
                    System.out.println("Result: " + result);
                    break;
                case 0:
                    System.out.println(" Value: \n" + message.encodePrettily());
                    System.out.println("You can't put 0 under the denominator");
                    break;
                case -1:
                    System.out.println(" Value: \n" + message.encodePrettily());
                    System.out.println("Please enter the right operation (+, -, x, :)");
                    break;
            }
        } else {
            System.out.println("Error");
        }
    }
}