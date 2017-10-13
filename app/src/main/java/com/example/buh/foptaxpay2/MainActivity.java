package com.example.buh.foptaxpay2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;


public class MainActivity extends Activity implements  OnItemSelectedListener, View.OnClickListener{
    private ShareActionProvider mShareActionProvider;
    public static final String KEY_SPINNER_STRING = "com.example.buh.foptaxpayer.KEY_SPINNER_STRING";
    Button btn_edinii, btn_esv;
    Cursor c;


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
        btn_esv = (Button) findViewById(R.id.btn_esv);
        btn_esv.setOnClickListener(this);
        btn_edinii.setOnClickListener(this);

        }
    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public Intent doShare() {
        String URL = "content://com.example.buh.foptaxpay2.FOPContentProvider/requisite";
        Uri requisite = Uri.parse(URL);
         c = this.getContentResolver().query(requisite, null, null, null, null);
            c.moveToFirst();


        String sharedata ="";

                    sharedata = System.getProperty("line.separator") +
                            "ОДЕРЖУВАЧ: " + c.getString(c.getColumnIndex(Constants.RECIPIENT)) + System.getProperty("line.separator") +
                            "ЄДРПОУ: " + c.getString(c.getColumnIndex(Constants.EDRPOU)) + System.getProperty("line.separator") +
                            "РАХУНОК: " + c.getString(c.getColumnIndex(Constants.ACCOUNT)) + System.getProperty("line.separator") +
                            "БАНК: " + c.getString(c.getColumnIndex(Constants.BANK)) + System.getProperty("line.separator") +
                            "МФО: " + c.getString(c.getColumnIndex(Constants.MFO)) + System.getProperty("line.separator") +
                            "ПРИЗНАЧЕННЯ ПЛАТЕЖУ: " + c.getString(c.getColumnIndex(Constants.DETAILS));
                    Log.d("my", sharedata);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
       intent.putExtra(Intent.EXTRA_TEXT, sharedata);
getContentResolver().delete(requisite,null,null);
        return intent;
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        String item = parent.getItemAtPosition(pos).toString();

        Intent i = new Intent (this, RequisiteFragment.class);
        i.putExtra(KEY_SPINNER_STRING, item);
        setIntent(i);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_item_share:
                mShareActionProvider = (ShareActionProvider) item.getActionProvider();
                mShareActionProvider.setShareIntent(doShare());
                return true;

            case R.id.help:
                Intent i =new Intent(this, HelpActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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



                ft.commit();
                break;
            case R.id.btn_esv:
                ESV_Fragment frg_esv = new ESV_Fragment();
                android.app.FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                ft1.replace(R.id.frg2, frg_esv);

                ft1.commit();
                break;
            default:
                break;

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();



    }
}