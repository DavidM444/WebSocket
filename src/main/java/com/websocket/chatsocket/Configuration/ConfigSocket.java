package com.websocket.chatsocket.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
//interfaz websocket... usado para etablecer la infraestructura necesario para haabilitar la comunicacion web socket
public class ConfigSocket implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //agrego un endopoint o puento de conexion para el socket. y se habilita sockjs que es una libreria para permitir la comunicacion en tiempo real
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*El broker es el encargado de distribuir los mensajes entre diferentes clientes y servidores
        se configura el broker con ruta app como para que los mensajes de tipo app o general vayan a esa ruta. Tambien
        se configura otro broker simple llamado enablebroker. Con esto los mensajes enviados a app son de contexto de la app, mientras que
        en enablebroker solo seran para queienes esten conectados a ese destino */
        registry.setUserDestinationPrefix("/app");
        registry.enableSimpleBroker("/enablebroker");
    }
}
