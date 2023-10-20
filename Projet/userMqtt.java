package Projet;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class TemperatureSubscriber {
    public static void main(String[] args) {
        String broker = "tcp://mqtt.eclipse.org:1883";
        String clientId = "TemperatureSubscriber";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection to MQTT broker lost!");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Received message on topic: " + topic);
                    System.out.println("Message: " + new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                }
            });

            client.connect(connOpts);
            client.subscribe("temperature");

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

