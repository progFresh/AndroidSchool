package net.nosorog.project_777;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class Deserializer implements JsonDeserializer<Item[]> {

    @Override
    public Item[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        return new Gson().fromJson(jsonObject.get("items").getAsJsonArray(), Item[].class);
    }
}
