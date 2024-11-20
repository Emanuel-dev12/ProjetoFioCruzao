package com.example.projetofiocruz.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.projetofiocruz.R
import com.example.projetofiocruz.databinding.ActivityMainBinding
import com.example.projetofiocruz.databinding.FragmentListBinding


class ListFragment : Fragment() {

    var _binding: FragmentListBinding? =null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentListBinding.inflate(inflater, container, false)

        binding.floatingActionButton.setOnClickListener {
        findNavController().navigate(R.id.action_listFragment_to_addFragment)
    }

        return binding.root

    }

}