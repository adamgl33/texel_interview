package tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONSerializer {

    public String toJSON(Object input) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();;
        return gson.toJson(input);
    }
}