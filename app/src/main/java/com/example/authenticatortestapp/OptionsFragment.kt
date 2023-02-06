package com.example.authenticatortestapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.authenticatortestapp.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment(R.layout.fragment_options) {
    private lateinit var binding: FragmentOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOptionsBinding.inflate(inflater, container, false)
        return binding.root
    }
}
