package home;

import com.google.gson.Gson;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
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



        /*JsonReader reader = Json.createReader(new StringReader(json));

        BagOfPrimitives obj2 = (BagOfPrimitives)reader.read();
            System.out.println(obj.equals(obj2));
            System.out.println(obj2);*/
        }

    }


