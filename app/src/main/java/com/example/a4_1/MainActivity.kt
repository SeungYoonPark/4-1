package com.example.a4_1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.a4_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var likedItems: ArrayList<SearchItemModel> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            btnSearchFragment.setOnClickListener {
                setFragment(SearchFragment())
            }
            btnBookmarkFragment.setOnClickListener {
                setFragment(BookmarkFragment())
            }
        }

        setFragment(SearchFragment())
    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frameLayout, frag)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    fun addLikedItem(item: SearchItemModel) {
        if (!likedItems.contains(item)) {
            likedItems.add(item)
        }
    }

    fun removeLikedItem(item: SearchItemModel) {
        likedItems.remove(item)
    }
}