package org.example.sa_lab06_2004031.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.sa_lab06_2004031.models.Product;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    public static String convertListProducts2Json(List<Product> productList) {
        Gson gson = new Gson();
        return gson.toJson(productList);
    }
    private static List<Product> convertJson2ListProduct(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Product>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
