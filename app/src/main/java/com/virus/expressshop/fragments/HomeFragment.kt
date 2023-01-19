package com.virus.expressshop.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.virus.expressshop.R
import com.virus.expressshop.adapters.CategoriesAdapter
import com.virus.expressshop.adapters.ProductsAdapter
import com.virus.expressshop.data.Product
import com.virus.expressshop.databinding.FragmentHomeBinding
import com.virus.expressshop.viewmodels.HomeViewModel


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var productsAdapter: ProductsAdapter
    lateinit var categoriesAdapter: CategoriesAdapter
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[HomeViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        productsAdapter = ProductsAdapter()
        categoriesAdapter = CategoriesAdapter()
        onFavIconClicked()
    }

    private fun onFavIconClicked() {
        productsAdapter.onClickFavIcon = {
            productsAdapter.notifyDataSetChanged()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllProducts()
        viewModel.getCategories()
        viewModel.AllProductsObserver().observe(viewLifecycleOwner){
            productsAdapter.submitList(it)
            binding.rvProducts.apply {
                layoutManager = GridLayoutManager(requireActivity(),2,GridLayoutManager.VERTICAL,false)
                adapter = productsAdapter
            }
        }
        viewModel.CategoriesObserver().observe(viewLifecycleOwner){

            categoriesAdapter.setCategories(it as ArrayList<String>)
            binding.rvCategories.apply {
                layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
                adapter = categoriesAdapter
            }
        }
    }


}