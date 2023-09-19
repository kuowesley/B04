package com.example.project_b04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class signup1 extends Fragment {

    //介面元件
    private EditText mEtAccountNum;   // 帳號
    private EditText mEtPassword;     // 密碼
    private EditText mEtPW;           // 重新輸入密碼
    private Button mBtnNext;          // "下一頁"按鈕
    private Button mBtnBackSignin;    // "返回登入"按鈕
//    private TextView mTvHint_signup1; //提示字

    public signup1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup1, container, false);

        mEtAccountNum = v.findViewById(R.id.etAccountNum);
        mEtPassword = v.findViewById(R.id.etPassword);
        mEtPW = v.findViewById(R.id.etPW);

//        mTvHint_signup1=v.findViewById(R.id.tvHint_signup1);

        mBtnNext = v.findViewById(R.id.btnSignup);
        mBtnNext.setOnClickListener(btnBtnNextOnClick);
        mBtnBackSignin = v.findViewById(R.id.btnBackSignin);
        mBtnBackSignin.setOnClickListener(btnBackSigninOnClick);

        mEtAccountNum.setText("");
        mEtPassword.setText("");
        mEtPW.setText("");
        return v;
    }

    //按下一頁鍵
    private View.OnClickListener btnBtnNextOnClick  = new View.OnClickListener()   {
        @Override
        public void onClick(View view)  {
            MainActivity activity = (MainActivity) getActivity();
            final String Username=mEtAccountNum.getText().toString();
            final String Password = mEtPassword.getText().toString();
            final String pw = mEtPW.getText().toString();

            if(pw != Password)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject =new JSONObject() ;
                            jsonObject.put("username",Username);
                            jsonObject.put("password",Password);
                            jsonObject.put("session",123);
                            String Json=jsonObject.toString();
                            System.out.println(Json);
                          URL url =new URL("http://192.168.1.105/newAccount_PHP.php");
                            HttpURLConnection con =(HttpURLConnection) url.openConnection();
                            InputStream in=null;
                            con.setRequestMethod("GET");
                            con.setConnectTimeout(3000);
                            con.setDoOutput(true);
                            con.setDoInput(true);
                            con.setUseCaches(false);
                            con.connect();

                            DataOutputStream outputStream1 = new DataOutputStream(con.getOutputStream());

                            outputStream1.writeBytes(Json);
                            outputStream1.flush();
                            int v =con.getResponseCode();
                            System.out.println(v);
                            in=con.getInputStream();
                            BufferedReader br=new BufferedReader(new InputStreamReader(in));
                            StringBuilder sb=new StringBuilder();
                            String line=null;
                            while ((line=br.readLine())!=null){
                                sb.append(line+"\n");
                            }
                            System.out.println(sb.toString());
                            outputStream1.close();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();

            activity.showSignup2(); // 顯示註冊2頁面
            activity.hideSignup1(); // 隱藏註冊1頁面
        }
        }
    };

    //返回登入
    private View.OnClickListener btnBackSigninOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.showSignin(); // 顯示登入頁面
            activity.hideSignup1(); // 隱藏註冊1頁面
        }
    };
}
