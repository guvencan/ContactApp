package karpat.guvencan.contactapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import karpat.guvencan.contactapp.databinding.ItemLoaderBinding

class LoaderStateAdapter: LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {


    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
       holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder =
        LoaderViewHolder(
            ItemLoaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    class LoaderViewHolder(private val binding: ItemLoaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            with(binding) {
                progressBar.isVisible = loadState is LoadState.Loading
                executePendingBindings()
            }
        }

    }

}