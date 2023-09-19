package com.example.project_b04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class signup2 extends Fragment {

    //介面元件
    private EditText mEtName_Signup;    // 暱稱
    private EditText mEtHeight_Signup;  // 身高
    private EditText mEtWeight_Signup;  // 密碼
    private Button mBtnBack_signup2;    // "上一頁"按鈕
    private Button mBtnSignup;          // "註冊"按鈕
    private TextView mTvHint_signup2;   //提示字

    public signup2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup2, container, false);

        mEtName_Signup = v.findViewById(R.id.etName_Signup);
        mEtHeight_Signup = v.findViewById(R.id.etHeight_Signup);
        mEtWeight_Signup = v.findViewById(R.id.etWeight_Signup);

        mTvHint_signup2=v.findViewById(R.id.tvHint_signup2);

        mBtnBack_signup2 = v.findViewById(R.id.btnBack_signup2);
        mBtnBack_signup2.setOnClickListener(btnBack_signup2OnClick);
        mBtnSignup = v.findViewById(R.id.btnSignup);
        mBtnSignup.setOnClickListener(btnSignupOnClick);

        mEtName_Signup.setText("");
        mEtHeight_Signup.setText("");
        mEtWeight_Signup.setText("");
        mTvHint_signup2.setText("");
        return v;
    }

    //上一頁(返回signup1)
    private View.OnClickListener btnBack_signup2OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.showSignup1(); // 顯示註冊1頁面
            activity.hideSignup2(); // 隱藏註冊頁面
        }
    };

    //註冊
    private View.OnClickListener btnSignupOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            final  String nickname =mEtName_Signup.getText().toString();
            final  String height = mEtHeight_Signup.getText().toString();
            final  String weight =mEtWeight_Signup.getText().toString();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject =new JSONObject() ;
                        jsonObject.put("nickname",nickname);
                        jsonObject.put("height",height);
                        jsonObject.put("weight",weight);
                        jsonObject.put("session",123);
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

            activity.showSignin(); // 顯示登入頁面
            activity.hideSignup2(); // 隱藏註冊2頁面
        }
    };
}
