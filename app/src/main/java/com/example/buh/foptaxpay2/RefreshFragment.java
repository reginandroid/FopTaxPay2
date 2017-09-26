package com.example.buh.foptaxpay2;

import android.support.v4.app.Fragment;

/**
 * Created by Buh on 18.09.2017.
 */
public class RefreshFragment extends android.app.Fragment {
       public void reattach(){
                if (!this.isDetached() ){
                        getFragmentManager().beginTransaction()
                                .detach(this)
                                .attach(this)
                                .commit();
                }
        }
}