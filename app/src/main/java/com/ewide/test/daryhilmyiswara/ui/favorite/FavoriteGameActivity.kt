package com.ewide.test.daryhilmyiswara.ui.favorite

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.daryhilmyiswara.R
import com.ewide.test.daryhilmyiswara.adapter.FavoriteGamesAdapter
import com.ewide.test.daryhilmyiswara.databinding.ActivityFavoriteGameBinding
import com.ewide.test.daryhilmyiswara.ui.gamedetail.GameDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteGameActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, FavoriteGameActivity::class.java))
        }
    }

    private lateinit var binding: ActivityFavoriteGameBinding

    private val viewModel by viewModels<FavoriteGameViewModel>()
    private val favoriteGamesAdapter by lazy { FavoriteGamesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        subscribeToLiveData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteGames()
    }

    private fun setupView() {
        binding.rvFavoriteGames.run {
            layoutManager =
                LinearLayoutManager(this@FavoriteGameActivity, LinearLayoutManager.VERTICAL, false)
            adapter = favoriteGamesAdapter.apply {
                setOnClickListener {
                    GameDetailActivity.startActivity(this@FavoriteGameActivity, it)
                }
            }
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun subscribeToLiveData() {
        viewModel.getLoadingLiveData().observe(this) {
            binding.pbFavoriteGames.isVisible = it
            if (it) {
                binding.rvFavoriteGames.visibility = View.GONE
                binding.tvFavoriteGamesError.visibility = View.GONE
            }
        }
        viewModel.getFavoriteGameLiveData().observe(this) {
            if (it.isNotEmpty()) {
                binding.rvFavoriteGames.visibility = View.VISIBLE
                favoriteGamesAdapter.updateListGames(it)
            } else {
                binding.tvFavoriteGamesError.visibility = View.VISIBLE
                binding.tvFavoriteGamesError.text = getString(R.string.no_favorite)
            }
        }
    }

}