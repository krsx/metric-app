package com.capstone.metricapp.features.detail

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.capstone.metricapp.R
import com.capstone.metricapp.core.data.Resource
import com.capstone.metricapp.core.utils.constans.Divisions
import com.capstone.metricapp.features.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class DeleteKeypointFragment(private val id: String) : DialogFragment() {
    private val viewModel: DetailKeypointViewModel by viewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val builder = AlertDialog.Builder(it, R.style.TransparentDialogTheme)
            val inflater = requireActivity().layoutInflater

            val view = inflater.inflate(R.layout.dialog_logout, null)
            builder.setView(view)
            builder.setCancelable(false)

            val noButton = view?.findViewById<Button>(R.id.btn_no)
            val yesButton = view?.findViewById<Button>(R.id.btn_yes)

            noButton?.setOnClickListener {
                dismiss()
            }

            yesButton?.setOnClickListener {
                viewModel.getUserToken().observe(this) { token ->
                    viewModel.getUserDivision().observe(this) { division ->
                        when (division) {
                            Divisions.RTU.divisionName -> {
                            
                            }
                            Divisions.SCADATEL.divisionName -> {
                                viewModel.deleteScadatelKeypoint(token, id)
                                    .observe(this) { scadatel ->
                                        when (scadatel) {
                                            is Resource.Error -> {
                                                showLoading(false)
                                            }
                                            is Resource.Loading -> {
                                                showLoading(true)
                                            }
                                            is Resource.Message -> {
                                                Log.e(
                                                    "DeleteKeypointFragment",
                                                    scadatel.message.toString()
                                                )
                                            }
                                            is Resource.Success -> {
                                                val intentBackToHome = Intent(
                                                    requireContext(),
                                                    HomeActivity::class.java
                                                )
                                                startActivity(intentBackToHome)
                                                activity?.finish()
                                            }
                                        }
                                    }
                            }
                        }
                    }
                }
            }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun showLoading(isLoading: Boolean) {
        val loading = view?.findViewById<ProgressBar>(R.id.progress_bar)
        if (isLoading) {
            loading?.visibility = View.VISIBLE
        } else {
            loading?.visibility = View.GONE
        }
    }
}