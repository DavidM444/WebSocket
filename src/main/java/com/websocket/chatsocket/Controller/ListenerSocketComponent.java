package com.websocket.chatsocket.Controller;

import com.websocket.chatsocket.Model.ModelChat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class ListenerSocketComponent {
    //clase componenete eque encarga que hacer cuando haya una accion como que entro a un chat o salioa
    //crear un objeto de registro (logger) que se utiliza para registrar mensajes y eventos en una aplicación Java.
    public static Logger logger = LoggerFactory.getLogger(ListenerSocketComponent.class);

    @Autowired
    private SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void HandleSocketConnect(SessionConnectedEvent connectedEvent){
        logger.info("se ha conectado un nuevo usuario");
    }
    @EventListener
    public void HandleSocketDisconnect(SessionDisconnectEvent eventoDesconect){
        System.out.println("se ha desconectado un usuario");
        System.out.println("cosas de descontar: getMessage= " +eventoDesconect.getMessage()+
                " sesionId= "+eventoDesconect.getSessionId()+ "  closestatus= "+ eventoDesconect.getCloseStatus());
        //sacando como los datos del usuario asociado al mensaje stomp
        StompHeaderAccessor headeracceso = StompHeaderAccessor.wrap(eventoDesconect.getMessage());
        //nombre de usuario del evento que se ha realzado
        String usuario =(String)headeracceso.getSessionAttributes().get("username");
        System.out.println("nombre del usuario scado -> "+usuario);


        //validacion de si el usaurio no es nulo
        if (usuario!=null){
            logger.info("usaurio ha sido desconectaddo "+usuario);
            System.out.println("usaurio desconectado en el if pq no es nulo");

            //creacion objeto Modelchat para informar a otros usuairos de la desconexion de este uusauiro
            ModelChat chat = new ModelChat();
            //pasamos que el usuario salio y le seteamos su tiopo a leave
            chat.setTipo(ModelChat.MessageType.LEAVE);
            //editamos el destinario con el nombre de quien se desconecto
            chat.setSender(usuario);

            //usamos el template para enviar la info al broker de mensajeria a su ruta
            messageTemplate.convertAndSend("/enablebroker/public",chat);

        }

    }


    // extraer el nombre de usuario de un mensaje STOMP
    // (Simple Text Oriented Messaging Protocol) en una aplicación de chat o mensajería en tiempo real.

}
