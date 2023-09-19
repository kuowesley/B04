package com.example.project_b04;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class day_edit extends Fragment {

    public day_edit() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day_edit, container, false);

        Button btnEditNum_OK = v.findViewById(R.id.btnEditNum_OK);
        btnEditNum_OK.setOnClickListener(btnEditNum_OKOnClick);
        Button btnCancel_dayEdit = v.findViewById(R.id.btnCancel_dayEdit);
        btnCancel_dayEdit.setOnClickListener(btnCancel_dayEditOnClick);

        return v;
    }

    private View.OnClickListener btnEditNum_OKOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideDay_edit();
            activity.showDay();
        }
    };

    private View.OnClickListener btnCancel_dayEditOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideDay_edit();
            activity.showDay();
        }
    };
}

