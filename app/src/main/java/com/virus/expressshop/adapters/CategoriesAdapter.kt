package com.virus.expressshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.virus.expressshop.R
import com.virus.expressshop.data.CategoryStatus
import com.virus.expressshop.databinding.CategoryBinding

class CategoriesAdapter():RecyclerView.Adapter<CategoriesAdapter.CategoriesHolder>() {
    private var categories = mutableListOf<CategoryStatus>()
    private var selectedPosition = -1
    lateinit var onClickView:(status:CategoryStatus)->Unit
    fun setCategories(list: List<CategoryStatus>){
        this.categories.addAll(list)
    }
    class CategoriesHolder(val binding: CategoryBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesHolder {
        return CategoriesHolder(CategoryBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CategoriesHolder, position: Int) {
        holder.binding.category.text = categories[position].category

        holder.itemView.setOnClickListener {
            onClickView.invoke(categories[position])
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
        }

        if(selectedPosition ==position){
            holder.binding.bg.setBackgroundColor(holder.itemView.resources.getColor(R.color.Orange))
        }else{
            holder.binding.bg.setBackgroundColor(holder.itemView.resources.getColor(R.color.primaryDarkColor))

        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}