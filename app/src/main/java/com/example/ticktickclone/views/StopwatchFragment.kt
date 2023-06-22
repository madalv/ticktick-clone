package com.example.ticktickclone.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ticktickclone.databinding.StopwatchFragmentBinding

class StopwatchFragment : Fragment() {

    private lateinit var binding: StopwatchFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StopwatchFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}