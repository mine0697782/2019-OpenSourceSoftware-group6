package com.example.recipelab.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.recipelab.R

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)

        val controller = findNavController()

        val button: Button = root.findViewById(R.id.btn_gallery)//btn_recipe_add_research_tamplet
        button.setOnClickListener {
            controller.navigate(R.id.nav_home)
        }

//        galleryViewModel =
//            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
//            galleryViewModel.text.observe(this, Observer {
//                textView.text = it
//        })

        return root
    }
}