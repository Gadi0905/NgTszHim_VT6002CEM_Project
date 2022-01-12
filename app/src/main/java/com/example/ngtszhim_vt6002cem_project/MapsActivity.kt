package com.example.ngtszhim_vt6002cem_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.ngtszhim_vt6002cem_project.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var lat = 0.0
        var long = 0.0
        lat = intent.getDoubleExtra("lat")
        long = intent.getDoubleExtra("long")

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        val hongKong = LatLng(22.302711, 114.177216)
        val myLocation = LatLng(lat, long)
        mMap.addMarker(MarkerOptions().position(sydney).title("Maker in Sydney"))
        mMap.addMarker(MarkerOptions().position(hongKong).title("Maker in Hong Kong"))
        mMap.addMarker(MarkerOptions().position(myLocation).title("You are here"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
    }
}

private fun Intent.getDoubleExtra(s: String): Double {
    TODO("Not yet implemented")
}
