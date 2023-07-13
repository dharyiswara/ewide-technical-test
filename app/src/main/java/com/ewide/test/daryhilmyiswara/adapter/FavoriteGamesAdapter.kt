package com.ewide.test.daryhilmyiswara.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.daryhilmyiswara.adapter.viewholder.FavoriteGamesViewHolder
import com.ewide.test.daryhilmyiswara.databinding.ListItemGamesBinding
import com.ewide.test.daryhilmyiswara.model.FavoriteGameTable

class FavoriteGamesAdapter : RecyclerView.Adapter<FavoriteGamesViewHolder>() {

    private var listGames = listOf<FavoriteGameTable>()
    private var clickListener: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteGamesViewHolder {
        val binding = ListItemGamesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavoriteGamesViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: FavoriteGamesViewHolder, position: Int) {
        holder.bind(listGames[position])
    }

    override fun getItemCount(): Int = listGames.size

    fun updateListGames(list: List<FavoriteGameTable>) {
        listGames = list
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (String) -> Unit) {
        clickListener = listener
    }

}