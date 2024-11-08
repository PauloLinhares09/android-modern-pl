package com.packapps.features.quiz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.packapps.business.analytics.apublic.Analytics
import com.packapps.business.analytics.apublic.Dimensions
import com.packapps.features.databinding.FragmentHomeBinding
import com.packapps.features.quiz.analytics.QuizAnalytics
import com.packapps.features.quiz.viewModel.QuizViewModel

class QuizFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<QuizViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        return root
    }

    fun showDialog(){

        Analytics {
            Event(QuizAnalytics.EVENT) {
                Dimension {
                    Dimensions.DEVICE_ID value "123456"
                    QuizAnalytics.QUIZ_SPECIFIC_DIMENSION value binding.root.id
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}