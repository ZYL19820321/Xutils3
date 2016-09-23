package com.example.zhangxin.shoujiyanzheng;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import static cn.smssdk.SMSSDK.submitVerificationCode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String id = "1743fcf108590";
    private String psw = "97ebd51304b6e32376b3f56b0b4ec817";
    private EditText et;
    private Button btn;
    private Button btn1;
    private String phone;
    private EditText et1;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        SMSSDK.initSDK(this, id, psw);
        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        String da = data.toString();
                        System.out.println("11111111111" + da);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        String da = data.toString();
                        System.out.println("222222222" + da);
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                        String da = data.toString();
                        System.out.println("33333333" + da);
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    private void initView() {
        et = (EditText) findViewById(R.id.et);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        et1 = (EditText) findViewById(R.id.et1);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                submit();
                phone = et.getText().toString().trim();
                SMSSDK.getSupportedCountries();
                SMSSDK.getVerificationCode("+86", phone);
                break;
            case R.id.btn1:
                code = et1.getText().toString().trim();
                submitVerificationCode("+86", phone, code);
                break;
        }
    }


    private void submit() {
        // validate
        String et1String = et1.getText().toString().trim();
        if (TextUtils.isEmpty(et1String)) {
            Toast.makeText(this, "et1String不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
