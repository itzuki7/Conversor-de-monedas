import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonHistorial {
    public void guardarJson(List<String> historial) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter("historial_conversiones.json");
        escritura.write(gson.toJson(historial));
        escritura.close();
    }
}