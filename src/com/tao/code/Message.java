package com.tao.code;

import java.io.Serializable;

public class Message implements Serializable {
    private MType type;
    private String message;
    private static final long serialVersionUID = 1234567890L;

    public Message(MType mType, String message) {
        this.type = mType;
        this.message = message;
    }

    public MType getType() {
        return type;
    }

    public void setType(MType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
