package home;

import javax.json.*;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class Serialization {

    public String toJson(Object obj) {
        JsonStructure jsonst;
        JsonObjectBuilder builder;

        if (obj == null) {
            return JsonValue.NULL.toString();
        }
        else
        if (obj.getClass().isArray()|| obj instanceof Collection) {
            JsonArrayBuilder jsonArrayBuilder = arrayBuilder(obj);
            return jsonArrayBuilder.build().toString();
        }
            else {

            Field[] fields = obj.getClass().getDeclaredFields();

            try {
                builder = Json.createObjectBuilder();
                for (Field field : fields) {

                    boolean isTransient = Modifier.isTransient(field.getModifiers());
                    if (isTransient != true) {
                        field.setAccessible(true);

                        if (field.get(obj) == null) {
                            builder.addNull(field.getName());
                        } else {
                            if (field.get(obj).getClass().isArray()) {
                                builder.add(field.getName(), arrayBuilder(field.get(obj)));
                            }

                            if (field.get(obj) instanceof Collection) {
                                builder.add(field.getName(), arrayBuilder((Collection) field.get(obj)));
                            }

                            if (field.get(obj) instanceof Integer)
                                builder.add(field.getName(), (int) field.get(obj));

                            if (field.get(obj) instanceof String)
                                builder.add(field.getName(), (String) field.get(obj));
                        }
                    }
                }
                jsonst = builder.build();
            } catch (IllegalAccessException nsfe) {
                throw new RuntimeException(nsfe);
            }
        }


            String jsonData = writeToString((JsonObject) jsonst);
            System.out.println();
            System.out.println(jsonData);

            return jsonData;
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

    public static JsonArrayBuilder arrayBuilder (Object obj){
        JsonArrayBuilder jsonArrayBuilder=Json.createArrayBuilder();
        List list=new ArrayList();
        if (obj.getClass().isArray()){
        Class ofArray = obj.getClass().getComponentType();
        if (ofArray.isPrimitive()) {
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                list.add(Array.get(obj, i));
            }
        }
        else {list= Arrays.asList((Object[]) obj); }

        for (Object element : list){
            if (element instanceof String)
                jsonArrayBuilder.add((String)element);
            if (element instanceof Integer)
                jsonArrayBuilder.add((int)element); }}

        else {
            Collection collection;
            collection = (Collection) obj;
            return arrayBuilder(collection);
        }
        return jsonArrayBuilder;
    }

    private static String writeToString(JsonObject jsonst) {
        StringWriter stWriter = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriter(stWriter)) {
            jsonWriter.writeObject(jsonst);
        }
        return stWriter.toString();
    }

    /*public static JsonObjectBuilder objectBuilder (String str, Object obj){
        JsonObjectBuilder builder=Json.createObjectBuilder();

        if (obj instanceof String)
            builder.add(str,(String)obj);
        else if (obj instanceof Integer)
            builder.add(str,(int)obj);
        else if (obj.getClass().isArray())
            builder.add(str,arrayBuilder((Arrays)obj));
        else if (obj instanceof Collection)
            builder.add(str,arrayBuilder((Collection)obj));

        System.out.println(builder.build().size());

        return builder;
    }*/

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

