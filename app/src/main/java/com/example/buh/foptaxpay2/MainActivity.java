package com.example.buh.foptaxpay2;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.action;

public class MainActivity extends Activity implements OnItemSelectedListener, View.OnClickListener{


    public static final String KEY_SPINNER_STRING = "com.example.buh.foptaxpayer.KEY_SPINNER_STRING";

    Button btn_edinii, btn_esv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.city_region_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_region, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        btn_edinii = (Button) findViewById(R.id.btn_edinii);
        btn_esv = (Button)findViewById(R.id.btn_esv);
        btn_esv.setOnClickListener(this);
        btn_edinii.setOnClickListener(this);

    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String item = parent.getItemAtPosition(pos).toString();
        Intent i = new Intent (this, RequisiteFragment.class);
        i.putExtra(KEY_SPINNER_STRING, item);
        setIntent(i);

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_edinii:

                RequisiteFragment frg_ed = new RequisiteFragment();
               android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.frg1, frg_ed);
              ft.addToBackStack(null);


                ft.commit();
                break;
            case R.id.btn_esv:
                ESV_Fragment frg_esv = new ESV_Fragment();
                android.app.FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                ft1.replace(R.id.frg2, frg_esv);
                ft1.addToBackStack(null);
                ft1.commit();
                break;
            default:
                break;

        }

    }
}