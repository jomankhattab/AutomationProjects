package utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


public class DataDriven {
    private static JsonObject root;


    static {
        try (Reader reader = new InputStreamReader(
                Objects.requireNonNull(DataDriven.class.getResourceAsStream("/testData.json")), StandardCharsets.UTF_8)){
            root = new Gson().fromJson(reader,JsonObject.class);
        }
        catch (Exception e){
            e.printStackTrace();
            root = new JsonObject();
        }
    }

    public static  JsonObject jsonReader (){
        return root.deepCopy();
    }
}
