package com.ewide.test.daryhilmyiswara.ui.gameslist

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.daryhilmyiswara.R
import com.ewide.test.daryhilmyiswara.adapter.GamesListAdapter
import com.ewide.test.daryhilmyiswara.databinding.ActivityGamesListBinding
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

                }
            }
        }
    }

    private fun subscribeToLiveData() {
        viewModel.getGamesListLiveData().observe(this) {
            if (it.isNotEmpty()) {
                binding.rvGames.visibility = View.VISIBLE
                gamesListAdapter.updateListGames(it)
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

}