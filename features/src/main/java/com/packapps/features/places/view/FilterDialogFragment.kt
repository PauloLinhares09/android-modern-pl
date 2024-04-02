package com.packapps.features.places.view

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.packapps.features.R
import com.packapps.features.databinding.DialogFilterBinding


class FilterDialogFragment : DialogFragment() {

    private var _binding: DialogFilterBinding? = null
    private val binding get() = _binding!!

    interface FilterDialogListener {
        fun onFilterApply(priceRange: Int, openedNow: Boolean, radius: Int)
        fun onFilterCancel()
    }

    var listener: FilterDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogFilterBinding.inflate(LayoutInflater.from(context))

        // Evita que o diálogo seja recriado se já estiver sendo exibido
        isCancelable = false

        binding.btnCancel.setOnClickListener {
            listener?.onFilterCancel()
            dismiss()
        }

        binding.sliderRadius.addOnChangeListener { slider, value, fromUser ->
            binding.tvRadiusValue.text = getString(R.string.radius_value, value.toInt())
        }

        binding.btnApply.setOnClickListener {
            val priceRangeChecked = binding.spPriceRange.selectedItemPosition
            val openedNowChecked = binding.switchOpenNow.isChecked
            val radius = binding.sliderRadius.value.toInt()
            listener?.onFilterApply(priceRangeChecked, openedNowChecked, radius)
            dismiss()
        }

        return Dialog(requireContext()).apply {
            setContentView(binding.root)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}