package com.mts.toyskingdom.data.mgt;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class ResponseObject<OBJ> implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean success;

    private String message;

    private OBJ data;

    public ResponseObject() {
        this.setSuccess(true);
        this.setMessage("Success");
    }

    ;

    // only Data
    public ResponseObject(OBJ data) {
        this();
        this.data = data;
    }

    ;

    // success and message
    public ResponseObject(boolean success, String message) {
        this(success, message, null);
    }

    ;

    //
    public ResponseObject(boolean success, String message, OBJ data) {
        this.setSuccess(success);
        this.setMessage(message);
        this.setData(data);
    }

    public void setMessage(String format, String... params) {
        if (params != null && params.length > 0) {
            this.message = String.format(format, (Object[]) params);
        } else {
            this.message = format;
        }
    }

    ;

    public void setFailMessage(String format, String... params) {
        this.success = false;
        this.setMessage(format, params);
    }

    public static ResponseObject failResponse(String format, String... params) {
        ResponseObject response = new ResponseObject();
        response.setSuccess(false);
        response.setMessage(format, params);
        return response;
    }
}