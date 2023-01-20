package com.virus.expressshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.virus.expressshop.data.Favourites
import com.virus.expressshop.databinding.FavItemBinding

class FavouritesAdapter:ListAdapter<Favourites,FavouritesAdapter.FavoritesHolder>(DiffUtilCallBack) {
    lateinit var onClickFavorite:((Favourites)->Unit)
    companion object DiffUtilCallBack : DiffUtil.ItemCallback<Favourites>() {
        override fun areItemsTheSame(oldItem: Favourites, newItem: Favourites): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Favourites, newItem: Favourites): Boolean {
            return oldItem.id == newItem.id
        }

    }
    class FavoritesHolder(var binding: FavItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(favourites: Favourites){
            binding.price.text = "$ ${favourites.price}"
            binding.name.text = favourites.title
            Glide.with(itemView.context).load(favourites.image).into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesHolder {
        return FavoritesHolder(FavItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: FavoritesHolder, position: Int) {
        val favourites = getItem(position)
        holder.bind(favourites)
        holder.binding.imageView.setOnClickListener {
            onClickFavorite.invoke(favourites)
        }
    }
}