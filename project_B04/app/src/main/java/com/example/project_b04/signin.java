package com.example.project_b04;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class signin extends Fragment {

    //介面元件
    EditText mEtAccount_;   // 帳號
    EditText mEtPassword_;     // 密碼
    private TextView tv;
    private Context myDialogContext; // 儲存"對話方塊背景"

    public signin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signin, container, false);

        Button btnLogin = v.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(btnLoginOnClick);
        Button btnSignup= v.findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(btnSignupOnClick);

        mEtAccount_ = v.findViewById(R.id.etAccountNum);
        mEtPassword_ =v.findViewById(R.id.etPassword);
        tv = v.findViewById(R.id.tvDateString);

        mEtAccount_.setText("");
        mEtPassword_.setText("");
        tv.setText("");

        return v;
    }

    //確認帳密 --> 登入
    private final View.OnClickListener btnLoginOnClick = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            final MainActivity activity = (MainActivity) getActivity();
            final String account_login = mEtAccount_.getText().toString();
            final String pw_login = mEtPassword_.getText().toString();

            final InputStream[] in = {null};

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("username", account_login);
                        jsonObject.put("password", pw_login);
                        jsonObject.put("session","123");

                        String Json = jsonObject.toString();
                        System.out.println(Json);
                     //   URL url = new URL("http://172.18.112.184/login.php");
                       URL url =new URL("http://192.168.1.105/login.php");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();

                        con.setConnectTimeout(3000);
                        con.setDoOutput(true);
                        con.setDoInput(true);
                        con.setRequestMethod("POST");

                        con.setUseCaches(false);
                        con.connect();
                        DataOutputStream outputStream1 = new DataOutputStream(con.getOutputStream());
                        outputStream1.writeBytes(Json);
                        outputStream1.flush();
                        int v = con.getResponseCode();
                        System.out.println(v);

                        in[0] = con.getInputStream();

                        BufferedReader br = new BufferedReader(new InputStreamReader(in[0]));
                        StringBuilder sb = new StringBuilder();
                        String line = null;

                        while ((line = br.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        String[] t = sb.toString().split("c");
                        if (t[0].equals("s")) {

                            activity.showDay();
                        } else {
                            tv.setText("登入失敗辣!!!");
                        }

                        outputStream1.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    };

    //跳轉到註冊頁面
    private View.OnClickListener btnSignupOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();

            activity.showSignup1();
        }
    };
}
