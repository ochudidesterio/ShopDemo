package com.virus.expressshop.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.virus.expressshop.R
import com.virus.expressshop.activities.MainActivity
import com.virus.expressshop.adapters.CartAdapter
import com.virus.expressshop.data.Cart
import com.virus.expressshop.databinding.FragmentCartBinding
import com.virus.expressshop.viewmodels.HomeViewModel
import kotlinx.coroutines.launch


class CartFragment : Fragment() {
    lateinit var viewModel: HomeViewModel
    lateinit var cartAdapter: CartAdapter

lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        cartAdapter =  CartAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ptotal.visibility = View.INVISIBLE
        binding.checkout.visibility = View.INVISIBLE
        lifecycleScope.launch {
            viewModel.getCartItems().collect{
                var total = 0.0
                for(cart in it){
                    total+=cart.price
                }
                cartAdapter.submitList(it)
                binding.rvCart.apply {
                    adapter = cartAdapter
                }
                if(it.isNotEmpty()){

                    binding.ptotal.visibility = View.VISIBLE
                    binding.checkout.visibility = View.VISIBLE
                    binding.total.text = "$ ${total}"
                }
            }
        }
        onClickAddorRemove()
    }

    private fun onClickAddorRemove() {
        cartAdapter.onClickAdorRemove = {
            viewModel.removeFromCart(it)
        }
        cartAdapter.notifyDataSetChanged()
    }


}