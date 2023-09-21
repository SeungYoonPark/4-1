package com.example.a4_1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.a4_1.Utils.getDataFromTimestampWithFormat
import com.example.a4_1.databinding.SearchItemBinding
import com.google.android.ads.mediationtestsuite.viewmodels.ItemViewHolder

class SearchAdapter(private val mContext: Context) :
    RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {
    var items = ArrayList<SearchItemModel>()

    fun clearItme() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currntItem = items[position]

        Glide.with(mContext)
            .load(currntItem.uri)
            .into(holder.iv_thum_img)

        holder.iv_like.visibility = if (currntItem.isLike) View.VISIBLE else View.INVISIBLE
        holder.tv_title.text = currntItem.title
        holder.tv_datetime.text = getDataFromTimestampWithFormat(
            currntItem.dataTime,
            "yyyy-MM-dd'T'HH:mm:ss.SSS+09:00",
            "yyyy-MM-dd HH:mm:ss"
        )
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var iv_thum_img: ImageView = binding.ivThumImg
        var iv_like: ImageView = binding.ivLike
        var tv_title: TextView = binding.tvTitle
        var tv_datetime: TextView = binding.tvDatetime
        var cl_thumb_item: ConstraintLayout = binding.clThumItem

        init {
            iv_like.visibility = View.GONE
            iv_thum_img.setOnClickListener(this)
            cl_thumb_item.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val item = items[position]

            items.isLike = !item.isLike

            if (item.isLike) {
                (mContext as MainActivity).addLikedItem(item)
            } else {
                (mContext as MainActivity).removeLikedItem(item)
            }

            notifyDataSetChanged(position)

        }
    }

}


}