package com.virus.expressshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virus.expressshop.data.Cart
import com.virus.expressshop.databinding.CartItemBinding
import com.virus.expressshop.viewmodels.HomeViewModel

class CartAdapter(): ListAdapter<Cart, CartAdapter.CartHolder>(DIFFCALLBACK) {
    lateinit var onClickAdorRemove:((Cart)->Unit)
    companion object DIFFCALLBACK: DiffUtil.ItemCallback<Cart>() {
        override fun areItemsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Cart, newItem: Cart): Boolean {
            return oldItem.id == newItem.id
        }

    }
    class CartHolder(var binding: CartItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(cart: Cart){
            binding.price.text = "$ ${cart.price}"
            Glide.with(itemView).load(cart.image).into(binding.image)
            var count = 1
            binding.count.text = "$ ${cart.count}"
//             binding.increment.setOnClickListener {
//                count++
//                binding.count.text = count.toString()
//                 binding.price.text = "$ ${count * cart.price}"
//
//            }
//            binding.decrement.setOnClickListener {
//                if(count != 1){
//                    count--
//                    binding.count.text = count.toString()
//                     binding.price.text = "$ ${count * cart.price}"
//                }
//            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        return CartHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
       val cart = getItem(position)
        holder.bind(cart)
        holder.binding.remove.setOnClickListener {
            onClickAdorRemove.invoke(cart)
        }
    }
}