package com.example.a4_1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.a4_1.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    private var binding: FragmentBookmarkBinding? = null
    private lateinit var adapter: BookmarkAdapter

    private var likedItems: List<SearchItemModel> = listOf()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout? {
        val mainActivity = activity as MainActivity
        likedItems = mainActivity.likedItems

        Log.d("BookmarkFragment", "#park likedItems size = ${likedItmes.size}")

        adapter = BookmarkAdapter(mContext).apply {
            items = likedItems.toMutableList()
        }

        binding = FragmentBookmarkBinding.inflate(inflater, container, false).apply {
            rvBookmark.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            rvBookmark.adapter = adapter
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}