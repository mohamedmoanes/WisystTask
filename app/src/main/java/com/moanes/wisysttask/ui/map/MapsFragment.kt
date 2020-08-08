package com.moanes.wisysttask.ui.map

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.moanes.wisysttask.R
import com.moanes.wisysttask.data.model.providers.DataItem
import com.moanes.wisysttask.utils.ProgressDialog
import com.moanes.wisysttask.utils.extension.setImageURL
import de.hdodenhof.circleimageview.CircleImageView
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapsFragment : Fragment() {
    private val viewModel: MapViewModel by viewModel()
    private var progressDialog: ProgressDialog? = null

    private val markerDataHashMap: HashMap<Marker, DataItem> = HashMap<Marker, DataItem>()
    private lateinit var googleMap: GoogleMap
    private val callback = OnMapReadyCallback { it ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        googleMap = it
googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(24.774265, 46.738586), 4f))
        googleMap.setOnMarkerClickListener { marker ->
            val data: DataItem = markerDataHashMap[marker]!!
            ProvidersDetails(data).show(childFragmentManager, "datails")

            false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            progressDialog = ProgressDialog(it)
        }
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        handelError()
        handleProgress()
        providersHandler()

//        viewModel.getAllProviders()
        viewModel.getProviders(viewModel.mCurrentPage)
    }


    private fun providersHandler() {
        viewModel.providersLiveData.observe(viewLifecycleOwner, Observer {
            it?.let { list ->

                for (provider: DataItem in list) {
                    val location =
                        LatLng(provider.latitude.toDouble(), provider.longitude.toDouble())
                    val marker: Marker = googleMap.addMarker(MarkerOptions().position(location).icon(
                        BitmapDescriptorFactory.fromBitmap(
                        createCustomMarker(requireContext(),provider.logo))))
                    markerDataHashMap[marker] = provider
                }

            }
        })
    }
    private fun handleProgress(){
        viewModel.showLoading.observe(viewLifecycleOwner, Observer {
            if(it)
                progressDialog?.show()
            else
                progressDialog?.hide()
        })
    }
    private fun handelError(){
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            view?.let { it1 -> Snackbar.make(it1,it, Snackbar.LENGTH_SHORT).show() }
        })
    }


    fun createCustomMarker(
        context: Context,
         logo_url:String
    ): Bitmap? {
        val marker: View =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.custom_marker_layout,
                null
            )
        val markerImage =
            marker.findViewById<CircleImageView>(R.id.logo)
        markerImage.setImageURL(logo_url)
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        marker.layoutParams = ViewGroup.LayoutParams(52, ViewGroup.LayoutParams.WRAP_CONTENT)
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        marker.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(
            marker.measuredWidth,
            marker.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        marker.draw(canvas)
        return bitmap
    }
}