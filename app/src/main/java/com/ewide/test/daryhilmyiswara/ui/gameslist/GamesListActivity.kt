package com.ewide.test.daryhilmyiswara.ui.gameslist

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.daryhilmyiswara.R
import com.ewide.test.daryhilmyiswara.adapter.GamesListAdapter
import com.ewide.test.daryhilmyiswara.databinding.ActivityGamesListBinding
import com.ewide.test.daryhilmyiswara.model.Games
import com.ewide.test.daryhilmyiswara.ui.favorite.FavoriteGameActivity
import com.ewide.test.daryhilmyiswara.ui.gamedetail.GameDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GamesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGamesListBinding

    private val viewModel by viewModels<GamesListViewModel>()
    private val gamesListAdapter by lazy { GamesListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGamesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        subscribeToLiveData()
        viewModel.getGamesList()
    }

    private fun setupView() {
        binding.rvGames.run {
            layoutManager =
                LinearLayoutManager(this@GamesListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = gamesListAdapter.apply {
                setOnClickListener {
                    GameDetailActivity.startActivity(this@GamesListActivity, it)
                }
            }
        }
        binding.ivFavorite.setOnClickListener {
            FavoriteGameActivity.startActivity(this)
        }
        binding.ivSort.setOnClickListener {
            viewModel.getGamesListLiveData().value?.let {
                viewModel.setIsSortAscending(!viewModel.isSortAscending())
                gamesListAdapter.updateListGames(sortData(it))
            }
        }
    }

    private fun subscribeToLiveData() {
        viewModel.getGamesListLiveData().observe(this) {
            if (it.isNotEmpty()) {
                binding.rvGames.visibility = View.VISIBLE
                gamesListAdapter.updateListGames(sortData(it))
            } else {
                binding.rvGames.visibility = View.GONE
                binding.tvGamesError.visibility = View.VISIBLE
                binding.tvGamesError.text = getString(R.string.games_not_found)
            }
        }

        viewModel.getLoadingLiveData().observe(this) {
            binding.run {
                pbGames.isVisible = it
                if (it) {
                    rvGames.visibility = View.GONE
                    tvGamesError.visibility = View.GONE
                }
            }
        }

        viewModel.getErrorLiveData().observe(this) {
            binding.tvGamesError.visibility = View.VISIBLE
            binding.tvGamesError.text = it
        }
    }

    private fun sortData(data: List<Games>): List<Games> {
        return if (viewModel.isSortAscending()) {
            binding.ivSort.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_sort_ascending)
            )
            data.sortedBy { it.name }
        } else {
            binding.ivSort.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_sort_descending)
            )
            data.sortedByDescending { it.name }
        }
    }

}