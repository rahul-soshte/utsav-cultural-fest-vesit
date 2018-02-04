package com.hunterlab.hunter.utsav18.Events;

import android.os.AsyncTask;

import com.burgstaller.okhttp.AuthenticationCacheInterceptor;
import com.burgstaller.okhttp.CachingAuthenticatorDecorator;
import com.burgstaller.okhttp.digest.CachingAuthenticator;
import com.burgstaller.okhttp.digest.Credentials;
import com.burgstaller.okhttp.digest.DigestAuthenticator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

;

/**
 * Created by Pavan on 7/17/2016.
 */
public class APIMiddleware extends AsyncTask<JSONObject, Void, JSONObject> {

    private TaskListener listener;
    //This depends on where the file is hosted
    private static final String BASE_URL = "http://praxisvesit.com/deep/deeper/deepest/api/index.php/";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    //api credentials not to be revealed
    private String username, password;

    public APIMiddleware(TaskListener listener) {
        this.listener = listener;
        username = "api_user";
        password = "praxis2k16RESTapi";
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        listener.onTaskBegin();
    }

    @Override
    protected JSONObject doInBackground(JSONObject... jsonObjects) {
        OkHttpClient client;
        if(jsonObjects.length > 1)
            return null;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        final DigestAuthenticator authenticator = new DigestAuthenticator(new Credentials(username, password));

        final Map<String, CachingAuthenticator> authCache = new ConcurrentHashMap<>();
        client = builder
                .authenticator(new CachingAuthenticatorDecorator(authenticator, authCache))
                .addInterceptor(new AuthenticationCacheInterceptor(authCache))
                .build();
        try {
            Request request = null;
            RequestBody body;
            switch(jsonObjects[0].getString("method")) {
                case "GET":
                    request = new Request.Builder()
                            .url(new URL(BASE_URL + jsonObjects[0].getString("url")))
                            .get()
                            .build();
                    break;
                case "POST":
                    body = RequestBody.create(JSON,jsonObjects[0].getJSONObject("body").toString());
                    request = new Request.Builder()
                            .url(new URL(BASE_URL + jsonObjects[0].getString("url")))
                            .post(body)
                            .build();
                    break;
                case "PUT":
                    body = RequestBody.create(JSON,jsonObjects[0].getJSONObject("body").toString());
                    request = new Request.Builder()
                            .url(new URL(BASE_URL + jsonObjects[0].getString("url")))
                            .put(body)
                            .build();
                    break;
                case "DELETE":
                    request = new Request.Builder()
                            .url(new URL(BASE_URL + jsonObjects[0].getString("url")))
                            .delete()
                            .build();
                    break;
            }
            if(request != null) {
                Response response = client.newCall(request).execute();
                JSONObject ret = new JSONObject();
                ret.put("data", new JSONArray(response.body().string()));
                ret.put("code", response.code());
                return ret;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        listener.onTaskCompleted(jsonObject);
    }

    interface TaskListener {
        void onTaskBegin();
        void onTaskCompleted(JSONObject response);
    }

}
