package com.example.alcancia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.util.Const;
import com.example.util.Util;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rdgGrupo;
    private RadioButton radioButton;
    private TextView txtCount50, txtCount100, txtCount200, txtCount500, txtCount1000, txtCountotal;
    private TextView txtSum50, txtSum100, txtSum200, txtSum500, txtSum1000, txtSumTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.checkPermission(MainActivity.this);
        init();
        calculateValues();

    }

    private void init() {
        rdgGrupo = (RadioGroup)findViewById(R.id.rdgGrupo);
        txtCount50 = (TextView)findViewById(R.id.txtCount50);
        txtCount100 = (TextView)findViewById(R.id.txtCount100);
        txtCount200 = (TextView)findViewById(R.id.txtCount200);
        txtCount500 = (TextView)findViewById(R.id.txtCount500);
        txtCount1000 = (TextView)findViewById(R.id.txtCount1000);
        txtCountotal = (TextView)findViewById(R.id.txtCountotal);

        txtSum50 = (TextView)findViewById(R.id.txtSum50);
        txtSum100 = (TextView)findViewById(R.id.txtSum100);
        txtSum200 = (TextView)findViewById(R.id.txtSum200);
        txtSum500 = (TextView)findViewById(R.id.txtSum500);
        txtSum1000 = (TextView)findViewById(R.id.txtSum1000);
        txtSumTotal = (TextView)findViewById(R.id.txtSumTotal);
    }

    private void calculateValues(){
        txtCount50.setText("Cantidad: " + DataBaseBO.getCountByDenominacion(Const.DEN_50));
        txtCount100.setText("Cantidad: " + DataBaseBO.getCountByDenominacion(Const.DEN_100));
        txtCount200.setText("Cantidad: " + DataBaseBO.getCountByDenominacion(Const.DEN_200));
        txtCount500.setText("Cantidad: " + DataBaseBO.getCountByDenominacion(Const.DEN_500));
        txtCount1000.setText("Cantidad: " + DataBaseBO.getCountByDenominacion(Const.DEN_100));
        txtCountotal.setText("Cantidad Total: " + DataBaseBO.getCount());

        txtSum50.setText("Total: $" + DataBaseBO.getSumByDenominacion(Const.DEN_50));
        txtSum100.setText("Total: $" + DataBaseBO.getSumByDenominacion(Const.DEN_100));
        txtSum200.setText("Total: $" + DataBaseBO.getSumByDenominacion(Const.DEN_200));
        txtSum500.setText("Total: $" + DataBaseBO.getSumByDenominacion(Const.DEN_500));
        txtSum1000.setText("Total: $" + DataBaseBO.getSumByDenominacion(Const.DEN_1000));
        txtSumTotal.setText("Total: $" + DataBaseBO.getSum());
    }

    public void OnInsert(View view) {
        String coinselected = "";

        int selectedId = rdgGrupo.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        coinselected = radioButton.getText().toString();

        if(DataBaseBO.existDataBase()){
            saveInsert(coinselected);
        }else{
            if(DataBaseBO.createDataBase()){
                saveInsert(coinselected);
            }
        }

    }

    private void saveInsert(String coinselected) {
        if(DataBaseBO.saveInsertCoin(coinselected)){
            Util.mostrarAlertDialog(MainActivity.this,"saving successfully!");
            calculateValues();
        }
    }
}