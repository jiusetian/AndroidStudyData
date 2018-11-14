package com.androidstudydata.propertyanima;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.androidstudydata.R;

public class AnimaActivity extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anima);

        btn=findViewById(R.id.value_anima);

        AnimaTest animaTest=new AnimaTest();
        animaTest.ofObjectBtn(this,btn);

//        findViewById(R.id.value_anima).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AnimaTest animaTest = new AnimaTest();
//                animaTest.valueAnimaTest();
//
//            }
//        });
    }
}
