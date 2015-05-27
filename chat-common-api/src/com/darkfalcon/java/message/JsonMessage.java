/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.message;

import com.google.gson.Gson;
import java.lang.reflect.Type;

/**
 *
 * @author Darkfalcon
 */
public class JsonMessage {
    
    private Opcode opcode;
    private String content;

    public JsonMessage() { }

    public Opcode getOpcode() {
        return opcode;
    }

    public void setOpcode(Opcode opcode) {
        this.opcode = opcode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public void setContentObject(Object object, Class type) {
        Gson gson = new Gson();
        this.content = gson.toJson(object, type); 
    }
    
    public Object getContentObject(Class type) {
        Gson gson = new Gson();
        return gson.fromJson(this.content, type);
    }

    @Override
    public String toString() {
        return "Message [opcode=" + opcode + ", content=" + content + "]";
    }
}
