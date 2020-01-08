package com.rz.frame.entityChat;

public enum MessageType {
    Running(0),
    Connect(1),
    ConfirConnect(2),
    ConfirmRecieve(3),
    Disconnect(4);
    private int code;

    MessageType(int code) {
        this.code = code;
    }

    private int getValue() {
        return code;
    }

    public static MessageType getMessageType(String typeName){
        for (MessageType mt :MessageType.values()) {
            if(mt.toString().equals(typeName.trim())){
                return mt;
            }

        }
        return null;
    }
}
