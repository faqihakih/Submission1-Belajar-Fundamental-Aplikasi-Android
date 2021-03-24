package com.akih.submissionbfaa1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akih.submissionbfaa1.data.Github
import com.akih.submissionbfaa1.databinding.ItemCardviewGithubListBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GithubAdapter (val dataGithub : ArrayList<Github>) : RecyclerView.Adapter<GithubAdapter.ViewHolder>() {
    lateinit var listener : setOnClickItemListerner
    fun setClick(listerner: setOnClickItemListerner){
        this.listener = listerner
    }
   inner class ViewHolder (val binding: ItemCardviewGithubListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(github: Github){
            with(binding){
                Glide.with(itemView.context)
                        .load(github.avatar)
                        .apply(RequestOptions().override(80, 80))
                        .into(imgItem)
                tvItemName.text = github.name
                tvItemUsername.text = github.username
                tvItemCompany.text = github.company
                itemView.setOnClickListener {
                    listener.setClickItem(github)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemCardviewGithubListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = dataGithub.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataGithub[position])

    interface setOnClickItemListerner{
        fun setClickItem(github: Github)
    }
}