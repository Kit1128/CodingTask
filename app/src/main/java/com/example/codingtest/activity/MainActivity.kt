package com.example.codingtest.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.codingtest.R
import com.example.codingtest.adapter.AlbumListAdapter
import com.example.codingtest.database.AppDatabase
import com.example.codingtest.databinding.ActivityMainBinding
import com.example.codingtest.retrofit.model.Album2
import com.example.codingtest.retrofit.model.SearchResult2
import com.example.codingtest.viewmodel.AlbumViewModel

class MainActivity : BaseAct<ActivityMainBinding>(), View.OnClickListener{

    private lateinit var viewModel: AlbumViewModel
    private lateinit var adapter: AlbumListAdapter
    private var albumList: List<Album2>? = null
    private var isBookmarked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adapter = AlbumListAdapter(this)
        binding.rvAlbum.adapter = adapter
        setBtnListener(this, binding.actionBar.icnBookMark)

        viewModel = ViewModelProvider(this)[AlbumViewModel::class.java]
        viewModel.getSearchAlbums(this,
            "jack+johnson",
            "album")

        viewModel.observeAlbumListLiveData().observe(this
        ) { t ->
            if (t != null) {
                AppDatabase.getInstance(this).getAlbumDao().deleteAndInsert(t.results)
                adapter.updateAlbumList(t.results)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onClick(v: View?) {
        Log.d("MainActivity", "v?.id: ${v?.id}")

        when(v?.id){
            binding.actionBar.icnBookMark.id -> {
                if (isBookmarked) {
                    isBookmarked = false
                    AppDatabase.getInstance(this).getAlbumDao().getAllAsync().observe(this) { list ->
                        Log.d("MainActivity", list.toString())
                        v.background = AppCompatResources.getDrawable(this, R.drawable.ic_bookmark_border)
                        adapter.updateAlbumList(list)
                    }
                } else {
                    isBookmarked = true
                    AppDatabase.getInstance(this).getAlbumDao().getBookmarked().observe(this) { list ->
                        Log.d("MainActivity", list.toString())
                        v.background = AppCompatResources.getDrawable(this, R.drawable.ic_bookmark_filled)
                        adapter.updateAlbumList(list)
                    }
                }

            }
        }
    }

    override fun inflateLayout(layoutInflater: LayoutInflater) = ActivityMainBinding.inflate(layoutInflater)

}