package com.virus.expressshop.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.virus.expressshop.R
import com.virus.expressshop.activities.MainActivity
import com.virus.expressshop.data.Cart
import com.virus.expressshop.data.Product
import com.virus.expressshop.databinding.FragmentProductBinding
import com.virus.expressshop.databinding.ProductBinding
import com.virus.expressshop.viewmodels.HomeViewModel
import kotlinx.coroutines.launch


class ProductFragment : Fragment() {

    lateinit var binding: FragmentProductBinding
    lateinit var product: Product
    lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProduct().observe(viewLifecycleOwner){
            product = it
            binding.name.text = it.title
            binding.description.text = it.description
            binding.price.text = "$ ${it.price}"
            binding.tvcategory.text = it.category
            Glide.with(requireActivity()).load(it.image).into(binding.image)
            if(it.isFavorite){
                binding.favoritesFab.setImageDrawable(requireActivity().resources.getDrawable(R.drawable.ic_favorite))
            }else{
                binding.favoritesFab.setImageDrawable(requireActivity().resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
            }
            binding.collapsingToolBar.apply {
                title=it.title
            }
        }
        binding.btnAdd.setOnClickListener {
            addToCart(product);
        }
    }

    private fun addToCart(product: Product) {
        lifecycleScope.launch {
            viewModel.addToCart(Cart(product.category,product.description,product.id,product.image,
                product.price,product.rating,product.title,product.isFavorite,1))
        }
    }


}