package com.capstone.metricapp.features.detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.R
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.ui.adapter.DetailKeypointsSectionsAdapter
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.constans.Divisions
import com.capstone.metricapp.core.utils.extractId
import com.capstone.metricapp.core.utils.showLongToast
import com.capstone.metricapp.databinding.ActivityDetailKeypointsBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailKeypointActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKeypointsBinding
    private val viewModel: DetailKeypointViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKeypointsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        val keypointsId = (intent.getStringExtra(KEY_ID_KEYPOINTS)!!)
        setupTabs(keypointsId)

        viewModel.getUserToken().observe(this) { token ->
            viewModel.getUserDivision().observe(this) { division ->
                setupHeaderInfo(token, keypointsId, division)
            }
        }

        setupDropdownMenu()


    }

    private fun setupDropdownMenu() {
        binding.btnMenu.setOnClickListener {
            val popUpMenu = PopupMenu(this, binding.btnMenu)
            popUpMenu.menuInflater.inflate(R.menu.keypoint_detail_menu, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menuExcel -> {

                        true
                    }
                    R.id.menuPdf -> {

                        true
                    }
                    R.id.menuKeypointDelete -> {

                        true
                    }
                    else -> false
                }
            }
            popUpMenu.show()
        }
    }

    private fun setupTabs(id: String) {
        val extractedId = extractId(id)
        val detailKeypointsSectionsAdapter = DetailKeypointsSectionsAdapter(this, extractedId)

        binding.viewPager.adapter = detailKeypointsSectionsAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupHeaderInfo(token: String, id: String, division: String) {
        when (division) {
            Divisions.RTU.divisionName -> {
                viewModel.getRTUById(token, id).observe(this) { rtu ->
                    when (rtu) {
                        is Resource.Error -> {
                            Log.e("TEST", "ERROR")
                        }
                        is Resource.Loading -> {
                            Log.e("TEST", "LOADING")
                        }
                        is Resource.Message -> {
                            Log.e("TEST", "MESSAGE")
                        }
                        is Resource.Success -> {
                            Log.e("TEST", "SUCCESS")

                            binding.apply {
                                tvDetailRegion.text = rtu.data?.region
                                tvDetailKeypoint.text = rtu.data?.keypoint
                                tvDetailDate.text =
                                    DateUtil.convertDateKeypoints(rtu.data!!.dateCreated)
                            }
                        }
                    }
                }
            }
            Divisions.SCADATEL.divisionName -> {
                viewModel.getScadatelById(token, id).observe(this) { scadatel ->
                    when (scadatel) {
                        is Resource.Error -> {
                            viewModel.setLoading(false)
                            showLongToast("Terjadi kesalahan, pastikan internet dan data yang telah diinput benar")
                        }
                        is Resource.Loading -> {
                            viewModel.setLoading(true)
                        }
                        is Resource.Message -> {
                            Log.e("DetailKeypointActivity", scadatel.message.toString())
                        }
                        is Resource.Success -> {
                            viewModel.setLoading(false)

                            viewModel.setId(scadatel.data?.uniqueId!!)
                            viewModel.setScadatelData(scadatel.data)

                            binding.apply {
                                tvDetailRegion.text = scadatel.data?.region
                                tvDetailKeypoint.text = scadatel.data?.keypoint
                                tvDetailDate.text =
                                    DateUtil.convertDateKeypoints(scadatel.data!!.dateCreated)
                            }
                        }
                    }
                }
            }
        }
    }


    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_spec,
            R.string.tab_history,
            R.string.tab_issue,
        )
    }
}