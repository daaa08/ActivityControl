# ActivityControl
Activity 끼리 값 주고 받기

## startActivityForResult 
이 함수로 Activity를 실행하면 실행된 Activity가 종료되면서 아래의 onActivity 함수를 호출해준다.
```java
// 액티비티를 실행하는 버튼을 구분하기 위한 플래그 
final int BTN_RESULT = 99;

Intent intent = new Intent(this,서브.class);
startActivityForResult(intent,BTN_RESULT);
```
[소스코드 전체보기](https://github.com/daaa08/ActivityControl/blob/master/app/src/main/java/com/example/da08/activitycontrol/MainActivity.java)


## setResult

호출되는 서브.class에 작성되는 코드
```java

Intent intent = new Intent(); // 이미 생성되어있는 Activity를 사용하기 때문에 Context를 필요로하지 않는다.
intent.putExtra("result", "결과값");

// RESERT_OK는 부모Activity에 이미 정의되어 있는 플래그값으로 처리가 성공적이라는 것을 의미함.
// setResult 함수는 현재 Activity에 Intent를 저장하기때문에 18번줄에서 언급한바와 같이 Context를 따로 필요로하지 않음.
setResult(RESERT_OK, "결과값");

```
[소스코드 전체보기](https://github.com/daaa08/ActivityControl/blob/master/app/src/main/java/com/example/da08/activitycontrol/SubActivity.java)

## onActivityResult

```java

// requestCode = startActivityForResult를 실행한 주체를 구분하기위한 플래그 
// resultCode = 결과처리의 성공여부 | RESULT_OK = 성공
// data = 돌려받은 값이 담겨있는 Intent

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
                    break;
            }
        }
    }
```
