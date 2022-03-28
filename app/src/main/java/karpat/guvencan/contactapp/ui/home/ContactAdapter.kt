package karpat.guvencan.contactapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import karpat.guvencan.contactapp.data.local.Contact
import karpat.guvencan.contactapp.databinding.ItemContactBinding

class ContactAdapter(private val onClick: (Contact?) -> Unit) :
    PagingDataAdapter<Contact, ContactAdapter.ContactViewHolder>(ContactDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder =
        ContactViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener { onClick.invoke(item) }
        holder.bind(item)
    }

    class ContactViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(_item: Contact?) {
            with(binding) {
                item = _item
                executePendingBindings()
            }
        }

    }

    object ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }
    }

}

