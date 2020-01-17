package com.tina.midterm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.tina.midterm.databinding.ContentMainBinding
import com.tina.midterm.viewModel.MainViewModel
import java.util.*

class ContentMainFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ContentMainBinding = DataBindingUtil.inflate(
            inflater, R.layout.content_main, container, false
        )


        return binding.root
    }


}




