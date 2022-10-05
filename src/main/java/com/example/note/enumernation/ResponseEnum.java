package com.example.note.enumernation;

public enum ResponseEnum {

    SUCCESS(0, "API SUCCESS"),
    DATA_EXISTS(-1, "DATA EXISTS IN DATABASE"),
    DATA_NOT_FOUND(-2, "DATA NOT EXISTS IN DATABASE"),
    FAILURE(-99, "API FAILURE");

    private int returnCode;
    private String returnMsg;

    private ResponseEnum(int returnCode, String returnMsg) {
        this.returnCode = returnCode;
        this.returnMsg = returnMsg;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

}
