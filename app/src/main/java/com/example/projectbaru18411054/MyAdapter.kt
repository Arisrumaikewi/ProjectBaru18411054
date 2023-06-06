package com.example.projectbaru18411054

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(var mContext: Context, var sejarahList:List<Image>
): RecyclerView.Adapter<MyAdapter.ListViewHolder>()
{
    inner class ListViewHolder(var v: View): RecyclerView.ViewHolder(v){
        var imageSrc = v.findViewById<ImageView>(R.id.imageSrc)
        var imageTitle = v.findViewById<TextView>(R.id.imageTitle)
        var imageDesc = v.findViewById<TextView>(R.id.imageDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflter = LayoutInflater.from(parent.context)
        val v = inflter.inflate(R.layout.data_item,parent,false)
        return ListViewHolder(v)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val newList = sejarahList[position]
        holder.imageTitle.text = newList.imageTitle
        holder.imageSrc.loadImage(newList.imageSrc)
        holder.imageDesc.text = newList.imageDesc
        holder.v.setOnClickListener {

            val imageSrc = newList.imageSrc
            val imageTitle = newList.imageTitle
            val imageDesc = newList.imageDesc

            /**set Data*/
            val mIntent = Intent(mContext,DetailActivity::class.java)
            mIntent.putExtra("IMAGESRC",imageSrc)
            mIntent.putExtra("IMAGETITLE",imageTitle)
            mIntent.putExtra("IMAGEDESC",imageDesc)
            mContext.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int =  sejarahList.size


}