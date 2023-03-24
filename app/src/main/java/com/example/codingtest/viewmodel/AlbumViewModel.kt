package com.example.codingtest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codingtest.activity.MainActivity
import com.example.codingtest.database.AppDatabase
import com.example.codingtest.retrofit.RetrofitInstance
import com.example.codingtest.retrofit.model.Album2
import com.example.codingtest.retrofit.model.SearchResult2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlbumViewModel: ViewModel() {
    private var albumLiveData = MutableLiveData<SearchResult2>()

    fun getSearchAlbums(act: MainActivity,
                        searchTerm: String,
                        entity: String) {
        act.startLoading()
        RetrofitInstance.api.searchAlbums(searchTerm, entity)
            .enqueue(object: Callback<SearchResult2>{
                override fun onResponse(
                    call: Call<SearchResult2>,
                    response: Response<SearchResult2>
                ) {
                    if(response.body() != null){
                        albumLiveData.postValue(response.body())
                        act.stopLoading()
                    }else{
                        return
                    }
                }

                override fun onFailure(call: Call<SearchResult2>, t: Throwable) {
                    Log.d("TAG",t.message.toString())
                }
            })
    }

    fun observeAlbumListLiveData() : MutableLiveData<SearchResult2> {
        return albumLiveData
    }
}