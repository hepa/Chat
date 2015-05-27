package com.darkfalcon.java.message;

public enum Opcode {

    CONNECT_SYN, 
    CONNECT_SYN_ACK, 
    CONNECT_ACK,
    IDENTIFY_SYN,
    IDENTIFY_SYN_ACK,
    IDENTIFY_ACK,
    CONNECTION_REFUSED,
    MESSAGE,
    DISCONNECT, 
    IDENTIFY, 
    VALIDATE_SYN, 
    VALIDATE_ACK, 
    VALIDATE_CLOSE, 
    ACKNOLEDGED, 
    REGISTER, 
}
