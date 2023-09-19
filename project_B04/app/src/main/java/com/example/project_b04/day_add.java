package com.example.project_b04;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import static android.app.Activity.RESULT_OK;


public class day_add extends Fragment {

    //介面元件
    private EditText mEtFoodName; // 食物名稱
    private EditText mEtAmount;   // 份數

    private TextView tvTypeNum;//類型
    private TextView tvOneAmountNum;//單一分量
    private TextView tvProteinNum;//蛋白質
    private TextView tvSugarNum;//碳水化合物
    private TextView tvSodiumNum;//鈉
    private TextView tvCaloriesNum;//熱量
    private TextView tvFatNum;//脂肪

    //類型. 單一分量. 熱量. 蛋白質. 脂肪. 碳水化合物. 鈉
    final String[] food =new String[7];

    public day_add() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_day_add, container, false);

        mEtFoodName = v.findViewById(R.id.etFoodName);
        mEtAmount = v.findViewById(R.id.etAmount);

        Button btnAddNew = v.findViewById(R.id.btnAddNew);
        btnAddNew.setOnClickListener(btnAddNewOnClick);
        Button btnCancelDayAdd = v.findViewById(R.id.btnCancelDayAdd);
        btnCancelDayAdd.setOnClickListener(btnCancelDayAddOnClick);

        Button btnPreview = v.findViewById(R.id.btnPreview);
        btnPreview.setOnClickListener(btnPreviewOnClick);

        tvTypeNum = v.findViewById(R.id.tvTypeNum);
        tvOneAmountNum = v.findViewById(R.id.tvOneAmountNum);
        tvProteinNum = v.findViewById(R.id.tvProteinNum);
        tvSugarNum = v.findViewById(R.id.tvSugarNum);
        tvSodiumNum = v.findViewById(R.id.tvSodiumNum);
        tvCaloriesNum = v.findViewById(R.id.tvCaloriesNum);
        tvFatNum = v.findViewById(R.id.tvFatNum);

        FloatingActionButton floatingBtnCamera =v.findViewById(R.id.floatingBtnCamera);
        //相簿
        floatingBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

        return v;
    }

    //相簿點圖片後執行之內容
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            System.out.println(uri);
            ContentResolver cr = getActivity().getContentResolver();
            String result = null;
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                if (bitmap != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                    baos.flush();
                    baos.close();

                    byte[] bitmapBytes = baos.toByteArray();
                    result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
                    result = "data:image/jpeg;base64,"+result;
                    result = result.replaceAll("[\\s\\t\\n\\r]", " ");

                }
                JSONObject jsonObject = new JSONObject();;
                jsonObject.put("pic",result);
                final String Json=jsonObject.toString();



                new Thread(new Runnable() {


                    @Override
                    public void run() {
                        try {
                            URL url =new URL("http://192.168.1.105/pic.php");
                            final HttpURLConnection con =(HttpURLConnection) url.openConnection();
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
                            InputStream in=null;
                            in=con.getInputStream();

                            BufferedReader br=new BufferedReader(new InputStreamReader(in));
                            StringBuilder sb=new StringBuilder();
                            String line = null ;

                            while ((line = br.readLine())!=null){
                                sb.append(line+"\n");
                            }
                            String[] tokens___ = sb.toString().split("py\n");
                            String[] t1___=tokens___[1].split("\n");
                            mEtFoodName.setText(t1___[0]);

                            System.out.println(sb);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(), e);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //預覽輸入值之數據
    private View.OnClickListener btnPreviewOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        final  String foodName =mEtFoodName.getText().toString();
        final  String amount = mEtAmount.getText().toString();
        final InputStream[] in = {null};

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject =new JSONObject() ;
                    jsonObject.put("foodName", URLEncoder.encode(foodName,"UTF-8"));
                    jsonObject.put("amount",amount);
                    String Json=jsonObject.toString();
                    System.out.println(Json);
                    URL url =new URL("http://192.168.1.105/showFoodData.php");
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
                    String[] t=sb.toString().split("a");

                    if(t[0].equals("f")){
                        mEtFoodName.setText("查詢失敗");
                    }
                    else {
                        String str = sb.toString().replace("\"", "");
                        System.out.println("0000000000000000");
                        System.out.println(str);
                        String[] tokens = str.split(",");

                        String[] t2 = tokens[0].split(":");
                        tvTypeNum.setText(t2[1]);
                        food[0] = t2[1];

                        String[] t3 = tokens[2].split(":");
                        tvOneAmountNum.setText(t3[1]);
                        food[1] = t3[1];

                        String[] t4 = tokens[3].split(":");
                        tvCaloriesNum.setText(t4[1] + " kal");
                        food[2] = t4[1];

                        String[] t5 = tokens[4].split(":");
                        tvProteinNum.setText(t5[1] + " kal");
                        food[3] = t5[1];

                        String[] t6 = tokens[5].split(":");
                        tvFatNum.setText(t6[1] + " kal");
                        food[4] = t6[1];

                        String[] t7 = tokens[6].split(":");
                        tvSugarNum.setText(t7[1] + " kal");
                        food[5] = t7[1];

                        String[] t8 = tokens[7].split(":");
                        String[] t8_ = t8[1].split("[}]");
                        tvSodiumNum.setText(t8_[0] + " kal");
                        food[6] = t8[1];

                        outputStream1.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        }
    };

    //新增
    private View.OnClickListener btnAddNewOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        MainActivity activity = (MainActivity) getActivity();
            final  String foodName =mEtFoodName.getText().toString();
            final  String howmuch = mEtAmount.getText().toString();
            final InputStream[] in = {null};

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject jsonObject =new JSONObject() ;
                        jsonObject.put("foodname", URLEncoder.encode(foodName,"UTF-8"));
                        jsonObject.put("session","123");
                        jsonObject.put("type",food[0]);
                        jsonObject.put("unit",food[3]);
                        jsonObject.put("portion",food[1]);
                        jsonObject.put("kal",food[2]);
                        jsonObject.put("proteni",howmuch);
                        jsonObject.put("fat",food[4]);
                        jsonObject.put("carbohydrate",food[5]);
                        jsonObject.put("Na",food[6]);
                        String Json=jsonObject.toString();
                        System.out.println(Json);
                        URL url =new URL("http://192.168.1.105/newrecord_php.php");
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
                        System.out.println(sb.toString());

                        outputStream1.close();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }).start();

        activity.hideDay_add();
        activity.showDay();
            mEtFoodName.setText("");
            mEtAmount.setText("1");
            tvTypeNum.setText("");
            tvOneAmountNum.setText("");
            tvProteinNum.setText("");
            tvSugarNum.setText("");
            tvSodiumNum.setText("");
            tvCaloriesNum.setText("");
            tvFatNum.setText("");
        }
    };

    //取消新增 -->回到每日紀錄主頁
    private View.OnClickListener btnCancelDayAddOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MainActivity activity = (MainActivity) getActivity();
            activity.hideDay_add();
            activity.showDay();
        }
    };
}
