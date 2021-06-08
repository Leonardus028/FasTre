package com.example.fastre.core.ui

import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.fastre.R
import com.example.fastre.core.domain.model.Poly
import com.example.fastre.databinding.ItemPolyBinding
import com.example.fastre.ui.queue.QueueActivity
import java.util.*

class PolyAdapter: RecyclerView.Adapter<PolyAdapter.ListViewHolder>() {

    private var listData = ArrayList<Poly>()
    var onItemClick: ((Poly) -> Unit)? = null

    fun setData(newListData: List<Poly>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_poly, parent, false))

    override fun getItemCount(): Int {
        return listData.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPolyBinding.bind(itemView)
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: Poly) {
            with(binding) {
                tvPolyName.text = data.polyName
                tvQueueNumber.text = data.currentNumber.toString()

                btnGetUserNumber.setOnClickListener {
                    val intent = Intent(itemView.context, QueueActivity::class.java)
                    intent.putExtra(QueueActivity.EXTRA_QUEUE_DATA, data)
                    itemView.context.startActivity(intent)
                }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

}