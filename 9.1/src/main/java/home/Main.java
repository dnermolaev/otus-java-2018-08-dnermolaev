package home;

import com.google.gson.Gson;

import javax.json.*;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        BagOfPrimitives obj = new BagOfPrimitives();
        System.out.println(obj);

        Serialization serial = new Serialization();
        Gson gson = new Gson();
        /*String json = gson.toJson(obj);*/
        String json=serial.toJson(obj);
        System.out.println();
        System.out.println(json);
        System.out.println();

        System.out.println(serial.toJson(null));
        //System.out.println(serial.toJson(new int[] {1, 2, 3}));

        System.out.println();
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);
        System.out.println(obj.equals(obj2));
        System.out.println(obj2);

        /*
        JsonReader reader = Json.createReader(new StringReader(json));
        BagOfPrimitives obj2 = (BagOfPrimitives)reader.read();
            System.out.println(obj.equals(obj2));
            System.out.println(obj2);*/
        }

    }


