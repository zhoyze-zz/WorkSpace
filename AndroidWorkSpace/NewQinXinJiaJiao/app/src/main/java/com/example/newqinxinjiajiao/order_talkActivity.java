package com.example.newqinxinjiajiao;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class order_talkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mes_order_talk);
    }
}
