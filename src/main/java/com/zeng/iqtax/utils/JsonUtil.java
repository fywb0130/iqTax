package com.zeng.iqtax.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

public class JsonUtil {
    private static Gson humanGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    private static Gson numricGson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
                public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                    return new JsonPrimitive(src.getTime());
                }
            })
            .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                    return new java.util.Date(json.getAsJsonPrimitive().getAsLong());
                }
            })
            .create();

    public static String toHumanJson(Object o) {
        return humanGson.toJson(o);
    }

    public static <T> T fromHumanJson(String s, Class<T> clazz) {
        return humanGson.fromJson(s, clazz);
    }

    public static String toNumricJson(Object o) {
        return numricGson.toJson(o);
    }

    public static <T> T fromNumricJson(String s, Class<T> clazz) {
        return numricGson.fromJson(s, clazz);
    }

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("zeng: " + toNumricJson(date));
        System.out.println("zeng: " + fromHumanJson("\"2017-11-12 13:48:34\"", Date.class));
    }
}
