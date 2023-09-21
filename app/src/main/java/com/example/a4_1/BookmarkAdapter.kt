package com.example.a4_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.a4_1.databinding.SearchItemBinding

class BookmarkAdapter(var mContext: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items = mutableListOf<SearchItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolderBinding(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Glide.with(mContext)
            .load(items[position], Url)
            .into(holder as ItemViewHolder).iv_thum_img

        holder.tv_title.text = items[position].title
        holder.iv_like.visibility = View.GONE
        holder.tv_datatime.text =
            getDateFromTimestampWithFormat(
                items[position].dataTime,
                "yyyy-MM=dd'T'HH:mm:ss.SSS+09:00",
                "yyyy-MM-dd HH-mm-ss"
            )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var iv_thum_img: ImageView = binding.ivThumImg
        var iv_like: ImageView = binding.ivLike
        var tv_title: TextView = binding.tvTitle
        var tv_datetime: TextView = binding.tvDatetime

        init {
            iv_like.visibility = View.GONE

            cl_item.setOnClickListener {

                val Position = adapterPosition
                (mContext as MainActivity).removeLikedItem(items[position])
                if (position != RecyclerView.NO_POSITION) {
                    items.removeAt(position)
                    notifyItemRemoved(position)

                }

            }
        }
    }
}