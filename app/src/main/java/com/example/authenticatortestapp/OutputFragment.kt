package com.example.authenticatortestapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.authenticatortestapp.databinding.FragmentOutputBinding

class OutputFragment : Fragment(R.layout.fragment_output) {
    private lateinit var binding: FragmentOutputBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOutputBinding.inflate(inflater, container, false)
        return binding.root
    }
}
