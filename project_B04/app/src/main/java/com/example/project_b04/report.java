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
import java.text.DecimalFormat;
import java.util.Calendar;


public class report extends Fragment {

    //最下排按鈕
    private Button btnPage_Report;
    private Button btnRecommend_Report;
    private Button btnAdd_Report;
    private Button btnDay_Report;
    private Button btnReport_Report;

    // 已攝取熱量 / 應攝取熱量
    private TextView tvAbsorb,tvAbsorbTotal;

    // 當日資料
    private TextView report_day_top_month,report_day_top_day;  // 月份 日期
    private TextView report_day_protein,report_day_sugar;      // 蛋白質 碳水化合物
    private TextView report_day_fat,report_day_sodium;         // 脂肪 鈉

    // 當月資料
    private TextView tvReport_month_top_month;                 //月份
    private TextView report_month_protein,report_month_sugar;  // 蛋白質 碳水化合物
    private TextView report_month_fat,report_month_sodium;     // 脂肪 鈉

    //當日百分比
    private TextView percentage_day_protein; // 蛋白質
    private TextView percentage_day_sugar;   // 碳水化合物
    private TextView percentage_day_fat;     // 脂肪
    private TextView percentage_day_sodium;  // 鈉

    //當月百分比
    private TextView percentage_month_protein; // 蛋白質
    private TextView percentage_month_sugar;   // 碳水化合物
    private TextView percentage_month_fat;     // 脂肪
    private TextView percentage_month_sodium;  // 鈉

    // 儲存紀錄日期
    String dayString = "";
    String monthString = "";

    public report() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_report, container, false);

        //按鈕
        btnPage_Report = v.findViewById(R.id.btnPage_Report);
        btnPage_Report.setOnClickListener(btnPage_ReportOnClick);

        btnDay_Report = v.findViewById(R.id.btnDay_Report);
        btnDay_Report.setOnClickListener(btnDay_ReportOnClick);

        btnRecommend_Report = v.findViewById(R.id.btnRecommend_Report);
        btnRecommend_Report.setOnClickListener(btnRecommend_ReportOnClick);

        btnAdd_Report = v.findViewById(R.id.btnAdd_Report);
        btnAdd_Report.setOnClickListener(btnAdd_ReportOnClick);

        btnReport_Report = v.findViewById(R.id.btnReport_Report);
        btnReport_Report.setOnClickListener(btnReport_ReportOnClick);

        //顯示數據
        tvAbsorb = v.findViewById(R.id.tvAbsorb);
        tvAbsorbTotal = v.findViewById(R.id.tvAbsorbTotal);

        report_day_top_month = v.findViewById(R.id.report_day_top_month);
        report_day_top_day = v.findViewById(R.id.report_day_top_day);
        report_day_protein = v.findViewById(R.id.report_day_protein);
        report_day_sugar = v.findViewById(R.id.report_day_sugar);
        report_day_fat = v.findViewById(R.id.report_day_fat);
        report_day_sodium = v.findViewById(R.id.report_day_sodium);

        tvReport_month_top_month = v.findViewById(R.id.tvReport_month_top_month);
        report_month_protein = v.findViewById(R.id.report_month_protein);
        report_month_sugar = v.findViewById(R.id.report_month_sugar);
        report_month_fat = v.findViewById(R.id.report_month_fat);
        report_month_sodium = v.findViewById(R.id.report_month_sodium);

        percentage_day_protein = v.findViewById(R.id.percentage_day_protein);
        percentage_day_sugar = v.findViewById(R.id.percentage_day_sugar);
        percentage_day_fat = v.findViewById(R.id.percentage_day_fat);
        percentage_day_sodium = v.findViewById(R.id.percentage_day_sodium);

        percentage_month_protein = v.findViewById(R.id.percentage_month_protein);
        percentage_month_sugar = v.findViewById(R.id.percentage_month_sugar);
        percentage_month_fat = v.findViewById(R.id.percentage_month_fat);
        percentage_month_sodium = v.findViewById(R.id.percentage_month_sodium);

        //抓取當下日期
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = (now.get(Calendar.MONTH))+1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        String m,d;//紀錄月、日

        if(month<10) {
            m = "0"+month;
            monthString = year + "-0" + month + "-";
            if(day<10) {
                d="0"+day;
                dayString = year + "-0" + month + "-0" + day;
            }
            else {
                d=""+day;
                dayString = year + "-0" + month + "-" + day;
            }
        }
        else {
            m = ""+month;
            monthString = year + "-" + month + "-";
            if(day<10) {
                d="0"+day;
                dayString = year + "-" + month + "-0" + day;
            }
            else{
                d=""+day;
                dayString = year + "-" + month + "-" + day;
            }
        }

        report_day_top_month.setText(m);
        tvReport_month_top_month.setText(m);
        report_day_top_day.setText(d);


        final  String time_day = dayString;
        final  String time_month = monthString;
        final InputStream[] in = {null};
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//      ||---------------------------------------------------------------------
//      ||           抓取 每日應攝取熱量(tvAbsorb)
//      ||---------------------------------------------------------------------
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
                    String[] tokens_1 = sb.toString().split(",");
                    String[] t1_1 = tokens_1[5].split(":");
                    String[] t1_1_ = t1_1[1].split("[}]");
                    tvAbsorbTotal.setText(t1_1_[0]);

//      ||---------------------------------------------------------------------
//      ||           抓取"當日"資料：
//      ||          已攝取熱量(tvAbsorb)、
//      ||           蛋白質(report_day_protein)、碳水化合物(report_day_sugar)、
//      ||           脂肪(report_day_fat)、鈉(report_day_sodium)
//      ||---------------------------------------------------------------------
                    jsonObject =new JSONObject() ;
                    jsonObject.put("session","123");
                    jsonObject.put("time",time_day);
                    Json=jsonObject.toString();
                    System.out.println(Json);
                    url =new URL("http://192.168.1.105/dayRecord.php");
                    con =(HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(3000);
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setUseCaches(false);
                    con.connect();

                    outputStream1 = new DataOutputStream(con.getOutputStream());

                    outputStream1.writeBytes(Json);
                    outputStream1.flush();
                    v =con.getResponseCode();
                    System.out.println(v);

                    in[0] =con.getInputStream();

                    br=new BufferedReader(new InputStreamReader(in[0]));
                    sb=new StringBuilder();
                    line = null ;

                    while ((line = br.readLine())!=null){
                        sb.append(line+"\n");
                    }
                    String[] tokens_2 = sb.toString().split(",");

                    String[] t1_2 = tokens_2[0].split(":");
                    tvAbsorb.setText(t1_2[1]);
                    String[] t2_2 = tokens_2[1].split(":");
                    report_day_protein.setText(t2_2[1]);
                    String[] t3_2 = tokens_2[2].split(":");
                    report_day_fat.setText(t3_2[1]);
                    String[] t4_2 = tokens_2[3].split(":");
                    report_day_sugar.setText(t4_2[1]);
                    String[] t5_2 = tokens_2[4].split(":");
                    String[] t5_2_ = t5_2[1].split("[}]");
                    report_day_sodium.setText(t5_2_[0]);

                    DecimalFormat df = new DecimalFormat("##.00");

                    double num1=Double.parseDouble(t2_2[1]);
                    double num2=Double.parseDouble(t3_2[1]);
                    double num3=Double.parseDouble(t4_2[1]);
                    double num4=Double.parseDouble(t5_2_[0]);

                    double n1=(num1/(num1+num2+num3+num4))*100;
                    double n2=(num2/(num1+num2+num3+num4))*100;
                    double n3=(num3/(num1+num2+num3+num4))*100;
                    double n4=(num4/(num1+num2+num3+num4))*100;

                    n1=Double.parseDouble(df.format(n1));
                    n2=Double.parseDouble(df.format(n2));
                    n3=Double.parseDouble(df.format(n3));
                    n4=Double.parseDouble(df.format(n4));

                    percentage_day_protein.setText(n1+" %");
                    percentage_day_sugar.setText(n2+" %");
                    percentage_day_fat.setText(n3+" %");
                    percentage_day_sodium.setText(n4+" %");

//      ||---------------------------------------------------------------------
//      ||          抓取"當月"資料：
//      ||          蛋白質(report_month_protein)、碳水化合物(report_month_sugar)、
//      ||          脂肪(report_month_fat)、鈉(report_month_sodium)
//      ||---------------------------------------------------------------------

                    jsonObject =new JSONObject() ;
                    jsonObject.put("session","123");
                    jsonObject.put("time",time_month);
                    Json=jsonObject.toString();
                    System.out.println(Json);
                    url =new URL("http://192.168.1.105/monthRecord.php");
                    con =(HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(3000);
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setUseCaches(false);
                    con.connect();

                    outputStream1 = new DataOutputStream(con.getOutputStream());

                    outputStream1.writeBytes(Json);
                    outputStream1.flush();
                    v =con.getResponseCode();
                    System.out.println(v);

                    in[0] =con.getInputStream();

                    br=new BufferedReader(new InputStreamReader(in[0]));
                    sb=new StringBuilder();
                    line = null ;

                    while ((line = br.readLine())!=null){
                        sb.append(line+"\n");
                    }

                    String[] tokens_3 = sb.toString().split(",");
                    String[] t2_3 = tokens_3[1].split(":");
                    report_month_protein.setText(t2_3[1]);
                    String[] t3_3 = tokens_3[2].split(":");
                    report_month_fat.setText(t3_3[1]);
                    String[] t4_3 = tokens_3[3].split(":");
                    report_month_sugar.setText(t4_3[1]);
                    String[] t5_3 = tokens_3[4].split(":");
                    String[] t5_3_ = t5_3[1].split("[}]");
                    report_month_sodium.setText(t5_3_[0]);

                    double num1_=Double.parseDouble(t2_3[1]);
                    double num2_=Double.parseDouble(t3_3[1]);
                    double num3_=Double.parseDouble(t4_3[1]);
                    double num4_=Double.parseDouble(t5_3_[0]);

                    double n1_=(num1_/(num1_+num2_+num3_+num4_))*100;
                    double n2_=(num2/(num1_+num2_+num3_+num4_))*100;
                    double n3_=(num3/(num1_+num2_+num3_+num4_))*100;
                    double n4_=(num4/(num1_+num2_+num3_+num4_))*100;

                    n1_=Double.parseDouble(df.format(n1_));
                    n2_=Double.parseDouble(df.format(n2_));
                    n3_=Double.parseDouble(df.format(n3_));
                    n4_=Double.parseDouble(df.format(n4_));

                    percentage_month_protein.setText(n1_+" %");
                    percentage_month_sugar.setText(n2_+" %");
                    percentage_month_fat.setText(n3_+" %");
                    percentage_month_sodium.setText(n4_+" %");

                    outputStream1.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        return v;
    }

    private View.OnClickListener btnReport_ReportOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final  String username = "A6209729";
            final  String time_day = dayString;
            final  String time_month = monthString;
            final InputStream[] in = {null};
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
//      ||---------------------------------------------------------------------
//      ||           抓取 每日應攝取熱量(tvAbsorb)
//      ||---------------------------------------------------------------------
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
                        String[] tokens_1 = sb.toString().split(",");
                        String[] t1_1 = tokens_1[5].split(":");
                        String[] t1_1_ = t1_1[1].split("[}]");
                        tvAbsorbTotal.setText(t1_1_[0]);

//      ||---------------------------------------------------------------------
//      ||           抓取"當日"資料：
//      ||          已攝取熱量(tvAbsorb)、
//      ||           蛋白質(report_day_protein)、碳水化合物(report_day_sugar)、
//      ||           脂肪(report_day_fat)、鈉(report_day_sodium)
//      ||---------------------------------------------------------------------
                        jsonObject =new JSONObject() ;
                        jsonObject.put("session","123");
                        jsonObject.put("time",time_day);
                        Json=jsonObject.toString();
                        System.out.println(Json);
                        url =new URL("http://192.168.1.105/dayRecord.php");
                        con =(HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.setConnectTimeout(3000);
                        con.setDoOutput(true);
                        con.setDoInput(true);
                        con.setUseCaches(false);
                        con.connect();

                        outputStream1 = new DataOutputStream(con.getOutputStream());

                        outputStream1.writeBytes(Json);
                        outputStream1.flush();
                        v =con.getResponseCode();
                        System.out.println(v);

                        in[0] =con.getInputStream();

                        br=new BufferedReader(new InputStreamReader(in[0]));
                        sb=new StringBuilder();
                        line = null ;

                        while ((line = br.readLine())!=null){
                            sb.append(line+"\n");
                        }
                        String[] tokens_2 = sb.toString().split(",");

                        String[] t1_2 = tokens_2[0].split(":");
                        tvAbsorb.setText(t1_2[1]);
                        String[] t2_2 = tokens_2[1].split(":");
                        report_day_protein.setText(t2_2[1]);
                        String[] t3_2 = tokens_2[2].split(":");
                        report_day_fat.setText(t3_2[1]);
                        String[] t4_2 = tokens_2[3].split(":");
                        report_day_sugar.setText(t4_2[1]);
                        String[] t5_2 = tokens_2[4].split(":");
                        String[] t5_2_ = t5_2[1].split("[}]");
                        report_day_sodium.setText(t5_2_[0]);

                        DecimalFormat df = new DecimalFormat("##.00");

                        double num1=Double.parseDouble(t2_2[1]);
                        double num2=Double.parseDouble(t3_2[1]);
                        double num3=Double.parseDouble(t4_2[1]);
                        double num4=Double.parseDouble(t5_2_[0]);

                        double n1=(num1/(num1+num2+num3+num4))*100;
                        double n2=(num2/(num1+num2+num3+num4))*100;
                        double n3=(num3/(num1+num2+num3+num4))*100;
                        double n4=(num4/(num1+num2+num3+num4))*100;

                        n1=Double.parseDouble(df.format(n1));
                        n2=Double.parseDouble(df.format(n2));
                        n3=Double.parseDouble(df.format(n3));
                        n4=Double.parseDouble(df.format(n4));

                        percentage_day_protein.setText(n1+" %");
                        percentage_day_sugar.setText(n2+" %");
                        percentage_day_fat.setText(n3+" %");
                        percentage_day_sodium.setText(n4+" %");

//      ||---------------------------------------------------------------------
//      ||          抓取"當月"資料：
//      ||          蛋白質(report_month_protein)、碳水化合物(report_month_sugar)、
//      ||          脂肪(report_month_fat)、鈉(report_month_sodium)
//      ||---------------------------------------------------------------------

                        jsonObject =new JSONObject() ;
                        jsonObject.put("session","123");
                        jsonObject.put("time",time_month);
                        Json=jsonObject.toString();
                        System.out.println(Json);
                        url =new URL("http://192.168.1.105/monthRecord.php");
                        con =(HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.setConnectTimeout(3000);
                        con.setDoOutput(true);
                        con.setDoInput(true);
                        con.setUseCaches(false);
                        con.connect();

                        outputStream1 = new DataOutputStream(con.getOutputStream());

                        outputStream1.writeBytes(Json);
                        outputStream1.flush();
                        v =con.getResponseCode();
                        System.out.println(v);

                        in[0] =con.getInputStream();

                        br=new BufferedReader(new InputStreamReader(in[0]));
                        sb=new StringBuilder();
                        line = null ;

                        while ((line = br.readLine())!=null){
                            sb.append(line+"\n");
                        }

                        String[] tokens_3 = sb.toString().split(",");
                        String[] t2_3 = tokens_3[1].split(":");
                        report_month_protein.setText(t2_3[1]);
                        String[] t3_3 = tokens_3[2].split(":");
                        report_month_fat.setText(t3_3[1]);
                        String[] t4_3 = tokens_3[3].split(":");
                        report_month_sugar.setText(t4_3[1]);
                        String[] t5_3 = tokens_3[4].split(":");
                        String[] t5_3_ = t5_3[1].split("[}]");
                        report_month_sodium.setText(t5_3_[0]);

                        double num1_=Double.parseDouble(t2_3[1]);
                        double num2_=Double.parseDouble(t3_3[1]);
                        double num3_=Double.parseDouble(t4_3[1]);
                        double num4_=Double.parseDouble(t5_3_[0]);

                        double n1_=(num1_/(num1_+num2_+num3_+num4_))*100;
                        double n2_=(num2/(num1_+num2_+num3_+num4_))*100;
                        double n3_=(num3/(num1_+num2_+num3_+num4_))*100;
                        double n4_=(num4/(num1_+num2_+num3_+num4_))*100;

                        n1_=Double.parseDouble(df.format(n1_));
                        n2_=Double.parseDouble(df.format(n2_));
                        n3_=Double.parseDouble(df.format(n3_));
                        n4_=Double.parseDouble(df.format(n4_));

                        percentage_month_protein.setText(n1_+" %");
                        percentage_month_sugar.setText(n2_+" %");
                        percentage_month_fat.setText(n3_+" %");
                        percentage_month_sodium.setText(n4_+" %");

                        outputStream1.close();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    };

    //個人
    private View.OnClickListener btnPage_ReportOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideReport();
            activity.showPage();
        }
    };

    //每日紀錄
    private  View.OnClickListener btnDay_ReportOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideReport();
            activity.showDay();
        }
    };

    //新增
    private View.OnClickListener btnAdd_ReportOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideReport();
            activity.showDay_add();
        }
    };

    //食物推薦
    private View.OnClickListener btnRecommend_ReportOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideReport();
            activity.showRecommend();
        }
    };

}
