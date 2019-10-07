package com.example.recipelab.ui.recipeAdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipelab.R
import com.example.recipelab.ui.gallery.GalleryViewModel
import kotlinx.android.synthetic.main.fragment_recipe_add_research_tamplet.*


class AddResearchTampletFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_recipe_add_research_tamplet, container, false)
        val textView: TextView = root.findViewById(R.id.text_recipe_add_research_tamplet)

        val controller = findNavController()

        val button: Button = root.findViewById(R.id.btn_recipe_add_research_tamplet)//btn_recipe_add_research_tamplet
        button.setOnClickListener {
            controller.navigate(R.id.nav_2)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}