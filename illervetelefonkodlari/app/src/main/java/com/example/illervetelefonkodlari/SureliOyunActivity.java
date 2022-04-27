package com.example.illervetelefonkodlari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;


public class SureliOyunActivity extends AppCompatActivity {

    static final int YENIDEN_BASLAT = 1;
    private TextView soruS;
    // private TextView ilerlemeYazisi;
    private Button cevap1S;
    private Button cevap2S;
    private Button cevap3S;
    private Button cevap4S;
    private ArrayList<Soru> sorular;
    private int dogruCevapSayisi;
    private int soruNo,ToplamSure=60000;
    private int dogruCevap;
    private TextView puanS;
    private TextView txtSure;
    //private ProgressBar progressBar;



    private void soruDosyasiOkuS(){
        InputStream stream = getResources().openRawResource(R.raw.sorular);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, Charset.forName("UTF-8")));
        Soru yeniSoru;
        String soru = null;
        sorular = new ArrayList<Soru>();
        try {
            while ((soru = reader.readLine()) != null) {
                String[] soruIcerik = soru.split(";");
                if ((soruIcerik[0].equalsIgnoreCase("A") || soruIcerik[0].equalsIgnoreCase("B")
                        || soruIcerik[0].equalsIgnoreCase("C") ||
                        soruIcerik[0].equalsIgnoreCase("D"))){
                    yeniSoru = new Soru(soruIcerik[1],soruIcerik[2],soruIcerik[3],
                            soruIcerik[4],soruIcerik[5], soruIcerik[0]);
                    sorular.add(yeniSoru);
                }
            }
        } catch (IOException e) {
        }
    }

    private Soru simdikiSoruyuBulS(){
        int i;
        i = 0;
        for (Soru soru:sorular){
            if (i == soruNo){
                return soru;
            } else {
                i++;
            }
        }
        return null;
    }
    /*public void btnTekrarOyna(View v){

            Intent tekrarOyna=new Intent(this,MainActivity.class);
            finish();
            startActivity(tekrarOyna);


    }*/
    private void menuyegeridonS(){
        Intent menugeridon=new Intent(this,Menu.class);
        startActivity(menugeridon);


    }
    private void TekrarOyna(){
        Intent tekrarOynas = new Intent(this, SureliOyunActivity.class);
        finish();
        startActivity(tekrarOynas);
    }
    private void simdikiSoruyuGosterS(){
        Soru simdikiSoruS;
        simdikiSoruS = simdikiSoruyuBulS();
        if (simdikiSoruS != null){
            soruS.setText((soruNo + 1) + ") " + simdikiSoruS.getSoruCumle() + " İlimizin telefon kodu hangisidir ?");
            cevap1S.setText("A) " + simdikiSoruS.getCevap1());
            cevap2S.setText("B) " + simdikiSoruS.getCevap2());
            cevap3S.setText("C) " + simdikiSoruS.getCevap3());
            cevap4S.setText("D) " + simdikiSoruS.getCevap4());
            if (simdikiSoruS.getDogruCevap().equalsIgnoreCase("A")){
                dogruCevap = 1;
            } else {
                if (simdikiSoruS.getDogruCevap().equalsIgnoreCase("B")){
                    dogruCevap = 2;
                } else {
                    if (simdikiSoruS.getDogruCevap().equalsIgnoreCase("C")){
                        dogruCevap = 3;
                    } else {
                        dogruCevap = 4;
                    }
                }
            }
        }
    }

    private void sinaviBaslatS(){
        soruNo = 0;
        dogruCevapSayisi = 0;
        // progressBar.setProgress(0);
        puanS.setVisibility(View.GONE);
        Collections.shuffle(sorular);
        simdikiSoruyuGosterS();
        cevap1S.setEnabled(true);
        cevap2S.setEnabled(true);
        cevap3S.setEnabled(true);
        cevap4S.setEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surelioyun);
        soruS = (TextView) findViewById(R.id.soruS);
        soruS.setEnabled(false);
        cevap1S = (Button) findViewById(R.id.cevap1S);
        cevap2S = (Button) findViewById(R.id.cevap2S);
        cevap3S = (Button) findViewById(R.id.cevap3S);
        cevap4S = (Button) findViewById(R.id.cevap4S);
        txtSure=(TextView)findViewById(R.id.txtSure);
        Button sinaviBaslatS = (Button) findViewById(R.id.sinaviBaslatS);
        Button btnmenuyegeridonS=(Button) findViewById(R.id.btnmenuyegeridonS);
        // progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // ilerlemeYazisi = (TextView) findViewById(R.id.ilerlemeYazisi) ;
        puanS = (TextView) findViewById(R.id.puanS);
        cevap1S.setOnClickListener(cevapTiklaS);
        cevap2S.setOnClickListener(cevapTiklaS);
        cevap3S.setOnClickListener(cevapTiklaS);
        cevap4S.setOnClickListener(cevapTiklaS);
        sinaviBaslatS.setOnClickListener(sinaviBaslatTiklaS);
        btnmenuyegeridonS.setOnClickListener(menuyegeridonTiklaS);
        new CountDownTimer(ToplamSure,1000){

            @Override
            public void onTick(long l) {
             txtSure.setText(( 2)+ ":"+ ((l % 60000) / 1000 ));
            }

            @Override
            public void onFinish() {
                txtSure.setText("0:00");
                cevap1S.setEnabled(false);
                cevap2S.setEnabled(false);
                cevap3S.setEnabled(false);
                cevap4S.setEnabled(false);
                puanS.setEnabled(false);
                sinaviBaslatS.setVisibility(View.VISIBLE);
String mesaj2= "Süreniz Bitti \n Toplam Dogru Sayınız:"+dogruCevapSayisi+"\n "
        +"Tekrar Oynamak İçin Tekrar Oyna Butonuna Basin.";
                Toast.makeText(getApplicationContext(),mesaj2
                       ,Toast.LENGTH_LONG).show();



            }
        }.start();

        soruDosyasiOkuS();
        sinaviBaslatS();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (requestCode == YENIDEN_BASLAT) {
            if (resultCode == RESULT_OK) {
                sinaviBaslatS();
            }
        }
    }


    public View.OnClickListener cevapTiklaS = new View.OnClickListener() {
        public void onClick(View v){
            if (((String)v.getTag()).equalsIgnoreCase(Integer.toString(dogruCevap))){
                dogruCevapSayisi++;
            }
            soruNo++;

            if (soruNo < 10){
                simdikiSoruyuGosterS();
            } else {
                puanS.setVisibility(View.VISIBLE);
                puanS.setText("Dogru Sayısı:"+dogruCevapSayisi);
                String mesaj = +dogruCevapSayisi + " soruyu doğru cevapladiniz.";
                Toast.makeText(getApplicationContext(), mesaj, Toast.LENGTH_LONG).show();
                cevap1S.setEnabled(false);
                cevap2S.setEnabled(false);
                cevap3S.setEnabled(false);
                cevap4S.setEnabled(false);
            }
        }
    };

    public View.OnClickListener menuyegeridonTiklaS=new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            menuyegeridonS();
        }
    };


    public View.OnClickListener sinaviBaslatTiklaS = new View.OnClickListener() {
        public void onClick(View v){
            TekrarOyna();
            sinaviBaslatS();
        }
    };






}