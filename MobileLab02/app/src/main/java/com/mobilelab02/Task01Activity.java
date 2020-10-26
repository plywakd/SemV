package com.mobilelab02;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Task01Activity extends AppCompatActivity {

    private Button calc;
    private Button exit;
    private Toolbar header;
    private EditText paramA;
    private EditText paramB;
    private EditText paramC;
    private EditText delta;
    private EditText result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task01);
        calc = (Button) findViewById(R.id.calc);
        exit = (Button) findViewById(R.id.exit);
        paramA = (EditText) findViewById(R.id.paramA);
        paramB = (EditText) findViewById(R.id.paramB);
        paramC = (EditText) findViewById(R.id.paramC);
        delta = (EditText) findViewById(R.id.deltaOutput);
        result = (EditText) findViewById(R.id.result);
        header = (Toolbar) findViewById(R.id.toolbar);
        header.setTitle("Calculate square root");

        delta.setEnabled(false);
        result.setEnabled(false);
        calc.setOnClickListener((click) ->{
            double a=0 ,b=0 ,c=0;
            boolean inputOk = true;
            if (!paramA.getText().toString().equals("")){
                a = Double.parseDouble(String.valueOf(paramA.getText()));
            }
            else {
                inputOk=false;
                createAlert("Wrong input!","param needs to be a real number");
            }
            if (!paramB.getText().toString().equals("")){
                b = Double.parseDouble(String.valueOf(paramB.getText()));
            }
            else {
                inputOk=false;
                createAlert("Wrong input!","param needs to be a real number");
            }
            if (!paramC.getText().toString().equals("")){
                c = Double.parseDouble(String.valueOf(paramC.getText()));
            }
            else {
                inputOk=false;
                createAlert("Wrong input!","param needs to be a real number");
            }
            if (inputOk == true){
                delta.setText(String.valueOf(calcDelta(a,b,c)));
            }
        });
        exit.setOnClickListener((click) -> {
            System.exit(0);
        });
    }

    public void createAlert(String title, String msg){
        AlertDialog alertDialog = new AlertDialog.Builder(Task01Activity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }

    public double calcDelta(double a, double b, double c){
        double resDelta = pow(b,2)-4*a*c;
        if (resDelta > 0){
            double x1 = (b - sqrt(resDelta))/(2*a);
            double x2 = (b + sqrt(resDelta))/(2*a);
            result.setText("X1 ="+x1+" X2="+x2);
        }
        else if (resDelta == 0){
            double x = b/(2*a);
            result.setText("X="+x);
        }
        else {
            result.setText("No real results");
        }
        return resDelta;
    }


}
