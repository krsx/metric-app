package com.capstone.metricapp.features.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.metricapp.R
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.domain.model.RTU
import com.capstone.metricapp.core.domain.model.Scadatel
import com.capstone.metricapp.core.ui.adapter.RTUKeypointsAdapter
import com.capstone.metricapp.core.ui.adapter.ScadatelKeypointsAdapter
import com.capstone.metricapp.core.utils.FabMenuState
import com.capstone.metricapp.core.utils.constans.Divisions
import com.capstone.metricapp.core.utils.showLongToast
import com.capstone.metricapp.core.utils.showToast
import com.capstone.metricapp.databinding.ActivityHomeBinding
import com.capstone.metricapp.features.add_keypoints.desc.AddKeypointsDescActivity
import com.capstone.metricapp.features.scan.ScanActivity
import com.capstone.metricapp.features.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var fabMenuState: FabMenuState = FabMenuState.COLLAPSED
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivSetting.setOnClickListener {
            val intentToSetting = Intent(this, SettingsActivity::class.java)
            startActivity(intentToSetting)
        }

        setupSearch()

        setRecyclerView()

        handleFab()

        setupRefresh()
    }

    private fun setupRefresh() {
        binding.refKeypoints.setOnRefreshListener {
            val query = binding.edSearch.text.toString().trim()

            if (query.isEmpty()) {
                setRecyclerView()
            } else {
                viewModel.getUserToken().observe(this) { token ->
                    viewModel.findScadatelKeypoints(token, query).observe(this) { scadatel ->
                        when (scadatel) {
                            is Resource.Error -> {
                                binding.refKeypoints.isRefreshing = false
                                showToast("Terdapat kesahalan, silahkan refresh kembali halaman ini: ${scadatel.message}")
                            }
                            is Resource.Loading -> {
                                binding.refKeypoints.isRefreshing = true
                            }
                            is Resource.Message -> {
                                binding.refKeypoints.isRefreshing = false
                                showLongToast("Keypoints yang dicari tidak ditemukan")
                                Log.e("HomeViewModel", scadatel.message.toString())
                            }
                            is Resource.Success -> {
                                binding.refKeypoints.isRefreshing = false
                                initRecyclerViewScadatel(scadatel.data!!)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun handleFab() {
        binding.fabMenu.setOnClickListener {
            onFabMenuClick()
        }

        binding.fabScan.setOnClickListener {
            val intentToScan = Intent(this, ScanActivity::class.java)
            startActivity(intentToScan)
        }

        binding.fabAdd.setOnClickListener {
            val intentToAdd = Intent(this, AddKeypointsDescActivity::class.java)
            startActivity(intentToAdd)
        }
    }

    private fun setRecyclerView() {
        viewModel.getUserToken().observe(this) { token ->
            viewModel.getUserDivision().observe(this) { division ->
                binding.tvDivision.text = division.uppercase(Locale.getDefault())
                Log.e(
                    "TEST",
                    division.toString() + Divisions.RTU.divisionName + Divisions.SCADATEL.divisionName
                )
             
                when (division) {
                    Divisions.RTU.divisionName -> {
                        viewModel.getAllRTU(token).observe(this) { rtu ->
                            when (rtu) {
                                is Resource.Error -> {
                                    binding.refKeypoints.isRefreshing = false
                                    showToast("Terdapat kesahalan, silahkan refresh kembali halaman ini: ${rtu.message}")
                                }
                                is Resource.Loading -> {
                                    binding.refKeypoints.isRefreshing = true
                                }
                                is Resource.Message -> {
                                    binding.refKeypoints.isRefreshing = false
                                    Log.e("HomeViewModel", rtu.message.toString())
                                }
                                is Resource.Success -> {
                                    binding.refKeypoints.isRefreshing = false
                                    initRecyclerViewRTU(rtu.data!!)
                                }
                            }
                        }
                    }
                    Divisions.SCADATEL.divisionName -> {
                        viewModel.getAllScadatel(token).observe(this) { scadatel ->
                            when (scadatel) {
                                is Resource.Error -> {
                                    binding.refKeypoints.isRefreshing = false
                                    showToast("Terdapat kesahalan, silahkan refresh kembali halaman ini: ${scadatel.message}")
                                }
                                is Resource.Loading -> {
                                    binding.refKeypoints.isRefreshing = true
                                }
                                is Resource.Message -> {
                                    binding.refKeypoints.isRefreshing = false
                                    Log.e("HomeViewModel", scadatel.message.toString())
                                }
                                is Resource.Success -> {
                                    binding.refKeypoints.isRefreshing = false
                                    initRecyclerViewScadatel(scadatel.data!!)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupSearch() {
        binding.edSearch.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                event?.action == KeyEvent.ACTION_DOWN &&
                event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                val query = binding.edSearch.text.toString().trim()
                if (query.isNotEmpty()) {
                    viewModel.getUserToken().observe(this) { token ->
                        viewModel.getUserDivision().observe(this) { division ->
                            when (division) {
                                Divisions.RTU.divisionName -> {
                                    viewModel.findRTUKeypoints(token, query).observe(this) { rtu ->
                                        when (rtu) {
                                            is Resource.Error -> {
                                                binding.refKeypoints.isRefreshing = false
                                                showToast("Terdapat kesahalan, silahkan refresh kembali halaman ini: ${rtu.message}")
                                            }
                                            is Resource.Loading -> {
                                                binding.refKeypoints.isRefreshing = true
                                            }
                                            is Resource.Message -> {
                                                binding.refKeypoints.isRefreshing = false
                                                showLongToast("Keypoints yang dicari tidak ditemukan")
                                            }
                                            is Resource.Success -> {
                                                binding.refKeypoints.isRefreshing = false
                                                initRecyclerViewRTU(rtu.data!!)
                                            }
                                        }
                                    }
                                }
                                Divisions.SCADATEL.divisionName -> {
                                    viewModel.findScadatelKeypoints(token, query)
                                        .observe(this) { scadatel ->
                                            when (scadatel) {
                                                is Resource.Error -> {
                                                    binding.refKeypoints.isRefreshing = false
                                                    showToast("Terdapat kesahalan, silahkan refresh kembali halaman ini: ${scadatel.message}")
                                                }
                                                is Resource.Loading -> {
                                                    binding.refKeypoints.isRefreshing = true
                                                }
                                                is Resource.Message -> {
                                                    binding.refKeypoints.isRefreshing = false
                                                    showLongToast("Keypoints yang dicari tidak ditemukan")
                                                }
                                                is Resource.Success -> {
                                                    binding.refKeypoints.isRefreshing = false
                                                    initRecyclerViewScadatel(scadatel.data!!)
                                                }
                                            }
                                        }
                                }
                            }
                        }
                    }
                }

                val inputMethodManager =
                    this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(binding.edSearch.windowToken, 0)

                return@setOnEditorActionListener true
            }

            return@setOnEditorActionListener false
        }
    }

    private fun initRecyclerViewScadatel(scadatelList: List<Scadatel>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvKeypoints.layoutManager = layoutManager

        val adapter = ScadatelKeypointsAdapter(scadatelList)
        binding.rvKeypoints.adapter = adapter
        adapter.setOnItemClickCallback(object : ScadatelKeypointsAdapter.OnItemClickCallback {
            override fun onItemClicked(listScadatel: Scadatel) {

            }
        })
    }

    private fun initRecyclerViewRTU(rtuList: List<RTU>) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvKeypoints.layoutManager = layoutManager

        val adapter = RTUKeypointsAdapter(rtuList)
        binding.rvKeypoints.adapter = adapter
        adapter.setOnItemClickCallback(object : RTUKeypointsAdapter.OnItemClickCallback {
            override fun onItemClicked(listScadatel: RTU) {

            }
        })
    }

    private fun onFabMenuClick() {
        fabMenuState = if (fabMenuState == FabMenuState.COLLAPSED) {
            FabMenuState.EXPANDED
        } else {
            FabMenuState.COLLAPSED
        }
        setVisibility(fabMenuState == FabMenuState.EXPANDED)
        setAnimation(fabMenuState == FabMenuState.EXPANDED)
        setClickable(fabMenuState == FabMenuState.EXPANDED)
    }

    private fun setAnimation(isClicked: Boolean) {
        val rotateOpen: Animation by lazy {
            AnimationUtils.loadAnimation(this, R.anim.anim_rotate_open)
        }
        val rotateClose: Animation by lazy {
            AnimationUtils.loadAnimation(this, R.anim.anim_rotate_close)
        }
        val fromBottom: Animation by lazy {
            AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom)
        }
        val toBottom: Animation by lazy {
            AnimationUtils.loadAnimation(this, R.anim.anim_to_bottom)
        }

        if (isClicked) {
            binding.fabAdd.startAnimation(fromBottom)
            binding.tvAddKeypoints.startAnimation(fromBottom)
            binding.fabScan.startAnimation(fromBottom)
            binding.tvScanQr.startAnimation(fromBottom)
            binding.fabMenu.startAnimation(rotateOpen)
        } else {
            binding.fabAdd.startAnimation(toBottom)
            binding.tvAddKeypoints.startAnimation(toBottom)
            binding.fabScan.startAnimation(toBottom)
            binding.tvScanQr.startAnimation(toBottom)
            binding.fabMenu.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(isClicked: Boolean) {
        if (isClicked) {
            binding.fabScan.visibility = View.VISIBLE
            binding.tvScanQr.visibility = View.VISIBLE
            binding.fabAdd.visibility = View.VISIBLE
            binding.tvAddKeypoints.visibility = View.VISIBLE
        } else {
            binding.fabScan.visibility = View.INVISIBLE
            binding.tvScanQr.visibility = View.INVISIBLE
            binding.fabAdd.visibility = View.INVISIBLE
            binding.tvAddKeypoints.visibility = View.INVISIBLE
        }
    }

    private fun setClickable(isClicked: Boolean) {
        if (isClicked) {
            binding.fabAdd.isClickable = true
            binding.fabScan.isClickable = true
        } else {
            binding.fabAdd.isClickable = false
            binding.fabScan.isClickable = false
        }
    }
}