package com.miprojecto.esp32.api.conexionesp32.services;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    private final String brokerUrl = "tcp://broker.hivemq.com:1883";  // URL del broker MQTT

    public void sendLoginMessage(String userName) {
        try {
            MqttClient client = new MqttClient(brokerUrl, MqttClient.generateClientId());
            client.connect();

            String topic = "sesiones/inicio";
            String message = "Sesion iniciada por el usuario: " + userName;

            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(2);  // Calidad del servicio
            client.publish(topic, mqttMessage);

            client.disconnect();
        } catch (MqttException e) {
            e.getMessage();
        }
    }
}
