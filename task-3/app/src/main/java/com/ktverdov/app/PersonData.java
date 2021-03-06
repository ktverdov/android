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

    private static final Map<Long, Person> PERSON_LIST = new LinkedHashMap<>();

    public static void initialize(final Context context) {
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray m_jArry = obj.getJSONArray("persons");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);

                Long id = jo_inside.getLong("id");
                String name = jo_inside.getString("name");
                String note = jo_inside.getString("note");
                String image = jo_inside.getString("image");
                int imageRes = context.getResources().getIdentifier(image, "drawable",
                        context.getPackageName());

                PERSON_LIST.put(id, new Person(id, name, note, imageRes));
            }
        } catch (JSONException e) {

        }

    }

    public static String loadJSONFromAsset(final Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
        }
        return json;
    }

    static List<Person> getPersonList() {
        return new ArrayList<>(PERSON_LIST.values());
    }

    static Person getPersonById(final long id) {
        return PERSON_LIST.get(id);
    }
}