package com.samiu.host

import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.samiu.base.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Samiu 2020/3/2
 */
class MainActivity : BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_main
    override fun initData() = Unit

    override fun initView() {
        val navController = findNavController(R.id.nav_host_fragment)
        bottom_nav.setupWithNavController(navController)
    }

    private var y1 = 0f
    private var y2 = 0f

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            y1 = event.rawY
        }
        if (event?.action == MotionEvent.ACTION_UP) {
            y2 = event.rawY
            if (y2 - y1 > 100)
                showBottomNav(true)
            else if (y1 - y2 > 100)
                showBottomNav(false)
        }
        return super.dispatchTouchEvent(event)
    }

    private fun showBottomNav(show: Boolean) {
        if (show && !bottom_nav.isShown) {        //show
            val inAnim = AnimationUtils.loadAnimation(this, R.anim.in_bottom)
            bottom_nav.startAnimation(inAnim)
            inAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) = Unit
                override fun onAnimationEnd(animation: Animation?) = Unit
                override fun onAnimationStart(animation: Animation?) {
                    bottom_nav.visibility = View.VISIBLE
                }
            })
        } else if (!show && bottom_nav.isShown) {  //hide
            val outAnim = AnimationUtils.loadAnimation(this, R.anim.out_bottom)
            bottom_nav.startAnimation(outAnim)
            bottom_nav.visibility = View.GONE
        }
    }
}
