package com.macro.mall.tiny.datastruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2020/7/29.
 */
public class JsonArrayAndJsonObject {

    public static void methodOne() throws JSONException {
		/*  TODO
		 *  Construct the filter condition:
            {
                "where": {
                    "single": {
                        "id": "10086"
                    },
                    "or": [
                        {
                            "name": "张三"
                        }
                    ],
                    "and": [
                        {
                            "tag": "test"
                        },
                        {
                            "tag": "Test"
                        }
                    ]
                }
            }
		 */
        JSONObject filterJson = new JSONObject();
        JSONObject whereJson = new JSONObject();
        JSONArray tagArray = new JSONArray();
        JSONObject testTag = new JSONObject();
        JSONObject TestTag = new JSONObject();

        testTag.put("tag", "test");
        TestTag.put("tag", "Test");
        tagArray.put(testTag);
        tagArray.put(TestTag);
        whereJson.put("and", tagArray);

        JSONArray orArray = new JSONArray();
        JSONObject nameTag = new JSONObject();
        nameTag.put("name","张三");
        orArray.put(nameTag);
        whereJson.put("or",orArray);

        JSONObject singleObject = new JSONObject();
        singleObject.put("id","10086");
        whereJson.put("single",singleObject);

        filterJson.put("where", whereJson);
        System.out.println(filterJson.toString());

    }

    public static void main(String[] args) throws JSONException {
        methodOne();
    }

}
