package com.example.buh.foptaxpay2;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ESV_Fragment extends Fragment {
    private Unbinder unbinder;
    @BindView(R.id.recipient)
    TextView recipient;
    @BindView(R.id.account) TextView account;
    @BindView(R.id.edrpou) TextView edrpou;
    @BindView(R.id.bank) TextView  bank;
    @BindView(R.id.mfo) TextView mfo;
    @BindView(R.id.details) TextView details;

    final static String url = "https://paytaxhere.000webhostapp.com/esv.json";
    String spinnerString;
    public static final String KEY_SPINNER_STRING = "com.example.buh.foptaxpayer.KEY_SPINNER_STRING";
    public static final String JSON_ARRAY = "esv";
    Jsonparser jsonparser;
    Item item;
    ProgressBar progressBar;
    public ESV_Fragment() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.frg_esv,container,false);
        unbinder = ButterKnife.bind(this,v);

        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        jsonparser = new Jsonparser(this.getActivity().getApplicationContext());
        jsonparser.getdata(url, JSON_ARRAY, new Jsonparser.CallBack() {
            @Override
            public void onSuccess(ArrayList<Item> list) {
                progressBar.setVisibility(View.GONE);
                MainActivity activity = (MainActivity) getActivity();
                Intent f = activity.getIntent();
                if (f != null) {
                    spinnerString = f.getStringExtra(KEY_SPINNER_STRING);

                }
                Log.d("my", spinnerString );

                for (int i = 0; i < list.size(); i++) {
                    if (spinnerString.equals(list.get(i).getRegion())) {
                        item = list.get(i);

                    }
                }

                recipient.setText(item.getReciepient());
                edrpou.setText(item.getEdrpou());
                account.setText(item.getAccount());
                account.invalidate();
                bank.setText(item.getBank());
                mfo.setText(item.getMfo());
                details.setText(item.getDetails());

                Log.d("my",list.size() + " "  +item.getReciepient() + " " + item.getEdrpou()+ " " + item.getAccount());
            }
            @Override
            public void onFail(String msg) {
                msg = "Error parse json";
            }
        });
        return v;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        Log.d("my", "was destroyed");
    }
}