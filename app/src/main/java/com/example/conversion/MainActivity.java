package com.example.conversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Spinner theSpinner;
    private EditText amountEditText;
    private TextView tspTextView, cupTextView, tbsTextView, ozTextView, kgTextView, quartTextView,
            gallonTextView, poundTextView, mlTextView, literTextView, mgTextView, pintTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItemsToUnitTypeSpinner();
        addListenerToUnitTypeSpinner();
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        initializeTextViews();

    }

    public void initializeTextViews() {
        tspTextView = (TextView) findViewById(R.id.tspTextView);
        cupTextView = (TextView) findViewById(R.id.cupTextView);
        tbsTextView = (TextView) findViewById(R.id.tbsTextView);
        ozTextView = (TextView) findViewById(R.id.ozTextView);
        kgTextView = (TextView) findViewById(R.id.kgTextView);
        quartTextView = (TextView) findViewById(R.id.quartTextView);
        gallonTextView = (TextView) findViewById(R.id.gallonTextView);
        poundTextView = (TextView) findViewById(R.id.poundTextView);
        mlTextView = (TextView) findViewById(R.id.mlTextView);
        literTextView = (TextView) findViewById(R.id.literTextView);
        mgTextView = (TextView) findViewById(R.id.mgTextView);
        pintTextView = (TextView) findViewById(R.id.pintTextView);

    }

    public void addItemsToUnitTypeSpinner () {
        theSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter =
                ArrayAdapter.createFromResource(this, R.array.conversion_types, android.R.layout.simple_spinner_item);
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        theSpinner.setAdapter(unitTypeSpinnerAdapter);
    }

    public void addListenerToUnitTypeSpinner() {
        theSpinner = (Spinner) findViewById(R.id.spinner);
        theSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();
                checkIfConvertingFromTsp(itemSelected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO add something
            }

        });
    }

    public void checkIfConvertingFromTsp(String currentUnit) {
        if(currentUnit.equals("teaspoon")){
            updateUnitTypeUsingTsp(Quantity.Unit.tsp);
        } else {
            if(currentUnit.equals("tablespoon")){
                updateUnitTypeUsingOther(Quantity.Unit.tbs);
            } else if(currentUnit.equals("cup")){
                updateUnitTypeUsingOther(Quantity.Unit.cup);
            }else if(currentUnit.equals("ounce")){
                updateUnitTypeUsingOther(Quantity.Unit.oz);
            }else if(currentUnit.equals("kilogram")){
                updateUnitTypeUsingOther(Quantity.Unit.kg);
            }else if(currentUnit.equals("quart")){
                updateUnitTypeUsingOther(Quantity.Unit.quart);
            }else if(currentUnit.equals("gallon")){
                updateUnitTypeUsingOther(Quantity.Unit.gallon);
            }else if(currentUnit.equals("pound")){
                updateUnitTypeUsingOther(Quantity.Unit.pound);
            }else if(currentUnit.equals("milliliter")){
                updateUnitTypeUsingOther(Quantity.Unit.ml);
            }else if(currentUnit.equals("liter")){
                updateUnitTypeUsingOther(Quantity.Unit.liter);
            }else if(currentUnit.equals("milligram")){
                updateUnitTypeUsingOther(Quantity.Unit.mg);
            }else updateUnitTypeUsingOther(Quantity.Unit.pint);
        }
    }

    public void updateUnitTypeUsingTsp(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amountEditText.getText().toString());
        String teaspoonValueAndUnit = doubleToConvert + "tsp";
        tspTextView.setText(teaspoonValueAndUnit);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.tbs, tbsTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.oz, ozTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.kg, kgTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.ml, mlTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.mg, mgTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, Quantity.Unit.pint, pintTextView);

    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit unitConvertingTo, TextView theTextView) {
        Quantity unitQuantity = new Quantity(doubleToConvert, Quantity.Unit.tsp);
        String tempUnit = unitQuantity.to(unitConvertingTo).toString();
        theTextView.setText(tempUnit);
    }

    public void updateUnitTypeUsingOther(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amountEditText.getText().toString());
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);
        String valueInTeaspoons = currentQuantitySelected.to(Quantity.Unit.tsp).toString();
        tspTextView.setText(valueInTeaspoons);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tbsTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.oz, ozTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.kg, kgTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.ml, mlTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.mg, mgTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pint, pintTextView);

        if(currentUnit.name().equals(currentQuantitySelected.unit.name())) {
            String currentUnitTextViewText = doubleToConvert + " " + currentQuantitySelected.unit.name();
            String currentTextViewName = currentQuantitySelected.unit.name() + "TextView";
            int currentId = getResources().getIdentifier(currentTextViewName, "id", MainActivity.this.getPackageName());
            TextView currentTextView = (TextView) findViewById(currentId);
            currentTextView.setText(currentUnitTextViewText);
        }


    }

    public void updateUnitTextFieldUsingTsp(double doubleToConvert, Quantity.Unit currentUnit, Quantity.Unit preferredUnit, TextView targetTextView ) {
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);
        String tempTextViewText = currentQuantitySelected.to(Quantity.Unit.tsp).to(preferredUnit).toString();
        targetTextView.setText(tempTextViewText);
    }
}
