package Projet;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttClient {
    public static void main(String[] args) {
        String broker = "tcp://mqtt.eclipse.org:1883";
        String clientId = "ExampleClient";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            MqttClient client = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            client.connect(connOpts);

            // À ce stade, le client MQTT est connecté au serveur MQTT.

            // Vous pouvez maintenant publier des messages, vous abonner à des sujets, etc.

            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
