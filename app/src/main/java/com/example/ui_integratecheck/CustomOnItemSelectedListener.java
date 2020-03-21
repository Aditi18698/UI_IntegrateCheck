package com.example.ui_integratecheck;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.example.ui_integratecheck.ui.home.HomeFragment;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(),
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
              Toast.LENGTH_SHORT).show();

        HomeFragment.BT_Name=parent.getItemAtPosition(position).toString();

        if (HomeFragment.BT_Name.equals("Activa 5G")){
            //String a="Activa_5G";
            //HomeFragment.BT_Name="Activa 5G";
            HomeFragment.tank=5.0f;
        }

        else if (HomeFragment.BT_Name.equals("Access 125")){
            //String a="Access_125";
            //HomeFragment.BT_Name="Access 125";
            HomeFragment.tank=5.6f;
        }


        else if (HomeFragment.BT_Name.equals("Aviator")){
            //HomeFragment.BT_Name="Aviator";
            HomeFragment.tank=5.3f;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
