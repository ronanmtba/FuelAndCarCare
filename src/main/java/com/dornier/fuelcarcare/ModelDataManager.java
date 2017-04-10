package com.dornier.fuelcarcare;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dornier on 27/03/2017.
 */

public class ModelDataManager {
    private RequestQueue mRequestQueue;
    private Context actualContext;
    private static ModelDataManager singleton;
    private Boolean sessionStart;
    private static final String TAG = ModelDataManager.class.getSimpleName();

    public Context getActualContext() {
        return actualContext;
    }

    public void setActualContext(Context actualContext) {
        this.actualContext = actualContext;
    }

    /*************/
    /* SINGLETON */
    /*************/

    private ModelDataManager() {
        CookieManager cm = new CookieManager();
        CookieHandler.setDefault(cm);
    }

    public static ModelDataManager getInstance(){
        if(singleton == null){
            singleton = new ModelDataManager();
        }
        return singleton;
    }

    /*********************************/
    /* Specific Server Communication */
    /*********************************/

    public void createUser(ReceiveFromServer o, String email, String password){
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", email);
            obj.put("password", password);
        }
        catch (Exception e){
            printErrorToConsole(e);
        }
        requestToServer("create_user.php", obj, o, "createUser");
    }

    public void login(ReceiveFromServer o, String email, String password){
        JSONObject obj = new JSONObject();
        try {
            obj.put("email", email);
            obj.put("password", password);
        }
        catch (Exception e){
            printErrorToConsole(e);
        }
        requestToServer("login.php", obj, o, "login");
    }


    /**************************************/
    /* Shared Server Comunication methods */
    /**************************************/

    public void requestToServer(final String page, final JSONObject array, ReceiveFromServer o, final String identifier) {
        final ReceiveFromServer requestOwner = o;

      /*  if(!sessionStart)
            setSession();*/
        Log.v("identifier", " => " +array);

        StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.114/" + page, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v(TAG,page+" => "+response);
                requestOwner.serverCall(response, identifier);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestOwner.serverCall(null, identifier);
                sessionStart = false;
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                for(int i = 0; i<array.names().length(); i++){
                    try {
                        params.put(array.names().getString(i), array.getString(array.names().getString(i)));
                    }
                    catch (Exception e){
                        ModelDataManager.printErrorToConsole(e);
                    }
                }
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                params.put("Content-Length",String.valueOf(getBody().length));
                return params;
            }

        };

        // queue.add(sr);

        sr.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        sr.setShouldCache(true);

        getInstance().addToRequestQueue(sr);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(actualContext);
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /***********/
    /* Statics */
    /***********/

    public static void printErrorToConsole(Exception e){
        Log.e("Exception: ", e.toString());
    }

    public static String getActualDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date());
    }
}
