/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.darkfalcon.java.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Darkfalcon
 */
public class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {

    @Override
    public JsonElement serialize(byte[] src, Type type, JsonSerializationContext jsc) {
        Base64 base = new Base64();
        return new JsonPrimitive(base.encodeToString(src));
    }

    @Override
    public byte[] deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        Base64 base = new Base64();
        return base.decode(json.getAsString());
    }
    
}
