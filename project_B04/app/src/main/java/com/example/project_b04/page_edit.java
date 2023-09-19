package com.example.project_b04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class page_edit extends Fragment {

    //介面元件
    private EditText mEtName ;
    private EditText mEtHeight ;
    private EditText mEtWeight ;

    public page_edit() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page_edit, container, false);

        mEtName = v.findViewById(R.id.etFoodName);
        mEtHeight = v.findViewById(R.id.etHeight);
        mEtWeight = v.findViewById(R.id.etWeight);

        Button btnConfirm = v.findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(btnConfirmOnClick);
        Button page_edit_cancel_btn = v.findViewById(R.id.page_edit_cancel_btn);
        page_edit_cancel_btn.setOnClickListener(page_edit_cancel_btnOnClick);

        return v;
    }

    //確認修改個人資訊 -->回到個人介面
    private View.OnClickListener btnConfirmOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        MainActivity activity = (MainActivity) getActivity();
        final  String nickname = mEtName.getText().toString();
        final  String height = mEtHeight.getText().toString();
        final  String weight =mEtWeight.getText().toString();

            new Thread(new Runnable() {
                @Override
                public void run() {
                try {
                    JSONObject jsonObject =new JSONObject() ;
                    jsonObject.put("session","123");
                    jsonObject.put("nickname",nickname);
                    jsonObject.put("height",height);
                    jsonObject.put("weight",weight);
                    String Json=jsonObject.toString();
                    System.out.println(Json);
                    URL url =new URL("http://192.168.1.105/editUser_php.php");
                    HttpURLConnection con =(HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(3000);
                    con.setDoOutput(true);
                    con.setUseCaches(false);
                    con.connect();

                    DataOutputStream outputStream1 = new DataOutputStream(con.getOutputStream());

                    outputStream1.writeBytes(Json);
                    outputStream1.flush();
                    int v =con.getResponseCode();
                    System.out.println(v);
                    outputStream1.close();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    }
                }).start();

            activity.hidePage_edit();
            activity.showDay();
        }
    };

    //取消修改個人資訊 -->回到個人介面
    private View.OnClickListener page_edit_cancel_btnOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hidePage_edit();
            activity.showDay();
        }
    };
}

