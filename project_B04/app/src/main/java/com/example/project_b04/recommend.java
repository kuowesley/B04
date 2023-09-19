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


public class recommend extends Fragment {

    public recommend() {
        // Required empty public constructor
    }
    private TextView name1,name2,name3,name4,name5; //食物名稱
    private TextView type1,type2,type3,type4,type5; //食物類型
    private TextView amount1,amount2,amount3,amount4,amount5;  //單一分量
    private TextView protein1,protein2,protein3,protein4,protein5;   //蛋白質
    private TextView sugar1,sugar2,sugar3,sugar4,sugar5;       //碳水化合物
    private TextView sodium1,sodium2,sodium3,sodium4,sodium5;  //鈉
    private TextView calories1,calories2,calories3,calories4,calories5;  //熱量
    private TextView fat1,fat2,fat3,fat4,fat5;    //脂肪
    private TextView tvResidueCalories_recommend; // 剩餘熱量

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("1111");

        View v = inflater.inflate(R.layout.fragment_recommend, container, false);

        Button btnSuggest = v.findViewById(R.id.btnSuggest);
        btnSuggest.setOnClickListener(btnSuggestOnClick);

        Button btnPage_recommend = v.findViewById(R.id.btnPage_recommend);
        btnPage_recommend.setOnClickListener(btnPage_recommendOnClick);

        Button btnAdd_recommend = v.findViewById(R.id.btnAdd_recommend);
        btnAdd_recommend.setOnClickListener(btnAdd_recommendOnClick);

        Button btnDay_recommend = v.findViewById(R.id.btnDay_recommend);
        btnDay_recommend.setOnClickListener(btnDay_recommendOnClick);

        Button btnReport_recommend = v.findViewById(R.id.btnReport_recommend);
        btnReport_recommend.setOnClickListener(btnReport_recommendOnClick);

        tvResidueCalories_recommend= v.findViewById(R.id.tvResidueCalories_recommend);

        name1 = v.findViewById(R.id.name1);
        type1 = v.findViewById(R.id.type1);
        amount1 = v.findViewById(R.id.amount1);
        protein1 = v.findViewById(R.id.protein1);
        sugar1 = v.findViewById(R.id.sugar1);
        sodium1 = v.findViewById(R.id.sodium1);
        calories1 = v.findViewById(R.id.calories1);
        fat1 = v.findViewById(R.id.fat1);

        name2 = v.findViewById(R.id.name2);
        type2 = v.findViewById(R.id.type2);
        amount2 = v.findViewById(R.id.amount2);
        protein2 = v.findViewById(R.id.protein2);
        sugar2 = v.findViewById(R.id.sugar2);
        sodium2 = v.findViewById(R.id.sodium2);
        calories2 = v.findViewById(R.id.calories2);
        fat2 = v.findViewById(R.id.fat2);

        name3 = v.findViewById(R.id.name3);
        type3 = v.findViewById(R.id.type3);
        amount3 = v.findViewById(R.id.amount3);
        protein3 = v.findViewById(R.id.protein3);
        sugar3 = v.findViewById(R.id.sugar3);
        sodium3 = v.findViewById(R.id.sodium3);
        calories3 = v.findViewById(R.id.calories3);
        fat3 = v.findViewById(R.id.fat3);

        name4 = v.findViewById(R.id.name4);
        type4 = v.findViewById(R.id.type4);
        amount4 = v.findViewById(R.id.amount4);
        protein4 = v.findViewById(R.id.protein4);
        sugar4 = v.findViewById(R.id.sugar4);
        sodium4 = v.findViewById(R.id.sodium4);
        calories4 = v.findViewById(R.id.calories4);
        fat4 = v.findViewById(R.id.fat4);

        name5 = v.findViewById(R.id.name5);
        type5 = v.findViewById(R.id.type5);
        amount5 = v.findViewById(R.id.amount5);
        protein5 = v.findViewById(R.id.protein5);
        sugar5 = v.findViewById(R.id.sugar5);
        sodium5 = v.findViewById(R.id.sodium5);
        calories5 = v.findViewById(R.id.calories5);
        fat5 = v.findViewById(R.id.fat5);
        final InputStream[] in = {null};

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject =new JSONObject() ;
                    jsonObject.put("session","123");
                    String Json=jsonObject.toString();
                    System.out.println(Json);
                    URL url =new URL("http://192.168.1.105/advice.php");
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
//                        System.out.println(sb.toString());
                    //去除""
                    String str =sb.toString().replace("\"", "");
                    //拆成5筆存於陣列
                    String[] tokens = str.split("[}],");
                    for (String token:tokens) {
                        System.out.println(token);
                    }

                    //第1筆資料
                    String[] t1 = tokens[0].split(",");
                    String[] t1_1 = t1[0].split(":");
                    name1.setText(t1_1[1]);
                    String[] t1_2 = t1[1].split(":");
                    type1.setText(t1_2[1]);
                    String[] t1_3 = t1[2].split(":");
                    amount1.setText(t1_3[1]);
                    String[] t1_4 = t1[3].split(":");
                    protein1.setText(t1_4[1]);
                    String[] t1_5 = t1[4].split(":");
                    sugar1.setText(t1_5[1]);
                    String[] t1_6 = t1[5].split(":");
                    sodium1.setText(t1_6[1]);
                    String[] t1_7 = t1[6].split(":");
                    calories1.setText(t1_7[1]);
                    String[] t1_8 = t1[7].split(":");
                    fat1.setText(t1_8[1]);
                    String[] t1_9 = t1[8].split(":");
                    String[] t1_9_ = t1_9[1].split("[}]");
                    tvResidueCalories_recommend.setText(t1_9_[0]);
//
//                        //第2筆資料
                    String[] t2 = tokens[1].split(",");
                    String[] t2_1 = t2[0].split(":");
                    name2.setText(t2_1[1]);
                    String[] t2_2 = t2[1].split(":");
                    type2.setText(t2_2[1]);
                    String[] t2_3 = t2[2].split(":");
                    amount2.setText(t2_3[1]);
                    String[] t2_4 = t2[3].split(":");
                    protein2.setText(t2_4[1]);
                    String[] t2_5 = t2[4].split(":");
                    sugar2.setText(t2_5[1]);
                    String[] t2_6 = t2[5].split(":");
                    sodium2.setText(t2_6[1]);
                    String[] t2_7 = t2[6].split(":");
                    calories2.setText(t2_7[1]);
                    String[] t2_8 = t2[7].split(":");
                    String[] t2_8_ = t2_8[1].split("[}]");
                    fat2.setText(t2_8_[0]);
//
                    String[] t3 = tokens[2].split(",");
                    String[] t3_1 = t3[0].split(":");
                    name3.setText(t3_1[1]);
                    String[] t3_2 = t3[1].split(":");
                    type3.setText(t3_2[1]);
                    String[] t3_3 = t2[2].split(":");
                    amount3.setText(t3_3[1]);
                    String[] t3_4 = t3[3].split(":");
                    protein3.setText(t3_4[1]);
                    String[] t3_5 = t3[4].split(":");
                    sugar3.setText(t3_5[1]);
                    String[] t3_6 = t3[5].split(":");
                    sodium3.setText(t3_6[1]);
                    String[] t3_7 = t3[6].split(":");
                    calories3.setText(t3_7[1]);
                    String[] t3_8 = t3[7].split(":");
                    String[] t3_8_ = t3_8[1].split("[}]");
                    fat3.setText(t3_8_[0]);

                    String[] t4 = tokens[3].split(",");
                    String[] t4_1 = t4[0].split(":");
                    name4.setText(t4_1[1]);
                    String[] t4_2 = t4[1].split(":");
                    type4.setText(t4_2[1]);
                    String[] t4_3 = t4[2].split(":");
                    amount4.setText(t4_3[1]);
                    String[] t4_4 = t4[3].split(":");
                    protein4.setText(t4_4[1]);
                    String[] t4_5 = t4[4].split(":");
                    sugar4.setText(t4_5[1]);
                    String[] t4_6 = t4[5].split(":");
                    sodium4.setText(t4_6[1]);
                    String[] t4_7 = t4[6].split(":");
                    calories4.setText(t4_7[1]);
                    String[] t4_8 = t4[7].split(":");
                    String[] t4_8_ = t4_8[1].split("[}]");
                    fat4.setText(t4_8_[0]);

                    String[] t5 = tokens[4].split(",");
                    String[] t5_1 = t5[0].split(":");
                    name5.setText(t5_1[1]);
                    String[] t5_2 = t5[1].split(":");
                    type5.setText(t5_2[1]);
                    String[] t5_3 = t5[2].split(":");
                    amount5.setText(t5_3[1]);
                    String[] t5_4 = t5[3].split(":");
                    protein5.setText(t5_4[1]);
                    String[] t5_5 = t5[4].split(":");
                    sugar5.setText(t5_5[1]);
                    String[] t5_6 = t5[5].split(":");
                    sodium5.setText(t5_6[1]);
                    String[] t5_7 = t5[6].split(":");
                    calories5.setText(t5_7[1]);
                    String[] t5_8 = t5[7].split(":");
                    String[] t5_8_ = t5_8[1].split("[}]");
                    fat5.setText(t5_8_[0]);

                    outputStream1.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        return v;
    }

    //搜尋推薦食物
    private View.OnClickListener btnSuggestOnClick  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final InputStream[] in = {null};

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject =new JSONObject() ;
                        jsonObject.put("session","123");
                        String Json=jsonObject.toString();
                        System.out.println(Json);
                        URL url =new URL("http://192.168.1.105/advice.php");
//                        URL url =new URL("http://192.168.43.86/advice.php");
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
//                        System.out.println(sb.toString());
                        //去除""
                        String str =sb.toString().replace("\"", "");
                        //拆成5筆存於陣列
                        String[] tokens = str.split("[}],");
                        for (String token:tokens) {
                            System.out.println(token);
                        }

                    // 第1筆資料
                    String[] t1 = tokens[0].split(",");
                    String[] t1_1 = t1[0].split(":");
                    name1.setText(t1_1[1]);
                    String[] t1_2 = t1[1].split(":");
                    type1.setText(t1_2[1]);
                    String[] t1_3 = t1[2].split(":");
                    amount1.setText(t1_3[1]);
                    String[] t1_4 = t1[3].split(":");
                    protein1.setText(t1_4[1]);
                    String[] t1_5 = t1[4].split(":");
                    sugar1.setText(t1_5[1]);
                    String[] t1_6 = t1[5].split(":");
                    sodium1.setText(t1_6[1]);
                    String[] t1_7 = t1[6].split(":");
                    calories1.setText(t1_7[1]);
                    String[] t1_8 = t1[7].split(":");
                    fat1.setText(t1_8[1]);
                    String[] t1_9 = t1[8].split(":");
                    String[] t1_9_ = t1_9[1].split("[}]");
                    tvResidueCalories_recommend.setText(t1_9_[0]);

                    // 第2筆資料
                    String[] t2 = tokens[1].split(",");
                    String[] t2_1 = t2[0].split(":");
                    name2.setText(t2_1[1]);
                    String[] t2_2 = t2[1].split(":");
                    type2.setText(t2_2[1]);
                    String[] t2_3 = t2[2].split(":");
                    amount2.setText(t2_3[1]);
                    String[] t2_4 = t2[3].split(":");
                    protein2.setText(t2_4[1]);
                    String[] t2_5 = t2[4].split(":");
                    sugar2.setText(t2_5[1]);
                    String[] t2_6 = t2[5].split(":");
                    sodium2.setText(t2_6[1]);
                    String[] t2_7 = t2[6].split(":");
                    calories2.setText(t2_7[1]);
                    String[] t2_8 = t2[7].split(":");
                    String[] t2_8_ = t2_8[1].split("[}]");
                    fat2.setText(t2_8_[0]);

                    // 第3筆資料
                    String[] t3 = tokens[2].split(",");
                    String[] t3_1 = t3[0].split(":");
                    name3.setText(t3_1[1]);
                    String[] t3_2 = t3[1].split(":");
                    type3.setText(t3_2[1]);
                    String[] t3_3 = t2[2].split(":");
                    amount3.setText(t3_3[1]);
                    String[] t3_4 = t3[3].split(":");
                    protein3.setText(t3_4[1]);
                    String[] t3_5 = t3[4].split(":");
                    sugar3.setText(t3_5[1]);
                    String[] t3_6 = t3[5].split(":");
                    sodium3.setText(t3_6[1]);
                    String[] t3_7 = t3[6].split(":");
                    calories3.setText(t3_7[1]);
                    String[] t3_8 = t3[7].split(":");
                    String[] t3_8_ = t3_8[1].split("[}]");
                    fat3.setText(t3_8_[0]);

                    // 第4筆資料
                    String[] t4 = tokens[3].split(",");
                    String[] t4_1 = t4[0].split(":");
                    name4.setText(t4_1[1]);
                    String[] t4_2 = t4[1].split(":");
                    type4.setText(t4_2[1]);
                    String[] t4_3 = t4[2].split(":");
                    amount4.setText(t4_3[1]);
                    String[] t4_4 = t4[3].split(":");
                    protein4.setText(t4_4[1]);
                    String[] t4_5 = t4[4].split(":");
                    sugar4.setText(t4_5[1]);
                    String[] t4_6 = t4[5].split(":");
                    sodium4.setText(t4_6[1]);
                    String[] t4_7 = t4[6].split(":");
                    calories4.setText(t4_7[1]);
                    String[] t4_8 = t4[7].split(":");
                    String[] t4_8_ = t4_8[1].split("[}]");
                    fat4.setText(t4_8_[0]);

                    // 第5筆資料
                    String[] t5 = tokens[4].split(",");
                    String[] t5_1 = t5[0].split(":");
                    name5.setText(t5_1[1]);
                    String[] t5_2 = t5[1].split(":");
                    type5.setText(t5_2[1]);
                    String[] t5_3 = t5[2].split(":");
                    amount5.setText(t5_3[1]);
                    String[] t5_4 = t5[3].split(":");
                    protein5.setText(t5_4[1]);
                    String[] t5_5 = t5[4].split(":");
                    sugar5.setText(t5_5[1]);
                    String[] t5_6 = t5[5].split(":");
                    sodium5.setText(t5_6[1]);
                    String[] t5_7 = t5[6].split(":");
                    calories5.setText(t5_7[1]);
                    String[] t5_8 = t5[7].split(":");
                    String[] t5_8_ = t5_8[1].split("[}]");
                    fat5.setText(t5_8_[0]);

                        outputStream1.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    };


    //個人
    private View.OnClickListener btnPage_recommendOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideRecommend();
            activity.showPage();
        }
    };

    //新增紀錄
    private View.OnClickListener btnAdd_recommendOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideRecommend();
            activity.showDay_add();
        }
    };

    //每日紀錄
    private View.OnClickListener btnDay_recommendOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideRecommend();
            activity.showDay();
        }
    };

    //報告
    private View.OnClickListener btnReport_recommendOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideRecommend();
            activity.showReport();
        }
    };
}
