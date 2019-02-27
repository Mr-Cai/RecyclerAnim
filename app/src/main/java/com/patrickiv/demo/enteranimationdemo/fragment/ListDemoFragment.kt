package com.patrickiv.demo.enteranimationdemo.fragment

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.patrickiv.demo.enteranimationdemo.R
import com.patrickiv.demo.enteranimationdemo.model.AnimationItem

class ListDemoFragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.frag_list

    override val animationItems: Array<AnimationItem>
        get() = arrayOf(
                AnimationItem("Fall down", R.anim.layout_animation_fall_down),
                AnimationItem("Slide from right", R.anim.layout_animation_from_right),
                AnimationItem("Slide from bottom", R.anim.layout_animation_from_bottom)
        )

    override fun getLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }
}
