package utilities;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.axiom.om.OMElement;
import java.util.Iterator;

public class GSON {

    static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.SSS").create();

    public static String toJson(final Object object) {
        return gson.toJson(object);
    }

    public static <T> T toObject(final String json, final Class<T> clazz) {
        final T jsonObj = gson.fromJson(json, clazz);
        return jsonObj;
    }

    public static <A, B> B transform(final A update, final Class<B> clazz) {
        return GSON.toObject(GSON.toJson(update), clazz);
    }

    public static JsonObject deserializeXML(final Iterator<OMElement> it, final JsonObject bag) {
        if (it.hasNext()) {
            final OMElement child = it.next();
            final String elementName = child.getLocalName();

            if (!child.getChildElements().hasNext())
                bag.addProperty(elementName, child.getText());
            else
                bag.add(elementName, deserializeXML(child.getChildElements(), new JsonObject()));

            return deserializeXML(it, bag);
        }
        return bag;
    }

}