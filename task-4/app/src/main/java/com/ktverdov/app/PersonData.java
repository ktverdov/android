package com.ktverdov.app;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PersonData {

    private static Map<Long, Person> PERSON_LIST = new LinkedHashMap<>();
    private static boolean initialized = false;

    public static void initialize(final Context context) {
        if (!initialized) {
            try {
                JSONObject jsonFile = new JSONObject(loadJSONFile(context));
                JSONArray personList = jsonFile.getJSONArray("persons");

                for (int i = 0; i < personList.length(); i++) {
                    JSONObject person = personList.getJSONObject(i);

                    Long id = person.getLong("id");
                    String name = person.getString("name");
                    String note = person.getString("note");
                    String image = person.getString("image");
                    int imageRes = context.getResources().getIdentifier(image, "drawable",
                            context.getPackageName());

                    PERSON_LIST.put(id, new Person(id, name, note, imageRes));
                }

                initialized = true;
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }

    }

    private static String loadJSONFile(final Context context) {
        try {
            InputStream inputStream = context.getAssets().open("data.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<Person> getPersonList() {
        return new ArrayList<>(PERSON_LIST.values());
    }

    public static Person getPersonById(final long id) {
        return PERSON_LIST.get(id);
    }
}