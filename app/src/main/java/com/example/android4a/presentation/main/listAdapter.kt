package com.example.android4a.presentation.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.android4a.R
import com.example.android4a.presentation.model.FinalFantasy
import com.squareup.picasso.Picasso


class listAdapter     // Provide a suitable constructor (depends on the kind of dataset)
    (private val values: MutableList<FinalFantasy>) :
    RecyclerView.Adapter<listAdapter.ViewHolder>() {
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        // each data item is just a string in this case
        var txtHeader: TextView
        var txtFooter: TextView
        var layout: View

        init {
            layout = v
            txtHeader = v.findViewById<TextView>(R.id.firstLine)
            txtFooter = v.findViewById<TextView>(R.id.secondLine)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(
            parent.context
        )
        val v: View = inflater.inflate(R.layout.row_layout, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val currentFinalFantasy : FinalFantasy = values[position]
        val myImageView : ImageView = holder.itemView.findViewById(R.id.icon)

        Picasso.get()
            .load(currentFinalFantasy.ImageUrl)
            .resize(200,200)
            .centerCrop()
            .into(myImageView)

        holder.txtHeader.text = currentFinalFantasy.name
        holder.txtFooter.text = currentFinalFantasy.NumeroDeTop
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return values.size
    }
}