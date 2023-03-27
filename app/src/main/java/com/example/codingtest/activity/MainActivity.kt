package com.example.codingtest.activity

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.codingtest.R
import com.example.codingtest.adapter.AlbumListAdapter
import com.example.codingtest.database.AppDatabase
import com.example.codingtest.databinding.ActivityMainBinding
import com.example.codingtest.retrofit.model.Album2
import com.example.codingtest.retrofit.model.SearchResult2
import com.example.codingtest.viewmodel.AlbumViewModel

class MainActivity : BaseAct<ActivityMainBinding>() {

    private lateinit var viewModel: AlbumViewModel
    private lateinit var adapter: AlbumListAdapter
    private var albumList: List<Album2>? = null
    private var isBookmarked = false

    private var liveData1: LiveData<List<Album2>>? = null
    private var observer1: Observer<List<Album2>>? = null

    private var liveData2: LiveData<List<Album2>>? = null
    private var observer2: Observer<List<Album2>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        adapter = AlbumListAdapter(this)
        binding.rvAlbum.itemAnimator = null;
        binding.rvAlbum.adapter = adapter

        viewModel = ViewModelProvider(this)[AlbumViewModel::class.java]
        viewModel.getSearchAlbums(
            this,
            "jack+johnson",
            "album"
        )

        viewModel.observeAlbumListLiveData().observe(
            this
        ) { t ->
            if (t != null) {
                startLoading()
                val result = AppDatabase.getInstance(this).getAlbumDao().deleteAndInsert(t.results)
                if (result) {
                    stopLoading()
                    adapter.updateAlbumList(t.results)
                } else {
                    stopLoading()
                }
            }
        }

        binding.actionBar.icnBookMark.setOnClickListener {
//            unObserveAll()
            isBookmarked = !isBookmarked

            if (isBookmarked) {
                it.background =
                    AppCompatResources.getDrawable(this, R.drawable.ic_bookmark_filled)
                val list = AppDatabase.getInstance(this).getAlbumDao().getBookmarked()
                adapter.updateAlbumList(list)
//                observer2 = Observer<List<Album2>> { list ->
//                    Log.d("MainActivity", "observer2:" + list.toString())
//                    adapter.updateAlbumList(list)
//                }
//                getLiveData2().observe(this, observer2!!)
            } else {
                it.background =
                    AppCompatResources.getDrawable(this, R.drawable.ic_bookmark_border)
                val list = AppDatabase.getInstance(this).getAlbumDao().getAllAsync()
                adapter.updateAlbumList(list)
//                observer1 = Observer<List<Album2>> { list ->
//                    Log.d("MainActivity", "observer1:" + list.toString())
//                    adapter.updateAlbumList(list)
//                }
//                getLiveData1().observe(this, observer1!!)
            }
        }

    }

//    private fun getLiveData1(): LiveData<List<Album2>> {
//        if (liveData1 == null) {
//            liveData1 = AppDatabase.getInstance(this).getAlbumDao().getAllAsync()
//        }
//        return liveData1!!
//    }
//
//    private fun getLiveData2(): LiveData<List<Album2>> {
//        if (liveData2 == null) {
//            liveData2 = AppDatabase.getInstance(this).getAlbumDao().getBookmarked()
//        }
//        return liveData2!!
//    }
//
//    private fun unObserveAll() {
//        observer1?.let {
//            getLiveData1().removeObserver(it)
//        }
//        observer2?.let {
//           getLiveData2().removeObserver(it)
//        }
//    }

    override fun inflateLayout(layoutInflater: LayoutInflater) =
        ActivityMainBinding.inflate(layoutInflater)

}