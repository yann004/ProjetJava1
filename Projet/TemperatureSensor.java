package Projet;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class TemperatureSensor {
    public static void main(String[] args) {
        String broker = "tcp://test.mosquitto.org:1883";
        String clientId = "TemperatureSensor";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            client.connect(connOpts);

            // Simulation de la lecture de la température
            
            double temperature = 25.5;

            // Envoi de la température au serveur MQTT
            String topic = "temperature";
            String content = Double.toString(temperature);
            client.publish(topic, new MqttMessage(content.getBytes()));

            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

