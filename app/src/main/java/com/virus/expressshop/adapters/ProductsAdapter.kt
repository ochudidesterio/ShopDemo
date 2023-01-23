package com.virus.expressshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virus.expressshop.R
import com.virus.expressshop.data.Product
import com.virus.expressshop.databinding.ProductBinding

class ProductsAdapter :ListAdapter<Product,ProductsAdapter.ProductHolder>(DiffCallBack) {
    lateinit var onClickFavIcon:((Product)->Unit)
    lateinit var onClickProductImage:((Product)->Unit)
    lateinit var onClickAdd:((Product)->Unit)

    companion object DiffCallBack: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(ProductBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        //holder.setIsRecyclable(false)
        val product =getItem(position)
        holder.bind(product)
        holder.binding.favorite.setOnClickListener {
            product.isFavorite = !product.isFavorite
            onClickFavIcon.invoke(product)
        }
        holder.binding.imageView.setOnClickListener {
            onClickProductImage.invoke(product)
        }
        holder.binding.add.setOnClickListener {
            onClickAdd.invoke(product)
        }


    }




    class ProductHolder(val binding: ProductBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product: Product){
            binding.price.text = "$ ${product.price}"
            binding.name.text = product.title
            Glide.with(itemView)
                .load(product.image)
                .into(binding.imageView)
            if(product.isFavorite){
                binding.favorite.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_favorite))
            }else{
                binding.favorite.setImageDrawable(itemView.resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
            }

        }
    }

}