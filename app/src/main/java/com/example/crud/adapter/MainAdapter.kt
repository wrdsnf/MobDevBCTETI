package com.example.crud.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.crud.databinding.ItemMainBinding
import com.example.crud.model.User

class MainAdapter: ListAdapter<User, MainAdapter.MainViewHolder>(diffCallback) {
    inner class MainViewHolder(val binding: ItemMainBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: User){
            with(binding) {
                tvName.text = item.name
                tvEmail.text = item.email
            }
        }
    }

    companion object{
        val diffCallback = object : DiffUtil.ItemCallback<User>(){
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }

    //Setting layout yang akan ditampilkan
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(binding)
    }

    //Menampilkan data ke layout
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}