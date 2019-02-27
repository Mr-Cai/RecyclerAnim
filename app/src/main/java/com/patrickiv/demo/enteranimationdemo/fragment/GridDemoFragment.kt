package com.patrickiv.demo.enteranimationdemo.fragment

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.patrickiv.demo.enteranimationdemo.R
import com.patrickiv.demo.enteranimationdemo.model.AnimationItem

class GridDemoFragment : BaseFragment() {

    override val layoutResId: Int
        get() = R.layout.frag_grid

    override val animationItems: Array<AnimationItem>
        get() = arrayOf(
                AnimationItem("Slide from bottom", R.anim.grid_layout_animation_from_bottom),
                AnimationItem("Scale", R.anim.grid_layout_animation_scale),
                AnimationItem("Scale random", R.anim.grid_layout_animation_scale_random)
        )

    override fun getLayoutManager(context: Context): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 4)
    }
}
