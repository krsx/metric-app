package com.capstone.metricapp.features.detail

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.capstone.metricapp.R
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.ui.adapter.DetailKeypointsSectionsAdapter
import com.capstone.metricapp.core.utils.DateUtil
import com.capstone.metricapp.core.utils.DownloadFileUtils
import com.capstone.metricapp.core.utils.constans.Divisions
import com.capstone.metricapp.core.utils.extractId
import com.capstone.metricapp.core.utils.showLongToast
import com.capstone.metricapp.databinding.ActivityDetailKeypointsBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class DetailKeypointActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailKeypointsBinding
    private val viewModel: DetailKeypointViewModel by viewModels()
    private var keypointsId = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailKeypointsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        keypointsId = (intent.getStringExtra(KEY_ID_KEYPOINTS)!!)
        setupTabs(keypointsId)

        viewModel.getUserToken().observe(this) { token ->
            viewModel.getUserDivision().observe(this) { division ->
                setupHeaderInfo(token, keypointsId, division)
            }
        }

        setupDropdownMenu(keypointsId)

        setupPermissions()
    }

    private fun setupDropdownMenu(id: String) {
        binding.btnMenu.setOnClickListener {
            val popUpMenu = PopupMenu(this, binding.btnMenu)
            popUpMenu.menuInflater.inflate(R.menu.keypoint_detail_menu, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menuExcel -> {
                        viewModel.getUserToken().observe(this) { token ->
                            viewModel.getUserDivision().observe(this) { division ->
                                when (division) {
                                    Divisions.RTU.divisionName -> {
                                        viewModel.exportRTUToExcel(token, id)
                                            .observe(this) { excel ->
                                                when (excel) {
                                                    is Resource.Error -> {
                                                        Log.e("ERROR", "ERROR ${excel.message}")
                                                    }
                                                    is Resource.Loading -> {
                                                        Log.e("LOADING", "LOADING ${excel.message}")
                                                    }
                                                    is Resource.Message -> {
                                                        Log.e("MESSAGE", "MESSAGE ${excel.message}")
                                                    }
                                                    is Resource.Success -> {
                                                        val fileName = "$keypointsId.xlsx"

                                                        DownloadFileUtils.readByteStreamExcel(
                                                            this,
                                                            excel.data!!,
                                                            fileName
                                                        )
                                                        showLongToast("Data sudah didownload, silahkan tunggu beberapa saat")
                                                    }
                                                }

                                            }
                                    }
                                    Divisions.SCADATEL.divisionName -> {
                                        viewModel.exportScadatelToExcel(token, id)
                                            .observe(this) { excel ->
                                                when (excel) {
                                                    is Resource.Error -> {
                                                        Log.e("ERROR", "ERROR ${excel.message}")
                                                    }
                                                    is Resource.Loading -> {
                                                        Log.e("LOADING", "LOADING ${excel.message}")
                                                    }
                                                    is Resource.Message -> {
                                                        Log.e("MESSAGE", "MESSAGE ${excel.message}")
                                                    }
                                                    is Resource.Success -> {
                                                        val fileName = "$keypointsId.xlsx"

                                                        DownloadFileUtils.readByteStreamExcel(
                                                            this,
                                                            excel.data!!,
                                                            fileName
                                                        )
                                                        showLongToast("Data sudah didownload, silahkan tunggu beberapa saat")
                                                    }
                                                }
                                            }
                                    }
                                }
                            }

                        }
                        true
                    }
                    R.id.menuPdf -> {
                        viewModel.getUserToken().observe(this) { token ->
                            viewModel.getUserDivision().observe(this) { division ->
                                when (division) {
                                    Divisions.RTU.divisionName -> {
                                        viewModel.exportRTUToPDF(token, id).observe(this) { pdf ->
                                            when (pdf) {
                                                is Resource.Error -> {
                                                    Log.e("ERROR", "ERROR ${pdf.message}")
                                                }
                                                is Resource.Loading -> {
                                                    Log.e("LOADING", "LOADING ${pdf.message}")
                                                }
                                                is Resource.Message -> {
                                                    Log.e("MESSAGE", "MESSAGE ${pdf.message}")
                                                }
                                                is Resource.Success -> {
                                                    val fileName = "$keypointsId.pdf"
                                                    DownloadFileUtils.readByteStreamPDF(
                                                        this,
                                                        pdf.data!!,
                                                        fileName,
                                                    )
                                                    DownloadFileUtils.logDocumentDirectory(this)
                                                    showLongToast("Data sudah didownload, silahkan tunggu beberapa saat")
                                                }
                                            }
                                        }
                                    }
                                    Divisions.SCADATEL.divisionName -> {
                                        viewModel.exportScadatelToPDF(token, id)
                                            .observe(this) { pdf ->
                                                when (pdf) {
                                                    is Resource.Error -> {
                                                        Log.e("ERROR", "ERROR ${pdf.message}")
                                                    }
                                                    is Resource.Loading -> {
                                                        Log.e("LOADING", "LOADING ${pdf.message}")
                                                    }
                                                    is Resource.Message -> {
                                                        Log.e("MESSAGE", "MESSAGE ${pdf.message}")
                                                    }
                                                    is Resource.Success -> {
                                                        val fileName = "$keypointsId.pdf"

                                                        DownloadFileUtils.readByteStreamPDF(
                                                            this,
                                                            pdf.data!!,
                                                            fileName,
                                                        )
                                                        DownloadFileUtils.logDocumentDirectory(this)
                                                        showLongToast("Data sudah didownload, silahkan tunggu beberapa saat")
                                                    }
                                                }
                                            }
                                    }
                                }

                            }


                        }
                        true
                    }
                    R.id.menuKeypointDelete -> {
                        val deleteKeypointDialog = DeleteKeypointFragment(id)
                        deleteKeypointDialog.show(supportFragmentManager, "Delete Dialog")
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
                            viewModel.setLoading(false)
                            showLongToast("Terjadi kesalahan, pastikan internet dan data yang telah diinput benar")
                        }
                        is Resource.Loading -> {
                            viewModel.setLoading(true)
                        }
                        is Resource.Message -> {
                            Log.e("DetailKeypointActivity", rtu.message.toString())
                        }
                        is Resource.Success -> {
                            viewModel.setLoading(false)

                            viewModel.setId(rtu.data?.uniqueId!!)
                            viewModel.setRTUData(rtu.data)

                            binding.apply {
                                tvDetailRegion.text = rtu.data?.region + " - ${rtu.data.uniqueId}"
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
                                tvDetailRegion.text =
                                    scadatel.data?.region + " - ${scadatel.data.uniqueId}"
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            EXTERNAL_STORAGE -> if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                showLongToast("Anda perlu memberikan izn akses kamera untuk dapat menggunakan fitur ini")
            } else {
                //success
            }
        }
    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            sendRequestPermissions()
        }
    }

    private fun sendRequestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
            EXTERNAL_STORAGE
        )
    }


    companion object {
        const val KEY_ID_KEYPOINTS = "key_id_keypoints"
        const val EXTERNAL_STORAGE = 121

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_spec,
            R.string.tab_history,
            R.string.tab_issue,
        )
    }
}