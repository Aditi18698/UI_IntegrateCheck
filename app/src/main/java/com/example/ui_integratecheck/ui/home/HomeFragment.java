package com.example.ui_integratecheck.ui.home;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ui_integratecheck.CustomOnItemSelectedListener;
import com.example.ui_integratecheck.DataBaseHelper1;
import com.example.ui_integratecheck.R;

public class HomeFragment extends Fragment {

    EditText BT_Num,Vehicle_no;
    Button add;

    Spinner spinner;
    DataBaseHelper1 dataBaseHelper;

    public static String BT_Name=" ";
    public static Float tank=0.0f;

    String table_Name;

    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseHelper=new DataBaseHelper1(getActivity());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
       // final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
               // textView.setText(s);
            }
        });

        BT_Num = root.findViewById(R.id.BT_no);
        Vehicle_no= root.findViewById(R.id.Vehicle_no);
        spinner = root.findViewById(R.id.spinner);

        add = root.findViewById(R.id.add);
        addListenerOnSpinnerItemSelection();
        return root;
    }
    public void addListenerOnSpinnerItemSelection ()
    {
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*

        table_Name=BT_Name;
                Log.d("Tag", " Values in DB:1 "+BT_Name);
                Log.d("Tag", " Values in DB:1 "+table_Name);
                if (table_Name.contains(" ")){
                    table_Name.replaceAll(" ","_");
                    Log.d("Tag", " Values in DB:1 "+table_Name);
                }
                table_Name=table_Name.concat(Vehicle_no.getText().toString());
                Log.d("Tag", " Values in homefrag "+table_Name);


                //Toast.makeText(getActivity(), "Yes selected", Toast.LENGTH_LONG).show();
                Log.d("Tag", "starting Values in DB: ");
                //dataBaseHelper.createTable(table_Name);

         */



        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                try{

                    Log.d("Tag", "INSERTING Values in DB: ");
                    boolean result = dataBaseHelper.addVehicle(BT_Num.getText().toString(),
                            BT_Name,
                            Vehicle_no.getText().toString(),
                            String.valueOf(tank)
                            );

                    if (result==true)
                        Toast.makeText(getActivity(),"VEHICLE ADDED",Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getActivity(),"VEHICLE NOT ADDED",Toast.LENGTH_LONG).show();

                }catch (Exception e){
                    Log.d("Exception", "INSERTING Values : "+e);
                }

                try{
                    Log.d("Tag", "GETTING Values in DB: ");
                    Cursor cursor=dataBaseHelper.getVehicleData();

                    if (cursor.getCount()==0){
                        showmsg("ERROR ","NOTHING FOUND");
                        //return;
                    }

                    StringBuffer buffer=new StringBuffer();
                    final int pr;
                    String a="";
                    while (cursor.moveToNext()){

                        //TO DISPLAY ALL THE DATABASE CONTAINS
                        buffer.append("BT_no "+cursor.getString(0)+"\n");
                        buffer.append("vehicle "+cursor.getString(1)+"\n");
                        buffer.append("vehicle no."+cursor.getString(2)+"\n");
                        buffer.append("tank  "+cursor.getString(3)+"\n");

                        //Log.d("Tag", "GETTING Values in DB of Meter1: "+cursor.getString(0));
                        //Log.d("Tag", "GETTING Values in DB of Meter2: "+cursor.getString(1));
                        //Log.d("Tag", "GETTING Values in DB of Fuel: "+cursor.getString(2));


                    }

                    showmsg("Data",buffer.toString());
                }catch (Exception e){
                    Log.d("Exception", "GETTING Values : "+e);
                }


            }


        }
        );



    }


    public void showmsg(String title,String msg){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setMessage(title);
        builder.setMessage(msg);
        builder.show();

    }

}