package com.example.project_b04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class page extends Fragment {

    private TextView tvPageAccountNum;  //帳號
    private TextView tvPageNickname;    //暱稱
    private TextView tvPageHeightNum;   //身高
    private TextView tvPageWeightNum;   //體重
    private TextView tvIdealWeight;     //理想體重
    private TextView tvPageCaloriesNum; //每日攝取熱量

    public page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page, container, false);
        //日記
        Button btnDay_Page = v.findViewById(R.id.btnDay_Page);
        btnDay_Page.setOnClickListener(btnDay_PageOnClick);
        //登出
        Button btnLogOut = v.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(btnLogOutOnClick);
        //編輯個人資訊
        Button btnEdit = v.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(btnEditOnClick);
        //報告
        Button btnReport_Page = v.findViewById(R.id.btnReport_Page);
        btnReport_Page.setOnClickListener(btnReport_PageOnClick);
        //新增
        Button btnAdd_Page = v.findViewById(R.id.btnAdd_Page);
        btnAdd_Page.setOnClickListener(btnAdd_PageOnClick);
        //推薦食物
        Button btnRecommend_page = v.findViewById(R.id.btnRecommend_page);
        btnRecommend_page.setOnClickListener(btnRecommend_pageOnClick);
        //個人
        Button btnPage_Page = v.findViewById(R.id.btnPage_Page);
        btnPage_Page.setOnClickListener(btnPage_PageOnClick);

        //顯示資料之元件
        tvPageAccountNum = v.findViewById(R.id.tvPageAccountNum);
        tvPageNickname = v.findViewById(R.id.tvPageNickname);
        tvPageHeightNum = v.findViewById(R.id.tvPageHeightNum);
        tvPageWeightNum = v.findViewById(R.id.tvPageWeightNum);
        tvIdealWeight = v.findViewById(R.id.tvIdealWeight);
        tvPageCaloriesNum = v.findViewById(R.id.tvPageCaloriesNum);


//        final  String account = "A6209729" ;
        final InputStream[] in = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    URL url =new URL("http://192.168.1.105/showUserData.php");
                    HttpURLConnection con =(HttpURLConnection) url.openConnection();
                    JSONObject jsonObject =new JSONObject() ;
//                    jsonObject.put("userName",account);
                    jsonObject.put("session","123");
                    String Json=jsonObject.toString();
                    System.out.println(Json);
                    con.setRequestMethod("POST");
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

                    in[0] =con.getInputStream();

                    BufferedReader br=new BufferedReader(new InputStreamReader(in[0]));
                    StringBuilder sb=new StringBuilder();
                    String line = null ;

                    while ((line = br.readLine())!=null){
                        sb.append(line+"\n");
                    }
                    System.out.println(sb);

                    String[] tokens = sb.toString().split(",");
//                    for (String token:tokens) {
//                        System.out.println(token);
//                    }
                    String[] t1 = tokens[0].split("[\"]");
                    tvPageAccountNum.setText(t1[3]);
                    String[] t2 = tokens[1].split("[\"]");
                    tvPageNickname.setText(t2[3]);
                    String[] t3 = tokens[2].split(":");
                    tvPageHeightNum.setText(t3[1]+" cm");
                    String[] t4 = tokens[3].split(":");
                    tvPageWeightNum.setText(t4[1]+" kg");
                    String[] t5 = tokens[4].split(":");
                    tvIdealWeight.setText(t5[1]+" kg");
                    String[] t8 = tokens[5].split(":");
                    String[] t8_ = t8[1].split("[}]");
                    tvPageCaloriesNum.setText(t8_[0]+" kal");

                    outputStream1.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        return v;
    }


    private View.OnClickListener btnPage_PageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            final InputStream[] in = {null};
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject =new JSONObject() ;
                        jsonObject.put("session","123");
                        String Json=jsonObject.toString();
                        System.out.println(Json);
                        URL url =new URL("http://192.168.1.105/showUserData.php");
                        HttpURLConnection con =(HttpURLConnection) url.openConnection();
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

                        in[0] =con.getInputStream();

                        BufferedReader br=new BufferedReader(new InputStreamReader(in[0]));
                        StringBuilder sb=new StringBuilder();
                        String line = null ;

                        while ((line = br.readLine())!=null){
                            sb.append(line+"\n");

                        }
                        System.out.println(sb);
                        String[] tokens = sb.toString().split(",");
//                        for (String token:tokens) {
//                            System.out.println(token);
//                        }

                        String[] t1 = tokens[0].split("[\"]");
                        tvPageAccountNum.setText(t1[3]);
                        String[] t2 = tokens[1].split("[\"]");
                        tvPageNickname.setText(t2[3]);
                        String[] t3 = tokens[2].split(":");
                        tvPageHeightNum.setText(t3[1]+" cm");
                        String[] t4 = tokens[3].split(":");
                        tvPageWeightNum.setText(t4[1]+" kg");
                        String[] t5 = tokens[4].split(":");
                        tvIdealWeight.setText(t5[1]+" kg");
                        String[] t8 = tokens[5].split(":");
                        String[] t8_ = t8[1].split("[}]");
                        tvPageCaloriesNum.setText(t8_[0]+" kal");

                        outputStream1.close();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    };

    //登出
    private View.OnClickListener btnLogOutOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        URL url =new URL("http://192.168.1.105/logout.php");
                        HttpURLConnection con =(HttpURLConnection) url.openConnection();
                        JSONObject jsonObject =new JSONObject() ;
                        jsonObject.put("session","123");
                        String Json=jsonObject.toString();
                        System.out.println(Json);
                        con.setRequestMethod("POST");
                        con.setConnectTimeout(3000);
                        con.setDoOutput(true);
                        con.setDoInput(true);
                        con.setUseCaches(false);
                        con.connect();

                        DataOutputStream outputStream1 = new DataOutputStream(con.getOutputStream());

                        outputStream1.writeBytes(Json);
                        outputStream1.flush();

                        outputStream1.close();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
            activity.hidePage();
            activity.hideDay();
            activity.showSignin();
        }
    };

    //編輯個人資料

    private View.OnClickListener btnEditOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hidePage();
            activity.showPage_edit();
        }
    };

    //日記

    private View.OnClickListener btnDay_PageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            runOnUiThread(new Runnable() {
//                public void run() {
//
//                }
//            });
            MainActivity activity = (MainActivity) getActivity();

            activity.hidePage();
            activity.showDay();
                            }

        };


    //報告
    private View.OnClickListener btnReport_PageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hidePage();
            activity.showReport();
        }
    };

    //新增
    private View.OnClickListener btnAdd_PageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hidePage();
            activity.showDay_add();
        }
    };

    //食物推薦
    private View.OnClickListener btnRecommend_pageOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hidePage();
            activity.showRecommend();
        }
    };

}
