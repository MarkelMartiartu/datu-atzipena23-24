package dambi;

import java.io.File;
import java.io.FileOutputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        JsonObject objetua = createObject();
        idatzi(objetua);
    }

    public static JsonObject createObject() {
        JsonObject model = Json.createObjectBuilder()
                .add("id", "file")
                .add("value", "File")
                .add("popup", Json.createObjectBuilder().add("menuitem",
                        Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("value", "new")
                                        .add("onclick", "CreateNewDoc()"))
                                .add(Json.createObjectBuilder()
                                        .add("value", "Open")
                                        .add("onclick", "OpenDoc()"))))
                .build();
        return model;
    }

    public static void idatzi(JsonObject modeloa) {
        try {
            new File("data/Irteera.json").createNewFile();
            JsonWriter jsonWriter = Json.createWriter(new FileOutputStream("data/Irteera.json"));
            jsonWriter.writeObject(modeloa);
        } catch (Exception ex) {
            System.out.println("Errorea gertatu da: " + ex.getMessage());
        }
    }
}
