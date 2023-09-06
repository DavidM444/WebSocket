package com.websocket.chatsocket.Model;

import org.springframework.messaging.handler.MessagingAdviceBean;

public class ModelChat {
    //clase con los atributos como el mensaje, enviador y estado de la conexion del usuario
    private MessageType tipo;
    private String sender;
    private String content;

    public enum MessageType{
        CHAT,
        JOIN,
        LEAVE
    }
    //generando geters u setters
    public MessageType getTipo(){
        return tipo;
    }
    public void setTipo(MessageType tipo){
        this.tipo = tipo;
    }
    public String getSender(){
        return sender;
    }
    public void setSender(String sender){
        this.sender = sender;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }

}
