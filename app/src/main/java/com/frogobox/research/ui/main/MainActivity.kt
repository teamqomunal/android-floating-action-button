package com.frogobox.research.ui.main

import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import com.frogobox.research.R
import com.frogobox.research.common.base.BaseBindActivity
import com.frogobox.research.common.delegate.PreferenceDelegates
import com.frogobox.research.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseBindActivity<ActivityMainBinding>() {


    private var isFABOpen = false
    private var fab_open: Animation? = null
    private var fab_close: Animation? = null
    private var rotate_forward: Animation? = null
    private var rotate_backward: Animation? = null

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var preferenceDelegates: PreferenceDelegates

    override fun initBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            // Call View Model Here
            viewModel.getData()
            Log.d(TAG, "View Model : ${viewModel::class.java.simpleName}")
        }
        Log.d("SampleDelegates Output", getTagMainDelegate())
        // TODO : Add your code here


    }

    override fun initView() {
        super.initView()
        binding.apply {

            fab_open = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_open)
            fab_close = AnimationUtils.loadAnimation(applicationContext, R.anim.fab_close)
            rotate_forward = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_forward)
            rotate_backward = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate_backward)

            fab.setOnClickListener { animateFAB() }

        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.apply {

        }
    }

    fun animateFAB() {
        if (isFABOpen) {
            binding.fab.startAnimation(rotate_backward)
            binding.fab1.startAnimation(fab_close)
            binding.fab1.isClickable = false
            isFABOpen = false
        } else {
            binding.fab.startAnimation(rotate_forward)
            binding.fab1.startAnimation(fab_open)
            binding.fab1.isClickable = true
            isFABOpen = true
        }
    }

}