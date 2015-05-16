/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.darkfalcon.java.message;

/**
 *
 * @author Darkfalcon
 */
public class Message<T> {

    private Opcode opcode;
    private T content;

    public Message() {

    }

    public Message(Opcode opcode, T content) {
        super();
        this.opcode = opcode;
        this.content = content;
    }

    public Opcode getOpcode() {
        return opcode;
    }

    public void setOpcode(Opcode opcode) {
        this.opcode = opcode;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message [opcode=" + opcode + ", content=" + content + "]";
    }
}
