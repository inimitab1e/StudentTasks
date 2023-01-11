package com.example.student_tasks.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class NoConnectionDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setMessage("For correct work please check your internet connection")
            .setPositiveButton("Try again") { _,_ -> }
            .create()

    companion object {
        const val TAG = "NoConnectionDialogFragment"
    }
}