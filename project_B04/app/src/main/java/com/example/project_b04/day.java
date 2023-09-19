package com.example.project_b04;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

import static com.example.project_b04.R.drawable.cout;


public class day extends Fragment {


    //介面元件
    private TextView tvProtein_day;
    private TextView tvSugar_day;
    private TextView tvFat_day;
    private TextView tvSodium_day;
    private TextView tvCalories_day;
    private TextView tvResidueCalories_day;
    private TextView tvDateString ;
    private ImageButton imgBtnCalendar;
    private Context myDialogContext; // 儲存"對話方塊背景"

    String noteDateString=""; // 儲存紀錄日期
    int Year, Month, Day; //儲存日期(年、月、日)

    LinearLayout lin =null;

    public day() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_day, container, false);
        //新增
        Button btnAddNum= v.findViewById(R.id.btnAddNum);
        btnAddNum.setOnClickListener(btnAddNumOnClick);
        //編輯
//        Button btnEditNum = v.findViewById(R.id.btnEditNum);
//        btnEditNum.setOnClickListener(btnEditNumOnClick);
        //推薦食物
        Button btnRecommend = v.findViewById(R.id.btnRecommend);
        btnRecommend.setOnClickListener(btnRecommendOnClick);
        //個人頁面
        Button btnPage_Day = v.findViewById(R.id.btnPage_Day);
        btnPage_Day.setOnClickListener(btnPage_DayOnClick);
        //數據報告
        Button btnReport_Day = v.findViewById(R.id.btnReport_Day);
        btnReport_Day.setOnClickListener(btnReport_DayOnClick);

        Button btnDay_Day = v.findViewById(R.id.btnDay_Day);
        btnDay_Day.setOnClickListener(btnDay_Day_DayOnClick);



        //選擇日期
        imgBtnCalendar = v.findViewById(R.id.imgBtnCalendar);
        imgBtnCalendar.setOnClickListener(imgBtnCalendarOnClick);
        //顯示數據
        tvProtein_day = v.findViewById(R.id.tvProtein_day);
        tvSugar_day = v.findViewById(R.id.tvSugar_day);
        tvFat_day = v.findViewById(R.id.tvFat_day);
        tvSodium_day = v.findViewById(R.id.tvSodium_day);
        tvCalories_day = v.findViewById(R.id.tvCalories_day);
        tvResidueCalories_day = v.findViewById(R.id.tvResidueCalories_day);
        tvDateString = v.findViewById(R.id.tvDateString);
        myDialogContext = getContext();

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = (now.get(Calendar.MONTH))+1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        tvDateString.setText(year+"/"+month+"/"+day);
        noteDateString= year+"-"+month+"-"+day;
        if(month<10) {
            noteDateString = year + "-0" + month + "-";
            if(day<10) {
                noteDateString = year + "-0" + month + "-0" + day;
            }
            else {
                noteDateString = year + "-0" + month + "-" + day;
            }
        }
        else {
            noteDateString = year + "-" + month + "-";
            if(day<10) {
                noteDateString = year + "-" + month + "-0" + day;
            }
            else{
                noteDateString = year + "-" + month + "-" + day;
            }
        }



        return v;
    }
    public void onStart() {

        lin = getActivity().findViewById(R.id.lin);


        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = (now.get(Calendar.MONTH))+1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        tvDateString.setText(year+"/"+month+"/"+day);
        noteDateString= year+"-"+month+"-"+day;
        if(month<10) {
            noteDateString = year + "-0" + month + "-";
            if(day<10) {
                noteDateString = year + "-0" + month + "-0" + day;
            }
            else {
                noteDateString = year + "-0" + month + "-" + day;
            }
        }
        else {
            noteDateString = year + "-" + month + "-";
            if(day<10) {
                noteDateString = year + "-" + month + "-0" + day;
            }
            else{
                noteDateString = year + "-" + month + "-" + day;
            }
        }

        final  String time = noteDateString;
        final InputStream[] in = {null};
        super.onStart();
        final LinearLayout finalLin = lin;
        new Thread(new Runnable() {
            @SuppressLint("ResourceType")
            @Override
            public void run() {
                try {
//      ||---------------------------------------------------------------------
//      ||          抓取"當日"資料：
//      ||          已攝取熱量(tvCalories_day)、
//      ||          蛋白質(tvProtein_day)、碳水化合物(tvSugar_day)、
//      ||          脂肪(tvFat_day)、鈉(tvSodium_day)
//      ||---------------------------------------------------------------------
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("session", "123");
                    jsonObject.put("time", time);
                    String Json = jsonObject.toString();
                    System.out.println(Json);
                    URL url = new URL("http://192.168.1.105/dayRecord.php");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(3000);
                    con.setDoOutput(true);
                    con.setDoInput(true);
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
                    String[] tokens_1 = sb.toString().split(",");

                    String[] t1_1 = tokens_1[0].split(":");
                    tvCalories_day.setText(t1_1[1]);
                    String[] t2_1 = tokens_1[1].split(":");
                    tvProtein_day.setText(t2_1[1]);
                    String[] t3_1 = tokens_1[2].split(":");
                    tvFat_day.setText(t3_1[1]);
                    String[] t4_1 = tokens_1[3].split(":");
                    tvSugar_day.setText(t4_1[1]);
                    String[] t5_1 = tokens_1[4].split(":");
                    String[] t5_1_ = t5_1[1].split("[}]");
                    tvSodium_day.setText(t5_1_[0]);

//      ||---------------------------------------------------------------------
//      ||          抓取"當日"剩餘熱量(tvResidueCalories_day)
//      ||---------------------------------------------------------------------
                    jsonObject = new JSONObject();
                    jsonObject.put("session", "123");
                    jsonObject.put("time",time);
                    Json = jsonObject.toString();
                    System.out.println(Json);
                    url = new URL("http://192.168.1.105/advice.php");
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(3000);
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setUseCaches(false);
                    con.connect();

                    outputStream1 = new DataOutputStream(con.getOutputStream());

                    outputStream1.writeBytes(Json);
                    outputStream1.flush();
                    v = con.getResponseCode();
                    System.out.println(v);

                    in[0] = con.getInputStream();

                    br = new BufferedReader(new InputStreamReader(in[0]));
                    sb = new StringBuilder();
                    line = null;

                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                        System.out.println(sb.toString());
                    //去除""
                    String str_2 = sb.toString().replace("\"", "");
                    //拆成5筆存於陣列
                    String[] tokens_2 = str_2.split("[}],");
//                    for (String token:tokens) {
//                        System.out.println(token);
//                    }

//                    // 第1筆資料
                    String[] t1 = tokens_2[0].split(",");
                    String[] t1_2 = t1[8].split(":");
                    String[] t1_2_ = t1_2[1].split("[}]");
                    tvResidueCalories_day.setText(t1_2_[0]);

//      ||---------------------------------------------------------------------
//      ||           抓取"當日"飲食紀錄
//      ||---------------------------------------------------------------------
                    jsonObject = new JSONObject();
                    jsonObject.put("session", "123");
                    jsonObject.put("time", time);
                    Json = jsonObject.toString();
                    System.out.println(Json);
                    url = new URL("http://192.168.1.105/showRecords.php");
                    con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(3000);
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    con.setUseCaches(false);
                    con.connect();

                    outputStream1 = new DataOutputStream(con.getOutputStream());

                    outputStream1.writeBytes(Json);
                    outputStream1.flush();
                    v = con.getResponseCode();
                    System.out.println(v);

                    in[0] = con.getInputStream();

                    br = new BufferedReader(new InputStreamReader(in[0]));
                    sb = new StringBuilder();
                    line = null;

                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    String str = sb.toString().replace("\"", "");
                    String[] tokens_ = str.split("[}],");
                    System.out.println("0000000000000000000");

                    System.out.println(tokens_[0]);
                    int test= tokens_.length;
                    System.out.println(test);
                    for (int i = 0; i<test; i++) {

                        String[] t3 = tokens_[i].split(",");
                        String[] t1_3 = t3[0].split(":");
//                        String[] t2_3 = t3[1].split(":");
                        String[] t3_3 = t3[2].split(":");
//                        String[] t4_3 = t3[3].split(":");
                        String[] t5_3 = t3[4].split(":");


                        Button child = new Button(myDialogContext);
                        child.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                        child.setTextSize(15);
                        child.setBackgroundResource(cout);
                        child.setTextColor(Color.rgb(184, 141, 95));
                        child.setText("    " + t1_3[1] + " ( " + t3_3[1] + " )\n    熱量：" + t5_3[1] + "  Kal");
                        System.out.println(child);

                        lin.addView(child);


                    }


                    outputStream1.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private View.OnClickListener btnDay_Day_DayOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    //新增
    private View.OnClickListener btnAddNumOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideDay();
            activity.showDay_add();
        }
    };

    //編輯
    private View.OnClickListener btnEditNumOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideDay();
            activity.showDay_edit();
        }
    };

    //推薦食物
    private View.OnClickListener btnRecommendOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideDay();
            activity.showRecommend();
        }
    };

    //個人
    private View.OnClickListener btnPage_DayOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        MainActivity activity = (MainActivity) getActivity();
            activity.hideDay();
            activity.showPage();
        }
    };

    //報告
    private View.OnClickListener btnReport_DayOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideDay();
            activity.showReport();
        }
    };

    private  View.OnClickListener imgBtnCalendarOnClick = new View.OnClickListener()
    {
        @Override
        public void onClick (View view){
        Calendar dt = Calendar.getInstance(); //取得一個日曆物件
        DatePickerDialog dDialog = new DatePickerDialog(myDialogContext, new DatePickerDialog.OnDateSetListener() {
            // 覆寫選擇日期後的事件處理方法onDateSet()，所傳入的是所選擇的年、月、日，
            // 注意:月份的索引值從0開始，因此正確的月份為(monthOfYear+1)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tvDateString.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);    // 將所選日期顯示在標籤上
            }
        }, dt.get(Calendar.YEAR), dt.get(Calendar.MONTH), dt.get(Calendar.DAY_OF_MONTH)); // 以目前日期為預設日期
        dDialog.show(); //日期選擇器對話方塊
    }
    };
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {  //不在最前端界面显示

        } else {  //重新显示到最前端
           //onStart();
        }
    }

}
