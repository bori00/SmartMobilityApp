package com.uid.smartmobilityapp.ui.bookmarks.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.uid.smartmobilityapp.R
import com.uid.smartmobilityapp.ui.bookmarks.model.Bookmark

class BookmarkRecyclerViewViewHolder (private val view: View) : RecyclerView.ViewHolder ( view )
{
    private var bookmarkNameRef: TextView
    private var bookmarkAddressRef: TextView
    private var editBookmarkButtonRef: FloatingActionButton

    init
    {
        bookmarkNameRef = view.findViewById(R.id.bookmarkNameTextView)
        bookmarkAddressRef = view.findViewById(R.id.bookmarkAddressTextView)
        editBookmarkButtonRef = view.findViewById(R.id.editBookmarkFAB)
    }

    fun bindData (bookmark: Bookmark)
    {
        bookmarkNameRef.text = bookmark.name
        bookmarkAddressRef.text = bookmark.address.getAddressLine(0)
    }
}