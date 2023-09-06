package com.websocket.chatsocket.Controller;

import com.websocket.chatsocket.Model.ModelChat;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerSocket {

    @MessageMapping("chat.sendmessage")
    @SendTo("/enablebroker/public")
    public ModelChat sendMessage(ModelChat message){
        //se retorna el mensage que llegue a esa ruta
        return message;
    }

    @MessageMapping("chat.addUser")
    @SendTo("/enablebroker/public")
    public ModelChat addUser(ModelChat message, SimpMessageHeaderAccessor accessor){
        //agregando el usuario
        accessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }

}
