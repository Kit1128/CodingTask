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
        holder.album = albumList[position]
        Log.d("btnBookMark", "position = $position")

        holder.itemAlbumBinding.btnBookMark.setOnClickListener {
            if (holder.album?.isBookmarked == true) {
                holder.album!!.isBookmarked = false
                AppDatabase.getInstance(act).getAlbumDao().removeBookmarked(holder.album!!.collectionId)
                Log.d("btnBookMark", "btnBookMark = false")
            } else {
                holder.album?.isBookmarked = true
                AppDatabase.getInstance(act).getAlbumDao().addBookmarked(holder.album!!.collectionId)
                Log.d("btnBookMark", "btnBookMark = true")
            }
            notifyItemChanged(position)
        }

        if (holder.album?.isBookmarked == true) {
            holder.itemAlbumBinding.btnBookMark.background = AppCompatResources.getDrawable(act, R.drawable.ic_bookmark_filled)
        } else {
            holder.itemAlbumBinding.btnBookMark.background = AppCompatResources.getDrawable(act, R.drawable.ic_bookmark_border)
        }

        holder.itemAlbumBinding.txtName.text = holder.album?.collectionName
        holder.itemAlbumBinding.txtArtistName.text = holder.album?.artistName
        holder.itemAlbumBinding.txtPrice.text = "$" + holder.album?.collectionPrice.toString()
        holder.itemAlbumBinding.txtReleaseDate.text =
            simpleDateFormat.format(simpleDateFormat.parse(holder.album?.releaseDate)).toString()

        Glide.with(act)
            .load(holder.album?.artworkUrl100)
            .timeout(10000)
            .into(holder.itemAlbumBinding.imgAlbum)
    }

    override fun getItemCount(): Int {
        Log.d("albumList.size", "albumList.size = ${albumList.size}")
        return albumList.size
    }

    fun updateAlbumList(albumList: List<Album2>) {
        this.albumList = albumList
        Log.d("updateAlbumList.size", "albumList.size = ${albumList.size}")

        notifyDataSetChanged()
    }


}