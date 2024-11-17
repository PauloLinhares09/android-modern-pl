package com.packapps.features.quiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.packapps.business.analytics.apublic.builder.AnalyticsBuilder
import com.packapps.features.databinding.FragmentHomeBinding
import com.packapps.features.quiz.viewModel.QuizViewModel

class QuizFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<QuizViewModel>()

    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        viewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        showDialog()

        return root
    }

    fun showDialog(){
//        val builder = AnalyticsBuilder()
//
//        val eventBuilder = AnalyticsBuilder.EventBuilder("hash-event-123")
//        eventBuilder.setDimension("DEVICE-ID", "788222409810a")
//        eventBuilder.setDimension("USER-ID", 123)
//        eventBuilder.setDimension("IS-LOGGED", true)
//
//        builder.setEvent(eventBuilder)
//        builder.build()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}