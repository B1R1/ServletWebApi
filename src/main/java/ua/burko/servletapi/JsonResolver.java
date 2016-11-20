package ua.burko.servletapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Parse JSON string with help GSON library and return string as a result.
 *
 * @author Roman Burko <roman.burko@gmail.com>
 * @version 1.0
 */

public class JsonResolver {

    public static String resolve(String text)  {
        String result = null;

        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(text);
            result = gson.toJson(jsonObject);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
