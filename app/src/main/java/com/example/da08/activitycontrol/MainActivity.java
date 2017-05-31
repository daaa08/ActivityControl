package com.example.da08.activitycontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnst, btnres;
    Intent intent;   // 버튼을 클릭할때마다 intent를 생성하기떄문에 메모리에 쌓임 그러므로 전역으로 빼준거임
    EditText editText2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this,SubActivity.class);

        btnst = (Button)findViewById(R.id.btnst);
        btnres = (Button)findViewById(R.id.btnres);
        editText2 = (EditText)findViewById(R.id.editText2);

        btnst.setOnClickListener(this);
        btnres.setOnClickListener(this);
    }

    public static final int BTN_RESULT = 99;
    /*
    BTN_RESULT는 호출을 구분해주는 구분자
    (숫자로만 쓰면 잘 모르기때문에 상수로 정의 )
     */

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            // 일반적인 액티비티 스타트
            case R.id.btnst :

                intent.putExtra("key","");
                startActivity(intent);  // 서브액티비티로부터 호출 ㄴㄴ
                break;
            // 값을 돌려받는 액티비티 스타트
            case R.id.btnres :
                intent.putExtra("key", editText2.getText().toString());   // editText에 값을 넣고 서브에 불러옴
//                intent.putExtra("key","from button result");  // key에 from button result를 담아서 호출
                                            // 변수 = "값"   > 변수 값 형태로 이름을 정해주는 것

                startActivityForResult(intent,BTN_RESULT);  // 서브액티비로부터 호출 받을 수 있음 아래의 온액티비티리저트를 통해서
                // SubActivuty.finish() > 결과값을 돌려줌 >MainActivity.onActivityResult(결과값을 담아서 돌려줌)
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  // 결과값이 담겨온다
                                    //  호출에서 받아온 값 , 반환해서 받아온 값
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode != RESULT_OK) {  // RESULT_OK 는 -1 이라고 툴에서 이미 정해둔 것
            switch (requestCode) {
                case BTN_RESULT:
                     // intent안 data에서 result 변수로 값을 꺼내는데 값이 없을 경우 디폴트값으로 -1을 사용한다
                    int result = data.getIntExtra("result", -1);
                    editText2.setText("결과값" + result);
//                    Toast.makeText(this,"결과값="+result,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
