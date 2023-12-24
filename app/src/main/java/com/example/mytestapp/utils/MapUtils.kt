package com.example.mytestapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.mytestapp.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.Locale

@Suppress("DEPRECATION")
class MapUtils {
    private var mMapZoomLevel = 15f
    var mLocationManager: LocationManager? = null

    fun mapMarkerCameraMove(
        context: Context,
        mMap: GoogleMap,
        mLatitude: Double,
        mLongitude: Double,
        view: View,
        zoom: Float
    ) {
        val location = LatLng(mLatitude, mLongitude)

        val marker = mMap.addMarker(
            MarkerOptions()
                .position(location)
                .title("Marker")
                .draggable(true)
                .visible(false)
        )

        val center = CameraUpdateFactory.newLatLng(location)
        val zoom = CameraUpdateFactory.newLatLngZoom(location, zoom)

        mMap.moveCamera(center)
        mMap.animateCamera(zoom)

        val animSlide = AnimationUtils.loadAnimation(context, R.anim.marker_aim)

        mMap.setOnCameraMoveListener {
            val midLatLng = mMap.cameraPosition.target
            if (marker != null) {
                marker.position = midLatLng
                view.startAnimation(animSlide)
            }

        }
    }



    fun mapMarker(
        context: Context,
        mMap: GoogleMap,
        mLatitude: Double,
        mLongitude: Double
    ) {
        val location = LatLng(mLatitude, mLongitude)
        mMap.clear()
        val marker = MarkerOptions()
            .position(location)
            .icon(getMarkerBitmapFromView(context)?.let { BitmapDescriptorFactory.fromBitmap(it) })
            .title("Marker")
            .draggable(true)
        mMap.addMarker(marker)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 17f))
    }


    private fun getMarkerBitmapFromView(context: Context): Bitmap? {
        val customMarkerView: View =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.custom_marker,
                null
            )

        val markerImage: ImageView = customMarkerView.findViewById(R.id.marker_image) as ImageView

        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        customMarkerView.layout(
            0,
            0,
            customMarkerView.measuredWidth,
            customMarkerView.measuredHeight
        )
        val returnedBitmap = Bitmap.createBitmap(
            customMarkerView.measuredWidth, customMarkerView.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(returnedBitmap)
        val drawable = customMarkerView.background
        drawable?.draw(canvas)
        customMarkerView.draw(canvas)
        return returnedBitmap
    }
}