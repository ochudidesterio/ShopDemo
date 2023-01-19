package com.virus.expressshop.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.virus.expressshop.databinding.CategoryBinding

class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    private var categories = ArrayList<String>()
    fun setCategories(list: ArrayList<String>){
        this.categories = list
    }
    class CategoriesHolder(val binding: CategoryBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        return CategoriesHolder(CategoryBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.binding.category.text = categories[position]
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}