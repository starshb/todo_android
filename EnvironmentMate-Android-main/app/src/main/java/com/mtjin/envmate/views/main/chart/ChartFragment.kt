package com.mtjin.envmate.views.main.chart

import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.mtjin.envmate.R
import com.mtjin.envmate.base.BaseFragment
import com.mtjin.envmate.databinding.FragmentChartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartFragment :
    BaseFragment<FragmentChartBinding>(R.layout.fragment_chart) {

    private val viewModel: ChartViewModel by viewModels()
    override fun init() {
        binding.vm = viewModel
        initView()
        initAdapter()
        initViewModelCallback()
    }

    private fun initAdapter() {
        val alarmAdapter = ChartMissionAdapter(onItemClick = {
            showToast(it.longMission)
        })
        binding.chartRvMissions.adapter = alarmAdapter
    }

    private fun initViewModelCallback() {
        with(viewModel) {
            goPhotoZoom.observe(this@ChartFragment, Observer {
                findNavController().navigate(
                    ChartFragmentDirections.actionChartFragmentToPhotoZoomFragment(
                        viewModel.imageUrl
                    )
                )
            })

            compareRegionResult.observe(this@ChartFragment, Observer {
                Glide.with(this@ChartFragment).load(it.mediaUrl).thumbnail(0.1f)
                    .into(binding.chartIvChart)
                binding.run {
                    chartTvComment.text = it.result
                    chartLayoutComment.visibility = View.VISIBLE
                    chartLayoutMission.visibility = View.GONE
                }
            })

            compareSameRegionResult.observe(this@ChartFragment, Observer {
                Glide.with(this@ChartFragment).load(it.mediaUrl).thumbnail(0.1f)
                    .into(binding.chartIvChart)
                binding.run {
                    chartTvComment.text = it.result
                    chartLayoutComment.visibility = View.VISIBLE
                    chartLayoutMission.visibility = View.GONE
                }
            })

            compareIndustryAllEnvResult.observe(this@ChartFragment, Observer {
                Glide.with(this@ChartFragment).load(it.mediaUrl).thumbnail(0.1f)
                    .into(binding.chartIvChart)
                binding.run {
                    chartTvComment.text = it.result
                    chartLayoutComment.visibility = View.VISIBLE
                    chartLayoutMission.visibility = View.GONE
                }
            })

            compareIndustrySameAllResult.observe(this@ChartFragment, Observer {
                Glide.with(this@ChartFragment).load(it.mediaUrl).thumbnail(0.1f)
                    .into(binding.chartIvChart)
                binding.run {
                    chartTvComment.text = it.result
                    chartLayoutComment.visibility = View.VISIBLE
                    chartLayoutMission.visibility = View.GONE
                }
            })

            detailIndustryEnergyResult.observe(this@ChartFragment, Observer {
                Glide.with(this@ChartFragment).load(it.mediaUrl).thumbnail(0.1f)
                    .into(binding.chartIvChart)
            })
        }
    }

    private fun initView() {
        binding.chartSpType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.chartTvChartAnalysis.text =
                    binding.chartSpType.selectedItem.toString()
                when (position) {
                    0 -> {
                    }
                    1 -> {
                        viewModel.requestCompareRegion()
                    }
                    2 -> {
                        viewModel.requestCompareSameRegion()
                    }
                    3 -> {
                        viewModel.requestCompareIndustryAllEnv()
                    }
                    4 -> {
                        viewModel.requestCompareIndustrySameAll()
                    }
                    5 -> {
                        binding.run {
                            chartLayoutComment.visibility = View.GONE
                            chartLayoutMission.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}