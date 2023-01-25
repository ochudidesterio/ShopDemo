package com.virus.expressshop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.virus.expressshop.R
import com.virus.expressshop.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ediProfile.setOnClickListener {
            val directions = ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment()
            findNavController().navigate(directions)
        }
        binding.orders.setOnClickListener {
            val directions = ProfileFragmentDirections.actionProfileFragmentToOrdersFragment()
            findNavController().navigate(directions)
        }
    }

}