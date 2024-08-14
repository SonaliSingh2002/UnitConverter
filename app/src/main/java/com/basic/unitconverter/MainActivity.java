package com.basic.unitconverter;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {
    private AppCompatEditText inputValue;
    private AppCompatSpinner fromUnit;
    private AppCompatSpinner toUnit;
    private AppCompatTextView resultTextView;
    private AppCompatButton convertButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        removeStatusBarWithBlackIcon();
        inputValue = findViewById(R.id.inputValue);
        fromUnit = findViewById(R.id.fromUnit);
        toUnit = findViewById(R.id.toUnit);
        resultTextView = findViewById(R.id.resultTextView);
        convertButton = findViewById(R.id.convertButton);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String input = inputValue.getText().toString();
        if (input.isEmpty()) {
            resultTextView.setText(R.string.please_enter_a_value);
            return;
        }

        double value = Double.parseDouble(input);
        String from = fromUnit.getSelectedItem().toString();
        String to = toUnit.getSelectedItem().toString();

        double result = 0;

        // Length Conversions
        if (from.equals("Centimeters") && to.equals("Meters")) {
            result = value / 100;
        } else if (from.equals("Meters") && to.equals("Centimeters")) {
            result = value * 100;
        } else if (from.equals("Inches") && to.equals("Centimeters")) {
            result = value * 2.54;
        } else if (from.equals("Centimeters") && to.equals("Inches")) {
            result = value / 2.54;
        } else if (from.equals("Feet") && to.equals("Meters")) {
            result = value * 0.3048;
        } else if (from.equals("Meters") && to.equals("Feet")) {
            result = value / 0.3048;
        }

        // Weight Conversions
        else if (from.equals("Grams") && to.equals("Kilograms")) {
            result = value / 1000;
        } else if (from.equals("Kilograms") && to.equals("Grams")) {
            result = value * 1000;
        } else if (from.equals("Pounds") && to.equals("Kilograms")) {
            result = value * 0.453592;
        } else if (from.equals("Kilograms") && to.equals("Pounds")) {
            result = value / 0.453592;
        }

        // Temperature Conversions
        else if (from.equals("Celsius") && to.equals("Fahrenheit")) {
            result = (value * 9/5) + 32;
        } else if (from.equals("Fahrenheit") && to.equals("Celsius")) {
            result = (value - 32) * 5/9;
        } else if (from.equals("Celsius") && to.equals("Kelvin")) {
            result = value + 273.15;
        } else if (from.equals("Kelvin") && to.equals("Celsius")) {
            result = value - 273.15;
        }

        // If no conversion was matched
        else {
            resultTextView.setText(R.string.conversion_not_available);
            return;
        }

        resultTextView.setText(String.format("%s%s", getString(R.string.result), result));
    }
}