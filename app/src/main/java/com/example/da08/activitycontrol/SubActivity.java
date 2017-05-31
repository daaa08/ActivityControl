package com.example.da08.activitycontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {


    TextView textView;
    EditText editText;
    Button button;

    String value = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        // 이전 액티비티에서 넘어온 intent 객체
        final Intent intent = getIntent();  // 여기는 null이 안된다

        // 값의 묶을을 꺼낸다.
        Bundle bundle = intent.getExtras(); // 전달된 묶음의 값이 없으면 null이 됨.때문에 bundle.을 하면NullPointException 오류가 뜨게 됨
        // 단일 값을 꺼낸다 , 꺼내기전에 null 체크를 해줘야 한다
        if(bundle != null) {
            value = bundle.getString("key");
            // 그 값이 있으면 텍스트뷰에 출력
            textView.setText(value);
        }

        /*
        Bundle bundle = intent.getExtras();
        String value = bundle.getString("key");
        아래는 위의 두줄을 합쳐놓은 함수 - 자체적으로 null 처리 로직을 포함하고 있다 (그치만 단일처리만 가능)
        intent.getStringExtra("key");
         */

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 메인 액티비티에서 넘겨받은 값을 int로 변환
                int num1 = Integer.parseInt(value);
                // 전체 액티비티에 입력된 값을 받아서
                String temp = editText.getText().toString();
                // int로 변환
                int num2 = Integer.parseInt(temp);
                int result = num1 + num2;

                // ------------------- 값 반환하기 -----------------------

                // 결과값을 인텐트에 담아서
                Intent intent =  new Intent();
                intent.putExtra("result",result);
                // 셋리절트 함수에 담아서 넘겨준다
                setResult(RESULT_OK,  intent);
                // 현재 액티비티 종료
                finish();


            }
        });
    }
}
