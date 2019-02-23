package com.tao.code;

public enum MType {
    ID(0, "id"),
    MESSAGE(1, "message"),
    LOGIN(2, "login"),
    LOGOUT(3, "logout"),
    SUCCESS(4, "success"),
    FALSE(5, "false");

    private int code;
    private String type;

    MType(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public int getCode() {
        return code;
    }

    public String getType() {
        return type;
    }
}
