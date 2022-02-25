package com.example.perfectserve.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.perfectserve.R
import com.example.perfectserve.model.Businesses
import com.squareup.picasso.Picasso

class BusinessListingAdapter(private val context: Context) : RecyclerView.Adapter<BusinessListingAdapter.BusinessItemViewHolder>() {

    private val businessDetailList = ArrayList<Businesses>()

    fun setBusinessList(businessList: ArrayList<Businesses>) {
        this.businessDetailList.addAll(businessList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BusinessItemViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return businessDetailList.size
    }

    override fun onBindViewHolder(holder: BusinessItemViewHolder, position: Int) {
        val model: Businesses = businessDetailList[position]
        holder.bind(model, context)
    }

    class BusinessItemViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.row_business_item, parent, false)) {
        private var businessIv: ImageView = itemView.findViewById(R.id.businessThumbnailIv)
        private var businessTitleTv: TextView = itemView.findViewById(R.id.businessNameTv)
        private var ratingBar: RatingBar = itemView.findViewById(R.id.ratingbar)
        private var phoneNumberTv: TextView = itemView.findViewById(R.id.phoneNumberTv)
        fun bind(business: Businesses, context: Context) {
            businessTitleTv.text = business.name
            ratingBar.rating = business.rating
            phoneNumberTv.text = "Phone: ${business.phone}"
            if (!business.image_url.isNullOrBlank()) {
                Picasso.get().load(business.image_url).into(businessIv)
            }
        }
    }
}

