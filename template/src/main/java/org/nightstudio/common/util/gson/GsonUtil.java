package org.nightstudio.common.util.gson;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Created by xuezhucao on 16/2/4.
 */
public class GsonUtil {
    private static Gson gson = new Gson();

    public static Gson getGson() {
        return gson;
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return getGson().fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, TypeToken<T> typeToken) throws JsonSyntaxException {
        return getGson().fromJson(json, typeToken.getType());
    }

    public static <T> String toJson(T obj) {
        return getGson().toJson(obj);
    }
}
