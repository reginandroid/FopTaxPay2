package com.example.buh.foptaxpay2;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Buh on 06.09.2017.
 */

public class Jsonparser {

    public static final String TAG_REGION = "region_name";
    public static final String TAG_RECIPIENT = "recipient";
    private static final String TAG_EDRPOU = "edrpou";
    private static final String TAG_ACCOUNT = "account";
    private static final String TAG_BANK = "bank";
    private static final String TAG_MFO = "mfo";
    private static final String TAG_DETAILS = "detailsOfPayment";


    private Context cnt;
    public Jsonparser(Context context){
        this.cnt = context;

    }


    public void getdata(String url, final String jsonarray, final CallBack onCallBack){
        final ArrayList<Item> list = new ArrayList<>();
        final Item item=null;
        RequestQueue requestQueue = Volley.newRequestQueue(cnt);
        StringRequest sr = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        response= fixEncoding(response);
                        JSONObject j = null;
                        try {
                            j = new JSONObject(response);
                            JSONArray ja = j.getJSONArray(jsonarray);

                            for (int i = 0; i < ja.length(); i++) {
                                JSONObject jb = ja.getJSONObject(i);
                                String region_name = jb.getString(TAG_REGION);
                                String recipient = jb.getString(TAG_RECIPIENT);
                                String edrpou = jb.getString(TAG_EDRPOU);
                                String account = jb.getString(TAG_ACCOUNT);
                                String bank = jb.getString(TAG_BANK);
                                String mfo = jb.getString(TAG_MFO);
                                String details = jb.getString(TAG_DETAILS);
                                Item item = new Item(region_name, recipient,edrpou, account, bank, mfo, details);
                                list.add(item);



                            }
                            onCallBack.onSuccess(list);

                        }catch (JSONException e) {
                            e.printStackTrace();
                            onCallBack.onFail(e.toString());
                        }

                    }
                },
                new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(cnt, "Error in loading history.", Toast.LENGTH_LONG).show();
                        Log.e("Volley", error.toString());

                    }
                }

        );
        requestQueue.add(sr);

    }
    public static String fixEncoding(String response) {
        try {
            byte[] u = response.toString().getBytes(
                    "ISO-8859-1");
            response = new String(u, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return response;
    }
    public interface CallBack {
        void onSuccess(ArrayList<Item> list);

        void onFail(String msg);
    }
}
