package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.calculatorText);
        display.setShowSoftInputOnFocus(false); //No default keyboard!!!

        display.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(getString(R.string.calculator_text).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd){
        String oldString = display.getText().toString();
        String defaultText = getString(R.string.calculator_text);
        int cursorPosition = display.getSelectionStart(); //cursor position!

        if(oldString.equals(defaultText)){
            oldString="";
            cursorPosition = 0;
        }

        /* from the cursorPosition, we´ll have the left side of the string and the right side, the new digit will be in the middle¨*/
        String leftString = oldString.substring(0, cursorPosition);
        String rightString = oldString.substring(cursorPosition);

        display.setText(String.format("%s%s%s", leftString, strToAdd, rightString));

        display.setSelection(cursorPosition + 1);

    }

    private void removeOneCharacter(){
        int cursorPosition = display.getSelectionStart();
        int textLength = display.getText().length();

        if(cursorPosition>0 && textLength>0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPosition - 1, cursorPosition, ""); //SpannableStringBuilder allows you to replace characters, in this case, the caracter between cursorPos-1 and cursorPos is replaced by ""
            display.setText(selection);
            display.setSelection(cursorPosition - 1);
        }
    }

    public void zeroButton(View view){
        updateText("0");
    }

    public void oneButton(View view){
        updateText("1");
    }

    public void twoButton(View view){
        updateText("2");
    }

    public void threeButton(View view){
        updateText("3");
    }

    public void fourButton(View view){
        updateText("4");
    }

    public void fiveButton(View view){
        updateText("5");
    }

    public void sixButton(View view){
        updateText("6");
    }

    public void sevenButton(View view){
        updateText("7");
    }

    public void eightButton(View view){
        updateText("8");
    }

    public void nineButton(View view){
        updateText("9");
    }

    public void addButton(View view){
        updateText("+");
    }

    public void substractButton(View view){
        updateText("-");
    }

    public void multiplyButton(View view){
        updateText("x");
    }

    public void divideButton(View view){
        updateText("/");
    }

    public void leftParenthesisButton(View view){
        updateText("(");
    }

    public void rightParenthesisButton(View view){
        updateText(")");
    }

    public void clearButton(View view){
        display.setText("");
    }

    public void powerButton(View view){
        updateText("^");
    }

    public void equalsButton(View view){
        String userExpresion = display.getText().toString();

        userExpresion = userExpresion.replaceAll("x", "*");

        Expression exp = new Expression(userExpresion);
        String result=String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void dotButton(View view){
        updateText(".");
    }

    public void plusMinusButton(View view){
        //TODO
    }

    public void backspaceButton(View view){
        removeOneCharacter();
    }
}