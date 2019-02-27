package com.patrickiv.demo.enteranimationdemo.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.patrickiv.demo.enteranimationdemo.R
import com.patrickiv.demo.enteranimationdemo.model.AnimationItem
import com.patrickiv.demo.enteranimationdemo.recyclerview.CardAdapter
import com.patrickiv.demo.enteranimationdemo.recyclerview.ItemOffsetDecoration
import java.util.*

abstract class BaseFragment : Fragment(), View.OnClickListener {

    private val mHandler = Handler()
    private var mAnimationItems: Array<AnimationItem>? = null
    private var mSelectedItem: AnimationItem? = null
    private var mRecyclerView: RecyclerView? = null
    private var mSpinner: AppCompatSpinner? = null

    protected abstract val layoutResId: Int

    /**
     * Get the array of animations to choose from
     * @return the array
     */
    protected abstract val animationItems: Array<AnimationItem>

    /**
     * Get the layout manager to use for the demo
     * @param context the context
     * @return the layout manager
     */
    protected abstract fun getLayoutManager(context: Context): RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAnimationItems = animationItems
        mSelectedItem = mAnimationItems!![0]

        mRecyclerView = view.findViewById(R.id.recycler_view)
        mSpinner = view.findViewById(R.id.spinner)
        view.findViewById<View>(R.id.btn_reload).setOnClickListener(this)

        setupRecyclerView()
        setupSpinner()
        runLayoutAnimation(mRecyclerView!!, mSelectedItem!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mHandler.removeCallbacksAndMessages(null)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_reload) {
            runLayoutAnimation(mRecyclerView!!, mSelectedItem!!)
        }
    }

    private fun runLayoutAnimation(recyclerView: RecyclerView, item: AnimationItem) {
        val context = recyclerView.context

        val controller = AnimationUtils.loadLayoutAnimation(context, item.resourceId)

        recyclerView.layoutAnimation = controller
        recyclerView.adapter!!.notifyDataSetChanged()
        recyclerView.scheduleLayoutAnimation()
    }

    private fun setupRecyclerView() {
        val context = mRecyclerView!!.context
        val spacing = resources.getDimensionPixelOffset(R.dimen.default_spacing_small)
        mRecyclerView!!.layoutManager = getLayoutManager(context)
        mRecyclerView!!.adapter = CardAdapter()
        mRecyclerView!!.addItemDecoration(ItemOffsetDecoration(spacing))
    }

    private fun setupSpinner() {
        val itemNames = ArrayList<String>()
        for (item in mAnimationItems!!) {
            itemNames.add(item.name)
        }

        val ctx = mRecyclerView!!.context
        // Apply another theme to make the spinner text the right color
        val themedCtx = ContextThemeWrapper(ctx, R.style.Theme_AppCompat)
        mSpinner!!.adapter = ArrayAdapter(themedCtx, R.layout.row_spinner_item, itemNames)
        mSpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
                mSelectedItem = mAnimationItems!![i]
                runLayoutAnimation(mRecyclerView!!, mSelectedItem!!)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>) {}
        }
    }
}
