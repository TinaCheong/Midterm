package com.tina.midterm

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tina.midterm.dataSource.Data
import com.tina.midterm.databinding.ItemTextFieldContentMainBinding
import com.tina.midterm.viewModel.MainViewModel

class MainAdapter (val viewModel: MainViewModel) : ListAdapter<Data, MainAdapter.ItemsViewHolder>(CartItemsDiffCallback) {
    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder.from(parent, viewModel)
    }



    class ItemsViewHolder private constructor(val binding: ItemTextFieldContentMainBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Data) {
            binding.data = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, viewModel: MainViewModel): ItemsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTextFieldContentMainBinding.inflate(layoutInflater, parent, false)
                binding.mainViewModel = viewModel
                return ItemsViewHolder(binding)
            }
        }
    }

    companion object CartItemsDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem.data == newItem.data
        }

        override fun areContentsTheSame(
            oldItem: Data,
            newItem: Data
        ): Boolean {
            return oldItem == newItem
        }
    }
}