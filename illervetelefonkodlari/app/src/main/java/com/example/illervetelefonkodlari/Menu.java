package com.example.illervetelefonkodlari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

public class Menu extends AppCompatActivity {
private Button btnnormaloyun;
private Button btnSureliOyun;
private Button cikisyap;





    public void btnAnaSayfaAyar(View v){
        switch (v.getId()){
            case R.id.btnnormaloyun:
                Intent baslat=new Intent(this,MainActivity.class);
                startActivity(baslat);
                break;

            case R.id.btnSureliOyun:
                Intent SureliOyun=new Intent(this, SureliOyunActivity.class);
                startActivity(SureliOyun);
                break;
            case R.id.cikisyap:
                cikisYap();
                break;

        }

        }
private void cikisYap(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
        }
public void onBackPressed(){
        cikisYap();
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btnnormaloyun=(Button)findViewById(R.id.btnnormaloyun);
        btnSureliOyun=(Button)findViewById(R.id.btnSureliOyun);
        cikisyap=(Button)findViewById(R.id.cikisyap);
    }
    }


