package com.example.codingtest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.codingtest.R
import com.example.codingtest.activity.MainActivity
import com.example.codingtest.database.AppDatabase
import com.example.codingtest.databinding.ItemAlbumBinding
import com.example.codingtest.retrofit.model.Album2
import java.text.SimpleDateFormat
import java.util.*


class AlbumListAdapter(
    private val act: MainActivity
) : RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {

    private var albumList: List<Album2> = listOf()
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

    inner class ViewHolder(val itemAlbumBinding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(itemAlbumBinding.root) {

        var album: Album2? = null

    }

    private fun checkBookmark(album: Album2, itemAlbumBinding: ItemAlbumBinding) {
        if (album.isBookmarked) {
            itemAlbumBinding.btnBookMark.background = AppCompatResources.getDrawable(act, R.drawable.ic_bookmark_filled)
        } else {
            itemAlbumBinding.btnBookMark.background = AppCompatResources.getDrawable(act, R.drawable.ic_bookmark_border)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListAdapter.ViewHolder {
        return ViewHolder(
            ItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumListAdapter.ViewHolder, position: Int) {
//        val album = albumList[position]
//        holder.bindItem(album)

        holder.itemAlbumBinding.btnBookMark.setOnClickListener {
            if (holder.album?.isBookmarked == true) {
                holder.album!!.isBookmarked = false
                Log.d("AlbumListAdapter - btnBookMark onclick isBookmarked = false", holder.album.toString())

                AppDatabase.getInstance(act).getAlbumDao()
                    .removeBookmarked(holder.album!!.collectionId)
                notifyDataSetChanged()
            } else {
                holder.album?.isBookmarked = true
                Log.d("AlbumListAdapter - btnBookMark onclick isBookmarked = true", holder.album.toString())

                AppDatabase.getInstance(act).getAlbumDao().addBookmarked(holder.album!!.collectionId)

                notifyDataSetChanged()
            }
        }

        holder.album = albumList[position]

        holder.itemAlbumBinding.txtName.text = holder.album?.collectionName
        holder.itemAlbumBinding.txtArtistName.text = holder.album?.artistName
        holder.itemAlbumBinding.txtPrice.text = "$" + holder.album?.collectionPrice.toString()
        holder.itemAlbumBinding.txtReleaseDate.text =
            simpleDateFormat.format(simpleDateFormat.parse(holder.album?.releaseDate)).toString()

        Glide.with(act)
            .load(holder.album?.artworkUrl100)
            .timeout(10000)
            .into(holder.itemAlbumBinding.imgAlbum)

        checkBookmark(holder.album!!, holder.itemAlbumBinding)

    }

    override fun getItemCount(): Int {
        return albumList.size
    }

    fun updateAlbumList(albumList: List<Album2>) {
        this.albumList = albumList
        notifyDataSetChanged()
    }


}