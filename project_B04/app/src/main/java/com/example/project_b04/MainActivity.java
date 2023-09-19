package com.example.project_b04;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static Context myDialogContext;
    //宣告Fragment物件(共9個)
    private signin mSignin;
    private signup1 mSignup1;
    private signup2 mSignup2;
    private day mDay;
    private day_add mDayAdd;
    private day_edit mDayEdit;
    private recommend mRecommend;
    private page mPage;
    private page_edit mPageEdit;
    private report mReport;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        system
        //把建立的Fragment物件存起來
        mSignin = new signin();
        mSignup1 = new signup1();
        mSignup2 = new signup2();
        mDay = new day();
        mDayAdd = new day_add();
        mDayEdit = new day_edit();
        mRecommend = new recommend();
        mPage = new page();
        mPageEdit = new page_edit();
        mReport = new report();

        //把Fragment物件放到FrameLayout
       getSupportFragmentManager().beginTransaction()
                .add(R.id.frameLay,mSignin,"Sign in")
                .add(R.id.frameLay,mSignup1,"Sign up1")
                .add(R.id.frameLay,mSignup2,"Sign up2")
               .add(R.id.frameLay,mDay,"Day")
                .add(R.id.frameLay,mDayAdd,"Day Add")
                .add(R.id.frameLay,mDayEdit,"Day Edit")
                .add(R.id.frameLay,mPage,"Page")
                .add(R.id.frameLay,mPageEdit,"Page Edit")
                .add(R.id.frameLay,mRecommend,"Recommend")
                .add(R.id.frameLay,mReport,"Report")
//                .add(R.id.frameLay,mCamera,"Camera")
                .hide(mRecommend)
                .hide(mDayEdit)
                .hide(mDayAdd)
                .hide(mPageEdit)
                .hide(mPage)
                .hide(mReport)
                .hide(mDay)
                .hide(mSignup1)
                .hide(mSignup2)
                .commit();
    }

    //顯示頁面
    public void showSignin(){  //顯示登入頁面
        getSupportFragmentManager().beginTransaction()
            .show(mSignin)
            .commit();
    }
    public void showSignup1(){  //顯示註冊頁面1
        getSupportFragmentManager().beginTransaction()
                .show(mSignup1)
                .commit();
    }
    public void showSignup2(){  //顯示註冊頁面2
        getSupportFragmentManager().beginTransaction()
                .show(mSignup2)
                .commit();
    }
    public void showDay(){  //顯示每日記錄頁面

        getSupportFragmentManager().beginTransaction()
                .show(mDay)
                .commit();
    }
    public void showDay_edit(){  //顯示編輯每日記錄頁面
        getSupportFragmentManager().beginTransaction()
                .show(mDayEdit)
                .commit();
    }
    public void showDay_add(){  //顯示新增每日記錄頁面
        getSupportFragmentManager().beginTransaction()
                .show(mDayAdd)
                .commit();
    }
    public void showPage(){  //顯示個人頁面
        getSupportFragmentManager().beginTransaction()
                .show(mPage)
                .commit();
    }
    public void showPage_edit(){  //顯示編輯個人頁面
        getSupportFragmentManager().beginTransaction()
                .show(mPageEdit)
                .commit();
    }
    public void showRecommend(){  //顯示推薦食物頁面
        getSupportFragmentManager().beginTransaction()
                .show(mRecommend)
                .commit();
    }

    public void showReport(){  //顯示紀錄報告頁面
        getSupportFragmentManager().beginTransaction()
                .show(mReport)
                .commit();
    }

    public void showCamera(){  //顯示相機
        getSupportFragmentManager().beginTransaction()
//                .show(mCamera)
                .commit();
    }

    //隱藏頁面

    public void hideSignup1(){  //隱藏註冊頁面1
        getSupportFragmentManager().beginTransaction()
                .hide(mSignup1)
                .commit();
    }
    public void hideSignup2(){  //隱藏註冊頁面2
        getSupportFragmentManager().beginTransaction()
                .hide(mSignup2)
                .commit();
    }
    public void hidePage(){  //隱藏個人資訊頁面
        getSupportFragmentManager().beginTransaction()
                .hide(mPage)
                .commit();
    }

    public void hideDay(){  //隱藏每日記錄頁面

        getSupportFragmentManager().beginTransaction()
                .hide(mDay)
                .commit();
    }
    public void hideDay_add(){  //隱藏新增每日記錄頁面
        getSupportFragmentManager().beginTransaction()
                .hide(mDayAdd)
                .commit();
    }
    public void hideDay_edit(){  //隱藏編輯每日記錄頁面
        getSupportFragmentManager().beginTransaction()
                .hide(mDayEdit)
                .commit();
    }
    public void hideRecommend(){  //隱藏推薦食物頁面
        getSupportFragmentManager().beginTransaction()
                .hide(mRecommend)
                .commit();
    }
    public void hidePage_edit(){  //隱藏推薦食物頁面

        getSupportFragmentManager().beginTransaction()
                .hide(mPageEdit)
                .commit();
    }
    public void hideReport(){  //隱藏數據報告
        getSupportFragmentManager().beginTransaction()
                .hide(mReport)
                .commit();
    }







}
