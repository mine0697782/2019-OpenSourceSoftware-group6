package com.example.recipelab.ui.recipeAdd

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.recipelab.R


class AddResearchTampletFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.content_add_research_tamplet, container, false)
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