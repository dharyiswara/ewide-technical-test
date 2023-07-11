package com.ewide.test.daryhilmyiswara.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ewide.test.daryhilmyiswara.databinding.ListItemGamesBinding
import com.ewide.test.daryhilmyiswara.model.Games

class GameListViewHolder(
    private val binding: ListItemGamesBinding,
    private val clickListener: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(game: Games) {
        binding.run {
            tvGamesName.text = game.name
            tvGamesPrice.text = "$${game.price}"
            Glide.with(ivGames.context)
                .load(game.image)
                .into(ivGames)

            root.setOnClickListener {
                clickListener.invoke(game.id.orEmpty())
            }
        }
    }

}