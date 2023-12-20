package com.example.mytestapp.presentation.feature.main.winget

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.mytestapp.databinding.AlertDialogBinding

class AlertDialog (
    private val onDialogNegativeClick: ((message:String) -> Unit)? = null,
) : DialogFragment() {

    private val binding by lazy { AlertDialogBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding){
        btnAlertDialog.setOnClickListener {
            if (!editText.text.isNullOrEmpty()) onDialogNegativeClick?.invoke(editText.text.toString())
            dialog?.dismiss()
        }
    }
}