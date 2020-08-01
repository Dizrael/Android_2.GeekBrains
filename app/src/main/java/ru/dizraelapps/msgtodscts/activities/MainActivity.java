package ru.dizraelapps.msgtodscts.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.dizraelapps.msgtodscts.ListAdapter;
import ru.dizraelapps.msgtodscts.R;
import ru.dizraelapps.msgtodscts.activities.AboutUsActivity;
import ru.dizraelapps.msgtodscts.activities.ContactUsActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SensorEventListener {

    private ListAdapter adapter;
    private SensorManager mSensorManager;
    private SensorEventListener mSensorListener;
    private Sensor mTemperature;
    private Sensor mHumidity;
    private final static String NOT_SUPPORTED_MSG = "Sorry, sensor not available for this device";
    private TextView ambientTempLabel;
    private TextView ambientHumidLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = initToolbar();
        initDrawer(toolbar);
        initList();
        initSensors();
    }

    private void initSensors() {
        ambientTempLabel = (TextView) findViewById(R.id.main_tv_ambient_temp_sensor);
        ambientHumidLabel = (TextView) findViewById(R.id.main_tv_humidity_sensor);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensorListener = new SensorEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onSensorChanged(SensorEvent event) {
                Sensor sensor = event.sensor;
                if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                    float ambient_temp = event.values[0];
                    ambientTempLabel.setText("Ambient temperature: " + ambient_temp + "°C");
                } else if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
                    float humidity = event.values[0];
                    ambientHumidLabel.setText("Humidity: " + humidity + "%");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            mTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            mHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        } else {
            ambientHumidLabel.setText(NOT_SUPPORTED_MSG);
            ambientTempLabel.setText(NOT_SUPPORTED_MSG);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(mSensorListener, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorListener);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    private void initList() {
        RecyclerView recyclerView = findViewById(R.id.recycler_list);

        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Установим адаптер
        adapter = new ListAdapter(initData(), this);
        recyclerView.setAdapter(adapter);
    }

    private List<String> initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(String.format("Element %d", i));
        }
        return list;
    }

    private void initDrawer(Toolbar toolbar) {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    private Toolbar initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        return toolbar;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            item.setChecked(true);


        } else if (id == R.id.nav_slideshow) {

            item.setChecked(true);

        } else if (id == R.id.nav_about) {

            Intent intent2AboutActivity = new Intent(getBaseContext(), AboutUsActivity.class);
            startActivity(intent2AboutActivity);
            item.setChecked(true);

        } else if (id == R.id.nav_contact) {

            Intent intent2ContactActivity = new Intent(getBaseContext(), ContactUsActivity.class);
            startActivity(intent2ContactActivity);
            item.setChecked(true);
        }
        item.setChecked(false);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

}