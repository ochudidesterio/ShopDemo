package com.virus.expressshop.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.virus.expressshop.R
import com.virus.expressshop.activities.MainActivity
import com.virus.expressshop.adapters.FavouritesAdapter
import com.virus.expressshop.data.Cart
import com.virus.expressshop.data.Product
import com.virus.expressshop.databinding.FragmentFavouriteBinding
import com.virus.expressshop.viewmodels.HomeViewModel
import kotlinx.coroutines.launch


class FavouriteFragment : Fragment() {
   lateinit var binding: FragmentFavouriteBinding
   lateinit var viewModel: HomeViewModel
   lateinit var favouritesAdapter: FavouritesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        favouritesAdapter = FavouritesAdapter()
        onClickFavItem()
        onClickAdd()
    }

    private fun onClickAdd() {
        favouritesAdapter.onClickAddFav = {
            val cart = Cart(it.category,it.description,it.id,it.image,it.price,it.rating,it.title,it.isFavorite,1)
            lifecycleScope.launch {
                viewModel.addToCart(cart)
            }
        }
    }

    private fun onClickFavItem() {
        favouritesAdapter.onClickFavorite = {
            val product = Product(it.category,it.description,it.id,it.image,it.price,it.rating,it.title,it.isFavorite)
            viewModel.product.value = product
            val directions = FavouriteFragmentDirections.actionFavouriteFragmentToProductFragment()
            findNavController().navigate(directions)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadFavourites()
    }

    private fun loadFavourites() {
       lifecycleScope.launch {
           viewModel.getAllFavourites().collect {
                if(it.isEmpty()){
                    Toast.makeText(requireActivity(),"You do not have favourites",Toast.LENGTH_LONG).show()
                }else{
                    favouritesAdapter.submitList(it)
                    binding.rvFavorites.apply {
                        layoutManager = LinearLayoutManager(activity,
                            LinearLayoutManager.VERTICAL,false)
                        adapter = favouritesAdapter

                    }
                }
           }
       }
    }
}