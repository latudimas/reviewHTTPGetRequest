package com.mitrais.reviewhttpreq;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 * javierchacana@gmail.com
 * javierchacana@gmail.com
 *
 */
public class App {
    public static String[] getMovieTitles(String substr) throws Exception {
        int pageNumber = 1;
        int totalPages = Integer.MAX_VALUE;

        List<String> titleList = new ArrayList<>();

        URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page=" + pageNumber);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String resp;

        while ((resp = in.readLine()) != null) {
            JsonObject convertedObject = new Gson().fromJson(resp, JsonObject.class);
            totalPages = convertedObject.get("total_pages").getAsInt();
            JsonArray data = convertedObject.getAsJsonArray("data");
            for (int i = 0; i < data.size(); i++) {
                String title = data.get(i).getAsJsonObject().get("Title").getAsString();
                titleList.add(title);
            }
        }
        in.close();
        pageNumber++;

        Collections.sort(titleList);
        return titleList.toArray(new String[titleList.size()]);
    }

    public static void main( String[] args ) throws Exception {
        String[] result = getMovieTitles("spiderman");

        for (String s : result) {
            System.out.println(s);
        }
    }
}
