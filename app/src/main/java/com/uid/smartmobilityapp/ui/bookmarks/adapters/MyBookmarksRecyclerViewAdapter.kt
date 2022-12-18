package com.uid.smartmobilityapp.ui.bookmarks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.bookmarks.model.Bookmark

class MyBookmarksRecyclerViewAdapter (
    private val context: Context,
    private val dataSource: List<Bookmark>) : RecyclerView.Adapter<BookmarkRecyclerViewViewHolder>()
{
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int ): BookmarkRecyclerViewViewHolder
    {
        val view = inflater.inflate ( viewType, parent, false )
        return BookmarkRecyclerViewViewHolder(view)
    }

    override fun onBindViewHolder ( holder: BookmarkRecyclerViewViewHolder, position: Int )
    {
        holder.bindData ( dataSource.get(position) )
    }

    override fun getItemViewType ( position: Int ) : Int
    {
        return R.layout.bookmark_item
    }

    override fun getItemCount(): Int
    {
        return dataSource.size
    }
}