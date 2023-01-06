package com.uid.smartmobilityapp.ui.logout

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.uid.smartmobilityapp.MainActivity
import com.uid.smartmobilityapp.databinding.FragmentLogoutBinding


class LogoutFragment : Fragment() {

    private var _binding: FragmentLogoutBinding? = null
    lateinit private var _root : View;

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLogoutBinding.inflate(inflater, container, false)
        _root = binding.root

        val intent = Intent(activity, MainActivity::class.java)
        startActivity(intent)

        return _root
    }
}