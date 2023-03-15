package com.example.mytestapp.presentation.feature.main.winget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.mytestapp.R
import kotlinx.android.synthetic.main.layout_bottom_menu.view.*

class BottomMenu @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var bottomMenuClickListener: BottomMenuListener

    private val activeColor = ContextCompat.getColor(context, R.color.cool_red)
    private val inActiveColor = ContextCompat.getColor(context, R.color.cool_white)

    private var pageSelected: BottomMenuHomePages = BottomMenuHomePages.HOME

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.layout_bottom_menu, this, true)

        bindUI()
    }

    fun setMenuClickListener(listener: BottomMenuListener?) {
        listener?.let {
            this.bottomMenuClickListener = it
        }
    }

    fun setViewSelected(page: BottomMenuHomePages?) {
        page?.let {
            setMenuActive(it)
        }
    }


    private fun bindUI() {
        //First active
        setMenuActive(pageSelected)

        btnBottomMenuHome.setOnClickListener {
            setMenuActive(BottomMenuHomePages.HOME)
            onBottomMenuClickListener(BottomMenuHomePages.HOME)
        }

        btnBottomMenuDetail.setOnClickListener {
            setMenuActive(BottomMenuHomePages.DETAIL)
            onBottomMenuClickListener(BottomMenuHomePages.DETAIL)
        }

        btnBottomMenuPoint.setOnClickListener {
            setMenuActive(BottomMenuHomePages.POINT)
            onBottomMenuClickListener(BottomMenuHomePages.POINT)
        }

    }

    private fun setMenuActive(page: BottomMenuHomePages) {
        pageSelected = page
        when (page) {
            BottomMenuHomePages.HOME -> {
                onDetailClick(false)
                onHomeClick(true)
                onPointClick(false)
            }
            BottomMenuHomePages.DETAIL -> {
                onDetailClick(true)
                onHomeClick(false)
                onPointClick(false)
            }
            BottomMenuHomePages.POINT -> {
                onDetailClick(false)
                onHomeClick(false)
                onPointClick(true)
            }
        }
    }

    private fun onBottomMenuClickListener(page: BottomMenuHomePages) {
        if (this::bottomMenuClickListener.isInitialized) {
            bottomMenuClickListener.onBottomMenuClick(page)
        }
    }

    private fun onDetailClick(isActive: Boolean) {
        ivBottomMenuDetail.setColorFilter(getColor(isActive))
        tvBottomMenuDetail.setTextColor(getColor(isActive))
        btnBottomMenuDetail.isClickable = !isActive
    }

    private fun onPointClick(isActive: Boolean) {
        tvBottomMenuPoint.setTextColor(getColor(isActive))
        btnBottomMenuPoint.isClickable = !isActive
    }

    private fun onHomeClick(isActive: Boolean) {
        ivBottomMenuHome.setColorFilter(getColor(isActive))
        tvBottomMenuHome.setTextColor(getColor(isActive))
        btnBottomMenuHome.isClickable = !isActive
    }

    private fun getColor(isActive: Boolean): Int {
        return if (isActive) activeColor else inActiveColor
    }

    enum class BottomMenuHomePages {
        HOME, DETAIL, POINT
    }

    interface BottomMenuListener {
        fun onBottomMenuClick(page: BottomMenuHomePages)
    }

}