package com.example.unitconverterapp_224385035;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // To create and store UI parts
    private Spinner unitFrom, unitTo;
    private EditText inputNumber;
    private TextView outputText;
    private Button convertBtn;

    // For all unit choices
    private final String[] unitList = {"inch", "foot", "yard", "mile", "cm", "m",
            "pound", "ounce", "ton", "kg", "g",
            "Celsius", "Fahrenheit", "Kelvin"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // To connect UI parts
        unitFrom = findViewById(R.id.spinner);
        unitTo = findViewById(R.id.spinner2);
        inputNumber = findViewById(R.id.editTextText);
        outputText = findViewById(R.id.textView2);
        convertBtn = findViewById(R.id.button);

        // To set up dropdown menus
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, unitList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitFrom.setAdapter(adapter);
        unitTo.setAdapter(adapter);

        // For button click action
        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnit();
            }
        });
    }

    private void convertUnit() {
        // To get user choices
        String fromUnit = unitFrom.getSelectedItem().toString();
        String toUnit = unitTo.getSelectedItem().toString();
        String inputText = inputNumber.getText().toString();

        // To check if empty
        if (inputText.isEmpty()) {
            outputText.setText("Please enter a value");
            return;
        }

        // To check if same unit type
        if (fromUnit == toUnit) {
            outputText.setText("Cannot convert " + fromUnit + " to " + toUnit);
            return;
        }

        // To change text to number
        double inputValue = Double.parseDouble(inputText);
        double result = 0;

        // To check temperature limits
        if (fromUnit.equals("Kelvin") && inputValue < 0) {
            outputText.setText("Kelvin cannot be negative");
            return;
        }

        // For length conversions
        if (fromUnit.equals("inch")) {
            if (toUnit.equals("foot")) result = inputValue / 12;
            else if (toUnit.equals("yard")) result = inputValue / 36;
            else if (toUnit.equals("mile")) result = inputValue / 63360;
            else if (toUnit.equals("cm")) result = inputValue * 2.54;
            else if (toUnit.equals("m")) result = inputValue * 0.0254;
        }
        else if (fromUnit.equals("foot")) {
            if (toUnit.equals("inch")) result = inputValue * 12;
            else if (toUnit.equals("yard")) result = inputValue / 3;
            else if (toUnit.equals("mile")) result = inputValue / 5280;
            else if (toUnit.equals("cm")) result = inputValue * 30.48;
            else if (toUnit.equals("m")) result = inputValue * 0.3048;
        }
        else if (fromUnit.equals("yard")) {
            if (toUnit.equals("inch")) result = inputValue * 36;
            else if (toUnit.equals("foot")) result = inputValue * 3;
            else if (toUnit.equals("mile")) result = inputValue / 1760;
            else if (toUnit.equals("cm")) result = inputValue * 91.44;
            else if (toUnit.equals("m")) result = inputValue * 0.9144;
        }
        else if (fromUnit.equals("mile")) {
            if (toUnit.equals("inch")) result = inputValue * 63360;
            else if (toUnit.equals("foot")) result = inputValue * 5280;
            else if (toUnit.equals("yard")) result = inputValue * 1760;
            else if (toUnit.equals("cm")) result = inputValue * 160934;
            else if (toUnit.equals("m")) result = inputValue * 1609.34;
        }
        else if (fromUnit.equals("cm")) {
            if (toUnit.equals("inch")) result = inputValue / 2.54;
            else if (toUnit.equals("foot")) result = inputValue / 30.48;
            else if (toUnit.equals("yard")) result = inputValue / 91.44;
            else if (toUnit.equals("mile")) result = inputValue / 160934;
            else if (toUnit.equals("m")) result = inputValue / 100;
        }
        else if (fromUnit.equals("m")) {
            if (toUnit.equals("inch")) result = inputValue * 39.3701;
            else if (toUnit.equals("foot")) result = inputValue * 3.28084;
            else if (toUnit.equals("yard")) result = inputValue * 1.09361;
            else if (toUnit.equals("mile")) result = inputValue / 1609.34;
            else if (toUnit.equals("cm")) result = inputValue * 100;
        }
        // For weight conversions
        else if (fromUnit.equals("pound")) {
            if (toUnit.equals("ounce")) result = inputValue * 16;
            else if (toUnit.equals("ton")) result = inputValue / 2000;
            else if (toUnit.equals("kg")) result = inputValue * 0.453592;
            else if (toUnit.equals("g")) result = inputValue * 453.592;
        }
        else if (fromUnit.equals("ounce")) {
            if (toUnit.equals("pound")) result = inputValue / 16;
            else if (toUnit.equals("ton")) result = inputValue / 32000;
            else if (toUnit.equals("kg")) result = inputValue * 0.0283495;
            else if (toUnit.equals("g")) result = inputValue * 28.3495;
        }
        else if (fromUnit.equals("ton")) {
            if (toUnit.equals("pound")) result = inputValue * 2000;
            else if (toUnit.equals("ounce")) result = inputValue * 32000;
            else if (toUnit.equals("kg")) result = inputValue * 907.185;
            else if (toUnit.equals("g")) result = inputValue * 907185;
        }
        else if (fromUnit.equals("kg")) {
            if (toUnit.equals("pound")) result = inputValue / 0.453592;
            else if (toUnit.equals("ounce")) result = inputValue / 0.0283495;
            else if (toUnit.equals("ton")) result = inputValue / 907.185;
            else if (toUnit.equals("g")) result = inputValue * 1000;
        }
        else if (fromUnit.equals("g")) {
            if (toUnit.equals("pound")) result = inputValue / 453.592;
            else if (toUnit.equals("ounce")) result = inputValue / 28.3495;
            else if (toUnit.equals("ton")) result = inputValue / 907185;
            else if (toUnit.equals("kg")) result = inputValue / 1000;
        }
        // For temperature conversions
        else if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            result = (inputValue * 9/5) + 32;
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Kelvin")) {
            result = inputValue + 273.15;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            result = (inputValue - 32) * 5/9;
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Kelvin")) {
            result = (inputValue - 32) * 5/9 + 273.15;
        } else if (fromUnit.equals("Kelvin") && toUnit.equals("Celsius")) {
            result = inputValue - 273.15;
        } else if (fromUnit.equals("Kelvin") && toUnit.equals("Fahrenheit")) {
            result = (inputValue - 273.15) * 9/5 + 32;
        } else {
            outputText.setText("Conversion not supported");
            return;
        }

        // To display the answer
        String resultText = "Your converter result is " + inputValue + " " + fromUnit + " ——> " +
                "<font color='#FF0000'>" + result + "</font> " + toUnit;
        outputText.setText(android.text.Html.fromHtml(resultText));
    }
}