package com.dornier.fuelcarcare;

import android.app.Dialog;
import android.support.annotation.FloatRange;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityMenuVehicle extends AppCompatActivity {

    ListView listView;
    ModelVehicle selectedVehicle;
    ArrayAdapter<String> adapter;
    String[] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_vehicle);
        ModelDataManager.getInstance().setActualContext(ActivityMenuVehicle.this);
        options = new String[] {
                "Adicionar abastecimento",
                "Adicionar alerta de manutenção",
                "Adicionar despesa",
                "Histórico consumo",
                "Consultar despesas",
                "Editar veiculo",
        };

        listView = (ListView) findViewById(R.id.MenuCarListView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        addFillUpActions();
                        break;
                    case 1:
                        addMaintenanceActions();
                        break;
                    case 2:
                        addExpenseActions();
                        break;
                    case 3:
                        showFillUpHistoryActions();
                        break;
                    case 4:
                        showExpensesActions();
                        break;
                    case 5:
                        editVehicleActions();
                }
            }
        });
    }

    public void addFillUpActions(){
        final Dialog dialog = new Dialog(ActivityMenuVehicle.this);
        dialog.setContentView(R.layout.dialog_add_fillup);
        dialog.setTitle("Adicionar abastecimento");

        // set the custom dialog components - text, image and button
        final EditText fuelPrice          = (EditText) dialog.findViewById(R.id.DialogFillUpFuelPrice);
        final EditText fillUpTotalPrice   = (EditText) dialog.findViewById(R.id.DialogFillUpTotalPrice);
        final EditText fillUpLiters       = (EditText) dialog.findViewById(R.id.DialogFillUpLiters);
        EditText location           = (EditText) dialog.findViewById(R.id.DialogFillUpLocation);
        EditText date               = (EditText) dialog.findViewById(R.id.DialogFillUpDate);
        Spinner  fuel               = (Spinner)  dialog.findViewById(R.id.DialogFillUpFuel);
        Button   confirmButton      = (Button)   dialog.findViewById(R.id.DialogFillUpButtonOK);

        date.setText(ModelDataManager.getActualDate());

        fuelPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(
                        (fuelPrice.length() > 0)
                        &&
                        (fillUpTotalPrice.length() > 0)
                 )
                fillUpLiters.setText(String.valueOf(
                        Float.parseFloat(fillUpTotalPrice.getText().toString())
                        /
                        Float.parseFloat(fuelPrice.getText().toString())
                        )
                );
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void addMaintenanceActions(){
        final Dialog dialog = new Dialog(ActivityMenuVehicle.this);
        dialog.setContentView(R.layout.dialog_add_maintenance);
        dialog.setTitle("Adicionar alerta de manutenção");

        // set the custom dialog components - text, image and button
        final EditText kilometers   = (EditText) dialog.findViewById(R.id.DialogMaintenanceKilometers);
        final EditText date         = (EditText) dialog.findViewById(R.id.DialogMaintenanceDate);
        final EditText item         = (EditText) dialog.findViewById(R.id.DialogMaintenanceItem);
        Button   confirmButton      = (Button)   dialog.findViewById(R.id.DialogMaintenanceButtonOK);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void addExpenseActions(){
        final Dialog dialog = new Dialog(ActivityMenuVehicle.this);
        dialog.setContentView(R.layout.dialog_add_expense);
        dialog.setTitle("Adicionar despesa");

        // set the custom dialog components - text, image and button
        final EditText item         = (EditText) dialog.findViewById(R.id.DialogExpenseComponentName);
        final EditText date         = (EditText) dialog.findViewById(R.id.DialogExpenseDate);
        final EditText price        = (EditText) dialog.findViewById(R.id.DialogExpensePrice);
        final EditText quantity     = (EditText) dialog.findViewById(R.id.DialogExpenseQuantity);
        Button   confirmButton      = (Button)   dialog.findViewById(R.id.DialogExpenseButtonOK);

        date.setText(ModelDataManager.getActualDate());

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void showFillUpHistoryActions(){

    }
    public void showExpensesActions(){

    }
    public void editVehicleActions(){
        final Dialog dialog = new Dialog(ActivityMenuVehicle.this);
        dialog.setContentView(R.layout.dialog_edit_vehicle);
        dialog.setTitle("Editar veiculo");

        // set the custom dialog components - text, image and button
        final EditText manufacturer = (EditText) dialog.findViewById(R.id.DialogEditVehicleManufacturer);
        final EditText model        = (EditText) dialog.findViewById(R.id.DialogEditVehicleModel);
        final EditText name         = (EditText) dialog.findViewById(R.id.DialogEditVehicleName);
        final EditText year         = (EditText) dialog.findViewById(R.id.DialogEditVehicleYear);
        Button confirmButton        = (Button)   dialog.findViewById(R.id.DialogEditVehicleButtonOK);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
