package com.packapps.features.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.packapps.features.databinding.FragmentQuizBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val viewModel by viewModel<QuizViewModel>()


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textQuiz

        viewModel.nameLiveData.observe(viewLifecycleOwner) { nameFromApi ->
            textView.text = "Hello World: $nameFromApi"
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}