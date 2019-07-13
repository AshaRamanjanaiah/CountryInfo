package com.example.android.countryinfo

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.countryinfo.model.Details
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_detail_item.view.*

class CanadaInfoAdapter(val context: Context, val details: List<Details>?): RecyclerView.Adapter<CanadaInfoAdapter.MyViewHolder>(){

    private val IMAGE_URL = "image_href"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_detail_item, parent, false )
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
            return details?.size ?: 0
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val canadaInfo = this.details?.get(position)
        holder.bind(canadaInfo)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(canadaInfo: Details?) {
            itemView.tv_title.text = canadaInfo?.title
            itemView.tv_description.text = canadaInfo?.description

            Picasso.get()
                .load(canadaInfo?.imageHref)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(itemView.imageview_place)

            itemView.setOnClickListener { clickListener(canadaInfo?.imageHref)}
        }

        fun clickListener(imageHref: String?){
            val intent = Intent(context, DisplayImageActivity::class.java)
            intent.putExtra(IMAGE_URL, imageHref)
            context.startActivity(intent)
        }

    }
}