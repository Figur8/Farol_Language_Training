package com.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pi4.farol.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashFragment extends Fragment {


    public DashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dash, container, false);
            Button function1 = v.findViewById(R.id.btn_function_1);
            Button function2 = v.findViewById(R.id.btn_function_2);
            Button function3 = v.findViewById(R.id.btn_function_3);
            Button function4 = v.findViewById(R.id.btn_function_4);
            ImageView img = v.findViewById(R.id.img_logo);
        return v;
    }

}
