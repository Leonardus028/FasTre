package com.example.fastre.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fastre.R
import com.example.fastre.databinding.ItemHospitalPreviewBinding
import com.example.fastre.core.domain.model.HospitalPhoto

class HospitalPhotoAdapter(private val listData: ArrayList<HospitalPhoto>): RecyclerView.Adapter<HospitalPhotoAdapter.CardViewViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_hospital_preview, parent, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    class CardViewViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemHospitalPreviewBinding.bind(itemView)
        fun bind(data: HospitalPhoto) {

            with(binding) {
                Glide.with(itemView.context)
                    .load(data.photo)
                    .into(ivHospitalPhoto)
            }
        }
    }

}