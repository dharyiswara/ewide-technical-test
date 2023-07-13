package com.ewide.test.daryhilmyiswara.ui.gamedetail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.ewide.test.daryhilmyiswara.R
import com.ewide.test.daryhilmyiswara.databinding.ActivityGameDetailBinding
import com.ewide.test.daryhilmyiswara.model.FavoriteGameTable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun startActivity(activity: Activity, gameId: String) {
            activity.startActivity(Intent(activity, GameDetailActivity::class.java).apply {
                val bundle = bundleOf().apply {
                    putString(GAME_ID, gameId)
                }
                putExtras(bundle)
            })
        }

        private const val GAME_ID = "GAME_ID"
    }

    private lateinit var binding: ActivityGameDetailBinding

    private val viewModel by viewModels<GameDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        subscribeToLiveData()
        viewModel.getGameDetail(viewModel.getGameId())
    }

    private fun setupView() {
        viewModel.setGameId(intent?.getStringExtra(GAME_ID).orEmpty())
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.ivFavoriteMovie.setOnClickListener {
            favorite()
        }
    }

    private fun subscribeToLiveData() {
        viewModel.getLoadingLiveData().observe(this) {
            binding.run {
                pbGameDetail.isVisible = it
                if (it) {
                    clGameDetail.visibility = View.GONE
                    tvGameDetailError.visibility = View.GONE
                }
            }
        }

        viewModel.getGameDetailLiveData().observe(this) {
            binding.run {
                clGameDetail.visibility = View.VISIBLE
                Glide.with(this@GameDetailActivity)
                    .load(it?.info?.image)
                    .into(ivGamesDetail)
                tvGameNameDetail.text = it?.info?.title
                tvPriceDetail.text = "$${it?.cheapestPrice?.price}"
            }
            viewModel.getSelectedGame(viewModel.getGameId())
        }

        viewModel.getErrorLiveData().observe(this) {
            binding.tvGameDetailError.visibility = View.VISIBLE
            binding.tvGameDetailError.text = it
        }

        viewModel.getCheckFavoriteListLiveData().observe(this) {
            binding.ivFavoriteMovie.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    if (it) {
                        R.drawable.ic_favorite
                    } else {
                        R.drawable.ic_unfavorite
                    }
                )
            )
        }
        viewModel.getClickFavoriteListLiveData().observe(this) {
            Toast.makeText(
                this,
                if (it) {
                    "Added to favorite"
                } else {
                    "Deleted from favorite"
                },
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun favorite() {
        val favoriteGame = viewModel.getCheckFavoriteListLiveData().value ?: false
        val game = viewModel.getGameDetailLiveData().value
        game?.let {
            if (favoriteGame) {
                viewModel.deleteFavoriteGame(viewModel.getGameId())
            } else {
                viewModel.insertFavoriteGame(
                    FavoriteGameTable(
                        viewModel.getGameId(),
                        it.cheapestPrice?.price.orEmpty(),
                        it.info?.title.orEmpty(),
                        it.info?.image.orEmpty(),
                    )
                )
            }
        }
    }

}