package com.example.accidenttrack;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PostIncidentActivity extends AppCompatActivity {

    private Spinner spinnerIncidentType;
    private CheckBox checkBoxVictims;
    private TextView tvLocation, tvDateTime;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_incident);

        spinnerIncidentType = findViewById(R.id.spinnerIncidentType);
        checkBoxVictims = findViewById(R.id.checkBoxVictims);
        tvLocation = findViewById(R.id.tvLocation);
        tvDateTime = findViewById(R.id.tvDateTime);
        Button btnUploadPhoto = findViewById(R.id.btnUploadPhoto);
        Button btnSubmitIncident = findViewById(R.id.btnSubmitIncident);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        getLastLocation();

        String currentDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date());
        tvDateTime.setText("Data e Hora: " + currentDateTime);

        btnUploadPhoto.setOnClickListener(v -> {
            // Falta lógica de como vamos inserir a foto
        });

        btnSubmitIncident.setOnClickListener(v -> {
            // Falta lógica de onde vamos inserir o acidente
        });
    }

    private void getLastLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            fusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful() && task.getResult() != null) {
                        Location location = task.getResult();
                        String message = "Localização: Latitude " + location.getLatitude() + ", Longitude " + location.getLongitude();
                        tvLocation.setText(message);
                    } else {
                        tvLocation.setText("Localização: Não disponível");
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLastLocation();
        } else {
            tvLocation.setText("Localização: Permissão negada");
        }
    }
}
