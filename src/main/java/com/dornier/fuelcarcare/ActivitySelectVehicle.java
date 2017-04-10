package com.dornier.fuelcarcare;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivitySelectVehicle extends AppCompatActivity {

    Button addCar;
    ListView listView;
    ArrayAdapter<ModelVehicle> adapter;
    ArrayList<ModelVehicle> vehicles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_vehicle);
        ModelDataManager.getInstance().setActualContext(ActivitySelectVehicle.this);

        addCar = (Button) findViewById(R.id.SelectCarAddNew);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCarActions();
            }
        });
        listView = (ListView) findViewById(R.id.SelectCarListView);

        vehicles.add(new ModelVehicle("Polo"));
        vehicles.add(new ModelVehicle("Fox"));
        vehicles.add(new ModelVehicle("Sahara"));
        vehicles.add(new ModelVehicle("XRE"));

        adapter = new ArrayAdapter<ModelVehicle>(this, android.R.layout.simple_list_item_1, vehicles);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectCarActions();
            }
        });

    }

    public void addCarActions(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(ActivitySelectVehicle.this);
        edittext.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        alert.setTitle("Nome do veículo");

        alert.setView(edittext);

        alert.setPositiveButton(" OK ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Editable editable = edittext.getText();
                String carName = edittext.getText().toString();
                vehicles.add(new ModelVehicle(carName));
            }
        });

        alert.show();
    }

    public void selectCarActions(){
        Intent i = new Intent(this, ActivityMenuVehicle.class);
        startActivity(i);
    }
}
