package com.hunterlab.hunter.utsav18.AboutUs;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hunterlab.hunter.utsav18.R;

/**
 * Created by Jayesh Saita on 12-Sep-16.
 */

public class AboutUs_main extends Fragment {

    public AboutUs_main(){
        //Default Constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.about_us_main,container,false);
        return v;
    }
}
