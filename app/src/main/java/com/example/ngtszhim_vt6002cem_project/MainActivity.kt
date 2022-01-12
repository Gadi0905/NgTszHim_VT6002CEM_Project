package com.example.ngtszhim_vt6002cem_project

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.location.LocationRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private var permissionId = 1000
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        // variable definition
        val btnMainMyProfile = findViewById<Button>(R.id.btnMainMyProfile)
        val btnMap = findViewById<Button>(R.id.btnMap)

        // when btnMainMyProfile is pressed, jump to MyProfileActivity screen
        btnMainMyProfile.setOnClickListener {
            startActivity(Intent(this, MyProfileActivity::class.java))
        }

        // when btnMap is pressed, jump to MapsActivity screen
        btnMap.setOnClickListener {
            checkLocationPermission()
        }
    }

    private fun checkLocationPermission() {
        // variable definition
        val tvLocation = findViewById<TextView>(R.id.tvLocation)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            if(isLocationEnable()) {
                fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
                    val location = task.result
                    if (location == null) {
                        Toast.makeText(
                            this,
                            "no location",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        tvLocation.text = "${location.latitude} + ${location.longitude}"
                        val intent = Intent(this, MapsActivity :: class.java)
                        intent.putExtra("lat", location.latitude)
                        intent.putExtra("long", location.longitude)
                        startActivity(intent)
                    }
                }
            } else {
                Toast.makeText(
                    this,
                    "Please enable your location service",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                permissionId
            )
        }
    }

    private fun isLocationEnable(): Boolean {
        var locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permissionId) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug:", "You have the permission")
            }
        }
    }
}