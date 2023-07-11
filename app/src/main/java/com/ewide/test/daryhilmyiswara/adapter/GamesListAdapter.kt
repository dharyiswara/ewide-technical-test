package com.ewide.test.daryhilmyiswara.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ewide.test.daryhilmyiswara.adapter.viewholder.GameListViewHolder
import com.ewide.test.daryhilmyiswara.databinding.ListItemGamesBinding
import com.ewide.test.daryhilmyiswara.model.Games

class GamesListAdapter : RecyclerView.Adapter<GameListViewHolder>() {

    private var listGames = listOf<Games>()
    private var clickListener: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameListViewHolder {
        val binding = ListItemGamesBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GameListViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: GameListViewHolder, position: Int) {
        holder.bind(listGames[position])
    }

    override fun getItemCount(): Int = listGames.size

    fun updateListGames(list: List<Games>) {
        listGames = list
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (String) -> Unit) {
        clickListener = listener
    }

}