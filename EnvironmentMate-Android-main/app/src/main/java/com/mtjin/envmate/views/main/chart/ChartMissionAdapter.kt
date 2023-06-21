package com.mtjin.envmate.views.main.chart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mtjin.envmate.R
import com.mtjin.envmate.data.model.response.IndustryEnergy
import com.mtjin.envmate.databinding.ItemChartMissionBinding

class ChartMissionAdapter(private val onItemClick: (IndustryEnergy) -> Unit) :
    RecyclerView.Adapter<ChartMissionAdapter.ViewHolder>() {
    private val items: ArrayList<IndustryEnergy> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemChartMissionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_chart_mission,
            parent,
            false
        )
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener {
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                onItemClick(items[position])
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        items[position].let {
            holder.bind(it)
        }
    }

    class ViewHolder(private val binding: ItemChartMissionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IndustryEnergy) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    fun addItems(items: List<IndustryEnergy>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: IndustryEnergy) {
        this.items.add(item)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }
}