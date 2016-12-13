package com.example.igx.problem1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener /* implements Something1, Something2 */ {

    LocationManager lm;
    SensorManager sm;
    Sensor s1,s2,s3,s4;
    float x1,x2,x3,x4,y1,y2,y3,y4,z1,z2,z3,z4;
    TextView text_selectedData;
    Double lati=32.7,longi=36.5;
    String lat_s,lon_s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_getLocation = (Button) findViewById(R.id.btn_getLocation);
        Button btn_getSensors = (Button) findViewById(R.id.btn_getSensors);
        Button btn_sendMessage = (Button) findViewById(R.id.btn_sendMessage);

        text_selectedData = (TextView) findViewById(R.id.text_selectedData);
        final TextView text_selectedType = (TextView) findViewById(R.id.text_selectedType);
        final EditText edit_phoneNumber = (EditText) findViewById(R.id.edit_phoneNumber);



        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lat_s = Double.toString(lati);
                lon_s = Double.toString(longi);


                text_selectedType.setText("LOCATION");
                text_selectedData.setText("위도 : " + lat_s + " 경도 : " + lon_s);

            }
        });

        btn_getSensors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                text_selectedType.setText("SENSORS");
                text_selectedData.setText("<GYRO> x: " + x1 +"y: " + y1 + "z: " + z1 + "<Linear Acc> x: "
                        + x2 +"y: " + y2 + "z: " + z2 + "<Acc> x: "  + x3 +"y: " + y3 + "z: " + z3 + "<Light> x: "  + x4 +"y: " + y4 + "z: " + z4);

            }
        });

        btn_sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone_n = edit_phoneNumber.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(phone_n));
                startActivity(intent);


            }
        });

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        GPSListener gs = new GPSListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(
                lm.NETWORK_PROVIDER, 1000, 0, gs
        );

        lm.requestLocationUpdates(
                lm.GPS_PROVIDER,1000,0,gs
        );


        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        s1 = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        s2 = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        s3 = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        s4 = sm.getDefaultSensor(Sensor.TYPE_LIGHT);



    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
            x1 = event.values[0];
            y1 = event.values[1];
            z1 = event.values[2];

            text_selectedData.setText("<GYRO> x: " + x1 +"y: " + y1 + "z: " + z1 + "<Linear Acc> x: "
                    + x2 +"y: " + y2 + "z: " + z2 + "<Acc> x: "  + x3 +"y: " + y3 + "z: " + z3 + "<Light> x: "  + x4 +"y: " + y4 + "z: " + z4);

        }

        if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION)
        {
            x2 = event.values[0];
            y2 = event.values[1];
            z2 = event.values[2];

            text_selectedData.setText("<GYRO> x: " + x1 +"y: " + y1 + "z: " + z1 + "<Linear Acc> x: "
                    + x2 +"y: " + y2 + "z: " + z2 + "<Acc> x: "  + x3 +"y: " + y3 + "z: " + z3 + "<Light> x: "  + x4 +"y: " + y4 + "z: " + z4);
        }

        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            x3 = event.values[0];
            y3 = event.values[1];
            z3 = event.values[2];

            text_selectedData.setText("<GYRO> x: " + x1 +"y: " + y1 + "z: " + z1 + "<Linear Acc> x: "
                    + x2 +"y: " + y2 + "z: " + z2 + "<Acc> x: "  + x3 +"y: " + y3 + "z: " + z3 + "<Light> x: "  + x4 +"y: " + y4 + "z: " + z4);
        }

        if(event.sensor.getType() == Sensor.TYPE_LIGHT)
        {
            x4 = event.values[0];
            y4 = event.values[1];
            z4 = event.values[2];

            text_selectedData.setText("<GYRO> x: " + x1 +"y: " + y1 + "z: " + z1 + "<Linear Acc> x: "
                    + x2 +"y: " + y2 + "z: " + z2 + "<Acc> x: "  + x3 +"y: " + y3 + "z: " + z3 + "<Light> x: "  + x4 +"y: " + y4 + "z: " + z4);
        }




    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        //sm.registerListener(,Sensor.TYPE_ACCELEROMETER);
    }

    public class GPSListener implements LocationListener
    {

        @Override
        public void onLocationChanged(Location location) {

            Double latitude;
            Double longitude;

            latitude = location.getLatitude();
            longitude = location.getLongitude();



            lati = latitude;
            longi = longitude;

            lat_s = Double.toString(lati);
            lon_s = Double.toString(longi);


            text_selectedData.setText("위도 : " + lat_s + " 경도 : " + lon_s);


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }


    }








}
