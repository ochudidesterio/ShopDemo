package com.virus.expressshop.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.virus.expressshop.R
import com.virus.expressshop.activities.MainActivity
import com.virus.expressshop.activities.ProductViewActivity
import com.virus.expressshop.adapters.CategoriesAdapter
import com.virus.expressshop.adapters.ProductsAdapter
import com.virus.expressshop.data.Cart
import com.virus.expressshop.data.Favourites
import com.virus.expressshop.data.Product
import com.virus.expressshop.databinding.FragmentHomeBinding
import com.virus.expressshop.viewmodels.HomeViewModel
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var productsAdapter: ProductsAdapter
    lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var  viewModel: HomeViewModel
    private var favoritesList : List<Favourites>? = null
    private var productList : List<Product>? = null
    private var categories = mutableListOf("All")


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
        viewModel = (activity as MainActivity).viewModel
        onFavIconClicked()
        onClickProductImage()
        onClickAdd()
    }

    private fun onClickAdd() {
        productsAdapter.onClickAdd = {
            val cart = Cart(it.category,it.description,it.id,it.image,it.price,it.rating,it.title,it.isFavorite,1)
            lifecycleScope.launch {
                viewModel.addToCart(cart)
            }
        }
    }

    private fun onClickProductImage() {
        productsAdapter.onClickProductImage = {
            viewModel.product.value = it
//            var intent = Intent(activity,ProductViewActivity::class.java)
//            startActivity(intent)
            val directions = HomeFragmentDirections.actionHomeFragmentToProductFragment()
            findNavController().navigate(directions)
        }
    }

    private fun onFavIconClicked() {
        productsAdapter.onClickFavIcon ={
            productsAdapter.notifyDataSetChanged()
            //viewModel.insertProduct(it)
            var favourites = Favourites(it.category,it.description,it.id,it.image,it.price,it.rating,it.title,it.isFavorite)
            if(favourites.isFavorite){
                 viewModel.insertFavourites(favourites)
                 //viewModel.insertProduct(it)
            }else{
                viewModel.deleteFavorites(favourites)
                // viewModel.deleteProduct(it)
            }
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       loadCachedProduct();
        viewModel.CategoriesObserver().observe(viewLifecycleOwner){
            categories.addAll(it)
            categoriesAdapter.setCategories(categories as ArrayList<String>)
            binding.rvCategories.apply {
                layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
                adapter = categoriesAdapter
            }
        }
        categoriesAdapter.onClickView.apply {

        }
    }

    private fun loadCachedProduct() {
        lifecycleScope.launch {
            viewModel.getAllFavourites().collect{
                favoritesList = it
            } }
        lifecycleScope.launch {
            viewModel.getAllCachedProduct().collect{
                productList =it
                loadProducts()
            }
        }
        }

    private fun loadProducts() {
        if (!favoritesList.isNullOrEmpty()){
            for(fav in favoritesList!!){
                for(prod in productList!!){
                    if(fav.id == prod.id)
                    {
                        prod.isFavorite = true
                    }
                }
            }
            productsAdapter.submitList(productList)

        }else{
            productsAdapter.submitList(productList)
        }

        binding.rvProducts.apply {
            layoutManager = GridLayoutManager(requireActivity(),2,GridLayoutManager.VERTICAL,false)
            adapter = productsAdapter
        }
    }
}


