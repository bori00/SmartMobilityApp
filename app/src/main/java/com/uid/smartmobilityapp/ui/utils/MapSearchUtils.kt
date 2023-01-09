package com.uid.smartmobilityapp.ui.utils

import android.R
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.database.Cursor
import android.database.MatrixCursor
import android.location.Address
import android.location.Geocoder
import android.provider.BaseColumns
import android.view.View
import android.widget.CursorAdapter
import android.widget.SearchView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import com.google.android.material.internal.ViewUtils
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.UserActivity
import com.uid.smartmobilityapp.models.AddressWithName
import com.uid.smartmobilityapp.ui.bookmarks.model.Bookmark
import com.uid.smartmobilityapp.ui.bookmarks.model.MyBookmarks
import java.io.IOException
import java.util.function.Consumer
import java.util.stream.Collectors

class MapSearchUtils {

    fun setupMapSearchWithBookmarkSuggestions(searchView : SearchView,
                                      context : Context,
                                      view : View,
                                      onAddressSelected : Consumer<AddressWithName>,
                                      onSuggestionSelected : Consumer<Bookmark>) {
        // inspired by https://www.geeksforgeeks.org/how-to-add-searchview-in-google-maps-in-android/
        searchView.onActionViewExpanded()

        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(android.R.id.text1)
        val cursorAdapter = SimpleCursorAdapter(context, R.layout.simple_list_item_1, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)
        val availableBookmarkNames = MyBookmarks.bookmarks.stream().map { bookmark -> bookmark.name }.collect(
            Collectors.toList())

        searchView.suggestionsAdapter = cursorAdapter
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val location = searchView.query.toString()
                var addressList: List<Address>? = null
                if (location.isNotEmpty()) {
                    val geocoder = Geocoder(UserActivity.context)
                    try {
                        addressList = geocoder.getFromLocationName(location, 1)
                        if (!addressList.isEmpty()) {
                            // Address successfully selected
                            val selectedAddressWithName = AddressWithName(addressList[0], addressList[0].getAddressLine(0))
                            onAddressSelected.accept(selectedAddressWithName)
                        } else {
                            Toast.makeText(UserActivity.context, "This location couldn't be found. Please make your query more specific", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: IOException) {
                        Toast.makeText(UserActivity.context, "This location couldn't be found. Please make your query more specific", Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                    }
                } else {
                    Toast.makeText(UserActivity.context, "Please select an address", Toast.LENGTH_SHORT).show()
                }
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val cursor = MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))

                query?.let {
                    availableBookmarkNames.forEachIndexed { index, suggestion ->
                        if (suggestion.contains(query, true)) {
                            cursor.addRow(arrayOf(index, suggestion))
                        }
                    }
                }

                cursorAdapter.changeCursor(cursor)
                cursorAdapter.notifyDataSetChanged()
                return true
            }
        })

        searchView.setOnSuggestionListener(object: SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            @SuppressLint("RestrictedApi", "Range")
            override fun onSuggestionClick(position: Int): Boolean {
                ViewUtils.hideKeyboard(view)
                val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor
                val selection = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                searchView.setQuery(selection, false)

                val selectedBookmark = MyBookmarks.bookmarks.stream().filter({ bookmark -> bookmark.name.equals(selection)}).collect(
                    Collectors.toList()).get(0)

                onSuggestionSelected.accept(selectedBookmark)

                return true
            }
        })
    }

    fun setupMapSearchWithNoBookmarkSuggestions(searchView : SearchView,
                                              onAddressSelected : Consumer<AddressWithName>) {
        // inspired by https://www.geeksforgeeks.org/how-to-add-searchview-in-google-maps-in-android/
        searchView.onActionViewExpanded()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val location = searchView.query.toString()
                var addressList: List<Address>? = null
                if (location.isNotEmpty()) {
                    val geocoder = Geocoder(UserActivity.context)
                    try {
                        addressList = geocoder.getFromLocationName(location, 1)
                        if (!addressList.isEmpty()) {
                            // Address successfully selected
                            val selectedAddressWithName = AddressWithName(addressList[0], addressList[0].getAddressLine(0))
                            onAddressSelected.accept(selectedAddressWithName)
                        } else {
                            Toast.makeText(UserActivity.context, "This location couldn't be found. Please make your query more specific", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: IOException) {
                        Toast.makeText(UserActivity.context, "This location couldn't be found. Please make your query more specific", Toast.LENGTH_SHORT).show()
                        e.printStackTrace()
                    }
                } else {
                    Toast.makeText(UserActivity.context, "Please select an address", Toast.LENGTH_SHORT).show()
                }
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
    }
}