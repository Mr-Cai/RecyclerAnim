package com.patrickiv.demo.enteranimationdemo

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.patrickiv.demo.enteranimationdemo.fragment.GridDemoFragment
import com.patrickiv.demo.enteranimationdemo.fragment.ListDemoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mButtonContainer: ViewGroup? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mButtonContainer = findViewById(R.id.button_container)
        displayListBTN.setOnClickListener(this)
        displayGridBTN.setOnClickListener(this)
        if (savedInstanceState != null) {
            val count = supportFragmentManager.backStackEntryCount
            mButtonContainer!!.alpha = if (count > 0) 0f else 1f
        }
    }
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            mButtonContainer!!.animate().alpha(1f).start()
        }
        super.onBackPressed()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.displayListBTN -> showFragment(ListDemoFragment())
            R.id.displayGridBTN -> showFragment(GridDemoFragment())
        }
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        mButtonContainer!!.animate().alpha(0f).start()
    }
}
