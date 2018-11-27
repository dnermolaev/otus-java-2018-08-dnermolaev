package home;


import javax.json.*;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Collection;

public class Serialization {

    public String toJson(Object obj) {
        if (obj == null) return this.toJson(null);
        else return this.toJson((Object) obj, obj.getClass());
    }

    private String toJson(Object obj, Type typeOfObj) {
        StringWriter writer = new StringWriter();
        this.toJson(obj, typeOfObj, (Appendable) writer);
        return writer.toString();
    }

    private void toJson(Object obj, Type typeOfObj, Appendable writer) {

        Field[] fields = obj.getClass().getDeclaredFields();

        JsonStructure jsonst;
        JsonObjectBuilder builder;
        try {
            builder = Json.createObjectBuilder();
            for (Field field : fields) {
                boolean isTransient = Modifier.isTransient(field.getModifiers());
                if (isTransient != true) {
                    field.setAccessible(true);

                    if (field.get(obj).getClass().isArray()) {

                        builder.add(field.getName(), arrayBuilder((Object[]) field.get(obj)));
                    }

                    if (field.get(obj) instanceof Collection) {

                        builder.add(field.getName(), arrayBuilder((Collection)field.get(obj)));
                    }

                if (field.get(obj) instanceof Integer)
                        builder.add(field.getName(), (int)field.get(obj) );

                    if (field.get(obj) instanceof String)

                        builder.add(field.getName(), (String)field.get(obj));

                    // System.out.println("123 "+ objectBuilder(field.getName(), field.get(obj)).build().size());
                    //builder=objectBuilder(field.getName(), field.get(obj)).build();
                }

            }

            jsonst = builder.build();
        } catch (IllegalAccessException nsfe) {
            throw new RuntimeException(nsfe);
        }
        String jsonData = writeToString((JsonObject) jsonst);
        System.out.println();
        System.out.println(jsonData);
    }

   public static JsonArrayBuilder arrayBuilder (Collection collection){
       JsonArrayBuilder jsonArrayBuilder=Json.createArrayBuilder();
       for (Object element : collection){
           if (element instanceof String)
               jsonArrayBuilder.add((String)element);
            if (element instanceof Integer)
                jsonArrayBuilder.add((int)element); }
       return jsonArrayBuilder;
   }

    public static JsonArrayBuilder arrayBuilder (Object [] obj){
        JsonArrayBuilder jsonArrayBuilder=Json.createArrayBuilder();
        for (Object element : obj){
            if (element instanceof String)
                jsonArrayBuilder.add((String)element);
            if (element instanceof Integer)
                jsonArrayBuilder.add((int)element); }
        return jsonArrayBuilder;
    }


    private static String writeToString(JsonObject jsonst) {
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(jsonst);
        }

        return stWriter.toString();
    }

    public static JsonObjectBuilder objectBuilder (String str, Object obj){
        JsonObjectBuilder builder=Json.createObjectBuilder();

        if (obj instanceof String)
            builder.add(str,(String)obj);
        else if (obj instanceof Integer)
            builder.add(str,(int)obj);
        else if (obj.getClass().isArray())
            builder.add(str,arrayBuilder((Object[])obj));
        else if (obj instanceof Collection)
            builder.add(str,arrayBuilder((Collection)obj));

        System.out.println(builder.build().size());

        return builder;
    }

}



/* if (field.get(obj).getClass().isArray()) {

         objectBuilder(field.getName(), arrayBuilder((Object[])field.get(obj)));
         }

         if (field.get(obj) instanceof Collection) {

         builder.add(field.getName(), arrayBuilder((Collection)field.get(obj)));
         }

/*if (field.get(obj) instanceof Integer)
                        builder.add(field.getName(), (int)field.get(obj) );

                    if (field.get(obj) instanceof String)

                        builder.add(field.getName(), (String)field.get(obj));*/

