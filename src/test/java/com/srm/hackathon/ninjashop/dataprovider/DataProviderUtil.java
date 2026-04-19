package com.srm.hackathon.ninjashop.dataprovider;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.srm.hackathon.ninjashop.utils.JsonUtil;
import org.testng.annotations.DataProvider;

public class DataProviderUtil {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {

        String path = "src/test/resources/testdata/loginData.json";

        JsonArray jsonArray = JsonUtil.readJsonArray(path);

        Object[][] data = new Object[jsonArray.size()][3];

        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject obj = jsonArray.get(i).getAsJsonObject();

            data[i][0] = obj.get("email").getAsString();
            data[i][1] = obj.get("password").getAsString();
            data[i][2] = obj.get("expected").getAsBoolean();
        }

        return data;
    }
}