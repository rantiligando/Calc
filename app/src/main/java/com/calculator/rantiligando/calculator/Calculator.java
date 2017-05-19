package com.calculator.rantiligando.calculator;

import java.math.BigDecimal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * The Calculator program implements an application that
 * performs basic arithmetic operations and some scientific calculator features.
 *
 * @author  Rema Antiligando
 * @version 1.0
 * @since   2017-03-30
 */

public class Calculator extends AppCompatActivity {
    OnClickListener ocl;
    Button btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9;
    Button opr_plus,opr_minus,opr_times,opr_divide,equals,del,base3,dot,backspace,signs;
    EditText et;
    char operator;
    String str;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        et = (EditText) findViewById(R.id.display);
        et.setText("0");
        btn_0 = (Button) this.findViewById(R.id.btn_0);
        btn_1 = (Button) this.findViewById(R.id.btn_1);
        btn_2 = (Button) this.findViewById(R.id.btn_2);
        btn_3 = (Button) this.findViewById(R.id.btn_3);
        btn_4 = (Button) this.findViewById(R.id.btn_4);
        btn_5 = (Button) this.findViewById(R.id.btn_5);
        btn_6 = (Button) this.findViewById(R.id.btn_6);
        btn_7 = (Button) this.findViewById(R.id.btn_7);
        btn_8 = (Button) this.findViewById(R.id.btn_8);
        btn_9 = (Button) this.findViewById(R.id.btn_9);
        opr_plus = (Button) this.findViewById(R.id.btn_plus);
        opr_minus = (Button) this.findViewById(R.id.btn_minus);
        opr_times = (Button) this.findViewById(R.id.btn_mul);
        opr_divide = (Button) this.findViewById(R.id.btn_div);
        equals = (Button) this.findViewById(R.id.btn_equal);
        del = (Button) this.findViewById(R.id.btn_del);
        base3 = (Button) this.findViewById(R.id.btn_base3);
        dot = (Button) this.findViewById(R.id.btn_dot);
        backspace = (Button) this.findViewById(R.id.btn_backspace);
        signs = (Button) this.findViewById(R.id.btn_sign);

        ocl = new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Button test = (Button) arg0;
                String value = et.getText().toString();

                switch (test.getId()){
                    case R.id.btn_0 :
                        display(value,btn_0.getText().toString());
                        break;
                    case R.id.btn_1 :
                        display(value,btn_1.getText().toString());
                        break;
                    case R.id.btn_2 :
                        display(value,btn_2.getText().toString());
                        break;
                    case R.id.btn_3 :
                        display(value,btn_3.getText().toString());
                        break;
                    case R.id.btn_4 :
                        display(value,btn_4.getText().toString());
                        break;
                    case R.id.btn_5 :
                        display(value,btn_5.getText().toString());
                        break;
                    case R.id.btn_6 :
                        display(value,btn_6.getText().toString());
                        break;
                    case R.id.btn_7 :
                        display(value,btn_7.getText().toString());
                        break;
                    case R.id.btn_8 :
                        display(value,btn_8.getText().toString());
                        break;
                    case R.id.btn_9 :
                        display(value,btn_9.getText().toString());
                        break;
                    case R.id.btn_plus :
                        compute(getOp());
                        setOp('+');
                        setPrev(value);
                        setNumberStatus(1);
                        break;
                    case R.id.btn_minus :
                        compute(getOp());
                        setOp('-');
                        setPrev(value);
                        setNumberStatus(1);
                        break;
                    case R.id.btn_mul :
                        compute(getOp());
                        setOp('X');
                        setPrev(value);
                        setNumberStatus(1);
                        break;
                    case R.id.btn_div :
                        compute(getOp());
                        setOp('/');
                        setPrev(value);
                        setNumberStatus(1);
                        break;
                    case R.id.btn_equal :
                        compute(getOp());
                        setPrev("0");
                        setNumberStatus(1);
                        break;
                    case R.id.btn_del :
                        et.setText("");
                        et.setText("0");
                        setNumberStatus(1);
                        break;
                    case R.id.btn_base3 :
                        convertBase10ToBase3();
                        setPrev(value);
                        setNumberStatus(1);
                        break;
                    case R.id.btn_dot :
                        et.setText(value + ".");
                        break;
                    case R.id.btn_backspace :
                        if(value.length() == 1){
                            et.setText("0");
                        }else {
                            et.setText(value.substring(0, value.length() - 1));
                        }
                        break;
                    case R.id.btn_sign :
                            et.setText("-"+value);
                        break;
                }
            }

        };
        // Putting a Listener
        btn_0.setOnClickListener(ocl);
        btn_1.setOnClickListener(ocl);
        btn_2.setOnClickListener(ocl);
        btn_3.setOnClickListener(ocl);
        btn_4.setOnClickListener(ocl);
        btn_5.setOnClickListener(ocl);
        btn_6.setOnClickListener(ocl);
        btn_7.setOnClickListener(ocl);
        btn_8.setOnClickListener(ocl);
        btn_9.setOnClickListener(ocl);
        opr_plus.setOnClickListener(ocl);
        opr_minus.setOnClickListener(ocl);
        opr_times.setOnClickListener(ocl);
        opr_divide.setOnClickListener(ocl);
        equals.setOnClickListener(ocl);
        del.setOnClickListener(ocl);
        base3.setOnClickListener(ocl);
        dot.setOnClickListener(ocl);
        backspace.setOnClickListener(ocl);
        signs.setOnClickListener(ocl);
    }
    /**
     * Display numbers
     * @param previousValue A variable of type String.
     * @param value A variable of type String.
     */
    public void display(String previousValue,String value){
        if (getNumberStatus() == 0 && !(previousValue.matches("0"))) {
            et.setText(previousValue + value);
        } else {
            et.setText(value);
            setNumberStatus(0);
        }

    }
    /**
     * Converts String to Bigdecimal
     * @param num A variable of type String.
     */
    public void convertStringToBigDecimal(String num){
        BigDecimal bd = new BigDecimal(num);
    }
    /**
     * Sets operator to be used.
     * @param operator A variable of type char.
     */
    public void setOp(char operator){
        this.operator=operator;
    }
    /**
     * Returns operator being used.
     */
    public char getOp(){
        return operator;
    }
    /**
     * Sets the previous value being input.
     * @param str A variable of type String.
     */
    public void setPrev(String str){
        this.str= str;
    }
    /**
     * Returns previous value being input.
     */
    public String getPrev(){
        return str;
    }
    /**
     * Sets number status used for appending numbers being input.
     * @param num A variable of type int.
     */
    public void setNumberStatus(int num){
        this.num = num;
    }
    /**
     * Returns number status being input.
     */
    public int getNumberStatus(){
        return num;
    }
    /**
     * Computation
     * @param operator A variable of type char.
     */
    public void compute(char operator){

        switch(operator){
            case '+' :
                et.setText(""+add(Double.parseDouble(getPrev()),Double.parseDouble(et.getText().toString())));
                setOp('\0');
                break;
            case '-' :
                et.setText(""+subtract(Double.parseDouble(getPrev()),Double.parseDouble(et.getText().toString())));
                setOp('\0');
                break;
            case 'X' :
                et.setText(""+multiply(Double.parseDouble(getPrev()),Double.parseDouble(et.getText().toString())));
                setOp('\0');
                break;
            case '/' :
                et.setText(""+divide(Double.parseDouble(getPrev()),Double.parseDouble(et.getText().toString())));
                setOp('\0');
                break;
            default:
                setOp('\0');
        }
    }
    /**
     * Performs addition
     * @param addend A variable of type double.
     * @param aguend A variable of type double
     */
    public double add(double addend,double aguend){
        return addend+aguend;
    }
    /**
     * Performs subtraction
     * @param minuend A variable of type double.
     * @param subtrahend A variable of type double
     */
    public double subtract(double minuend,double subtrahend){
        return minuend-subtrahend;
    }
    /**
     * Performs multiplication
     * @param multiplicand A variable of type double.
     * @param multiplier A variable of type double
     */
    public double multiply(double multiplicand,double multiplier){
        return multiplicand*multiplier;
    }
    /**
     * Performs division
     * @param dividend A variable of type double.
     * @param divisor A variable of type double
     */
    public double divide(double dividend,double divisor){
        return dividend/divisor;
    }
    /**
     * Converts base10 to base3
     */
    public void convertBase10ToBase3() {
        long ret = 0, factor = 1;
        long num = Long.parseLong(et.getText().toString());

        while (num > 0) {
            ret += num % 3 * factor;
            num /= 3;
            factor *= 10;
        }
        et.setText(""+ret);
    }
}
