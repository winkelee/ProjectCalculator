package com.example.inurcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<HistoryResult> pastCalcs = new ArrayList<HistoryResult>();
    TextView resultText;
    String TAG = "Logger";
    String firstNumber;
    String operator;
    String result;
    double preResult;
    String secondNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        getSupportActionBar().hide();
        resultText = findViewById(R.id.resultTextView);
        resultText.setText("0");
        operator = "1";
        ListView lv = findViewById(R.id.calcHistory);
        HistoryAdapter adapter = new HistoryAdapter(getApplicationContext(), 0, pastCalcs);
        lv.setAdapter(adapter);
    }

    public void numberEvent(View view){ //Первый блок

        String number = resultText.getText().toString();
        if (number.equals("0") && (view.getId() != R.id.bDot)){
            number = "";
        }
        switch (view.getId()){
            case R.id.b7: number = number+"7";
                break;
            case R.id.b1: number = number+"1";
                break;
            case R.id.b2: number = number+"2";
                break;
            case R.id.b3: number = number+"3";
                break;
            case R.id.b4: number = number+"4";
                break;
            case R.id.b5: number = number+"5";
                break;
            case R.id.b6: number = number+"6";
                break;
            case R.id.b8: number = number+"8";
                break;
            case R.id.b9: number = number+"9";
                break;
            case R.id.bPi:
                if(!number.contains("3.14") && !number.contains(".")){
                    number = number + "3.14";
                } else{

                }
                break;
            case R.id.b0:
                if (!number.equals("0")){
                    number = number + "0";
                }

                else{
                    number = "0";
                }
                break;
            case R.id.bDot:
                if(!number.contains(".")){
                    number = number+".";
                }
                else{

                }
                break;
            case R.id.bChange:
                if(!number.contains("-")){
                    number = "-"+number; //добавляем минус
                }
                else{
                    number = number.substring(1); //Укорачиваем строку на 1, убирая минус
                }
                break;
            case R.id.bBackspace:
                if(number.length() <= 1){
                    number = "0";
                    Log.d(TAG, "numberEvent: " + number.length());
                } else{
                    Log.d(TAG, "numberEvent: " + number.length());
                    number = number.substring(0, number.length() - 1); //Убираем последний символ строки
                    if(number.length() == 0){
                        number = "0";
                    }
                }


                break;
        }
        resultText.setText(number);
    }

    public void operatorEvent(View view){ //Второй блок
        firstNumber = resultText.getText().toString();
        resultText.setText("");
        HistoryResult historyResult = new HistoryResult("unidentified", "unidentified", "unidentified");

        switch (view.getId()){
            case R.id.bMinus: operator = "-";
                break;
            case R.id.bPlus: operator = "+";
                break;
            case R.id.bDivide: operator = "/";
                break;
            case R.id.bMultiply: operator = "*";
                break;
            case R.id.bPower: operator = "^";
                break;
            case R.id.bRoot: operator = "root";
                break;
            case R.id.bPowerTwo:
                preResult = Math.pow(Double.parseDouble(firstNumber), 2);
                result = String.valueOf(Math.round(preResult * 1000.0) / 1000.0);
                resultText.setText(result);
                historyResult.setResult(result);
                historyResult.setEquasion(firstNumber + "^2");
                pastCalcs.add(historyResult);
                break;
            case R.id.bSquareRoot:
                preResult = Math.sqrt(Double.parseDouble(firstNumber));
                result = String.valueOf(Math.round(preResult * 1000.0) / 1000.0);
                resultText.setText(result);
                historyResult.setResult(result);
                historyResult.setEquasion("sqrt(" + firstNumber + ")");
                pastCalcs.add(historyResult);
                break;
            case R.id.bSin: operator = "sin";
                preResult = Math.sin(Double.parseDouble(firstNumber));
                result = String.valueOf(Math.round(preResult * 1000.0) / 1000.0);
                resultText.setText(result);
                historyResult.setResult(result);
                historyResult.setEquasion("SIN(" + firstNumber + ")");
                pastCalcs.add(historyResult);
                break;
            case R.id.bCos: operator = "cos";
                preResult = Math.cos(Double.parseDouble(firstNumber));
                result = String.valueOf(Math.round(preResult * 1000.0) / 1000.0);
                resultText.setText(result);
                historyResult.setResult(result);
                historyResult.setEquasion("COS(" + firstNumber + ")");
                pastCalcs.add(historyResult);
                break;
            case R.id.bTan: operator = "tan";
                preResult = Math.tan(Double.parseDouble(firstNumber));
                result = String.valueOf(Math.round(preResult * 1000.0) / 1000.0);
                resultText.setText(result);
                historyResult.setResult(result);
                historyResult.setEquasion("TAN(" + firstNumber + ")");
                pastCalcs.add(historyResult);
                break;
            case R.id.bCtg: operator = "ctg";
                preResult = 1/Math.tan(Double.parseDouble(firstNumber));
                result = String.valueOf(Math.round(preResult * 1000.0) / 1000.0);
                resultText.setText(result);
                historyResult.setResult(result);
                historyResult.setEquasion("CTG(" + firstNumber + ")");
                pastCalcs.add(historyResult);
                break;
            default: resultText.setText("0");
                break;
        }
        ListView lv = findViewById(R.id.calcHistory);
        HistoryAdapter adapter = new HistoryAdapter(getApplicationContext(), 0, pastCalcs);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }

    public void calculateResult(View view){ //Третий блок
        secondNumber = resultText.getText().toString();
        HistoryResult historyResult = new HistoryResult("unidentified", "unidentified", "unidentified");

        switch (operator){
            case "-": result = String.valueOf(Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber));
                historyResult.setResult(result);
                historyResult.setEquasion(firstNumber + operator + secondNumber);
                pastCalcs.add(historyResult);
                break;
            case "+": result = String.valueOf(Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber));
                historyResult.setResult(result);
                historyResult.setEquasion(firstNumber + operator + secondNumber);
                pastCalcs.add(historyResult);
                break;
            case "*": preResult = Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
                result = String.valueOf(Math.round(preResult * 1000.0) / 1000.0);
                historyResult.setResult(result);
                historyResult.setEquasion(firstNumber + operator + secondNumber);
                pastCalcs.add(historyResult);
                break;
            case "root":
                preResult = Math.pow(Double.parseDouble(firstNumber), 1/Double.parseDouble(secondNumber));
                result = String.valueOf(Math.round(preResult * 1000.0) / 1000.0);
                historyResult.setResult(result);
                historyResult.setEquasion(firstNumber + operator + secondNumber);
                pastCalcs.add(historyResult);
                break;
            case "/":
                if (Integer.parseInt(secondNumber) == 0){

                }
                else{
                    preResult = Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
                    result= String.valueOf(Math.round(preResult * 1000.0) / 1000.0);
                    historyResult.setResult(result);
                    historyResult.setEquasion(firstNumber + operator + secondNumber);
                    pastCalcs.add(historyResult);
                }
                break;
            case "^": result =String.valueOf(Math.pow(Double.parseDouble(firstNumber), Integer.parseInt(secondNumber)));
                historyResult.setResult(result);
                historyResult.setEquasion(firstNumber + operator + secondNumber);
                pastCalcs.add(historyResult);
                break;
            case "1": result = "0";
                break;

        }
        resultText.setText(result);

        ListView lv = findViewById(R.id.calcHistory);
        HistoryAdapter adapter = new HistoryAdapter(getApplicationContext(), 0, pastCalcs);
        lv.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public void clear(View view){
        resultText.setText("0"); //результат заменяем на 0
    }
}