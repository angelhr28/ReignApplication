package com.example.reignapplication.ui.view.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.reignapplication.databinding.ActivityMainBinding
import com.example.reignapplication.ui.view.adapter.HitAdapter
import com.example.reignapplication.ui.viewmodel.HitViewModel
import com.example.reignapplication.utils.SwipeHelper
import com.example.reignapplication.utils.isConnected
import com.example.reignapplication.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var hitAdapter: HitAdapter
    private val hitViewModel: HitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setUpSwipeRefresh()
        initEvent()
        bindingObservers()
    }

    private fun setUpSwipeRefresh() {
        binding.swipeRefresh.apply {
            setOnRefreshListener(this@MainActivity)
        }
    }

    private fun initEvent() {
        hitViewModel.onCreate()
    }

    private fun bindingObservers() {

        hitViewModel.hitModel.observe(this, hitAdapter::setItems)

        hitViewModel.isProgress.observe(this) { binding.progress.isVisible = it }

        hitViewModel.snackbar.observe(this) { toast(it, this@MainActivity) }

        hitViewModel.isRefresh.observe(this) { binding.swipeRefresh.isRefreshing = it }
    }

    private fun setupRecyclerView() {
        hitAdapter = HitAdapter() {
            toDetail(it)
        }
        binding.rvItems.apply {
            adapter = hitAdapter
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@MainActivity)

            val itemTouchHelper = ItemTouchHelper(object : SwipeHelper(this) {
                override fun instantiateUnderlayButton(position: Int): List<UnderlayButton> {

                    return listOf(deleteButton(position))
                }
            })

            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    private fun toDetail(url: String) {
        val intent = Intent(this, DetailHitActivity::class.java)
        intent.apply {
            putExtra("url", url)
            startActivity(this)
        }

    }

    private fun deleteButton(position: Int): SwipeHelper.UnderlayButton {
        return SwipeHelper.UnderlayButton(
            this,
            "ELIMINAR",
            14.0f,
            android.R.color.holo_red_light,
            object : SwipeHelper.UnderlayButtonClickListener {
                override fun onClick() {
                    val hit = hitAdapter.getItem(position)
                    hitAdapter.delete(position)
                    hitViewModel.deleteHit(hit)
                }
            })
    }

    override fun onRefresh() {
        hitViewModel.refreshHit(isConnected(this))
    }

}