package com.raihan.myapplication.videokajian

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.raihan.myapplication.R
import com.raihan.myapplication.databinding.ActivityDetailVideoKajianBinding
import com.raihan.myapplication.videokajian.model.VideoKajianModel

class DetailVideoKajianActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailVideoKajianBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailVideoKajianBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDetailKajian)

        val paket = intent.getParcelableExtra<VideoKajianModel>(KUNCI_VIDEO_KAJIAN) as VideoKajianModel

        initView(paket)
    }

    private fun initView(paket: VideoKajianModel) {
        val youTubePlayer: YouTubePlayerView = binding.playerVideo
        lifecycle.addObserver(youTubePlayer)

        youTubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(paket.urlVideo, 0f)
            }
        })

        binding.tvTitle.text = paket.title
        binding.tvChannel.text = paket.channel
        binding.tvSpeaker.text = paket.speaker
        binding.tvSummary.text = paket.summary

        binding.ivShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,
                "kajian dengan judul \"${paket.title}\" " +
                        "dibawakan oleh \"${paket.speaker}\" di channel" +
                        "${paket.channel} \n \n" +
                        "Link :https://www.youtube.com/watch?v=${paket.urlVideo}")

            intent.type = "text/plain"
            startActivity(intent)
        }

    }

    companion object {
        const val KUNCI_VIDEO_KAJIAN = "datavideo"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> { finish()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}