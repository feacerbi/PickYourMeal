package com.felipeacerbi.pickyourmeal.view

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.felipeacerbi.pickyourmeal.databinding.RecipeListItemBinding
import com.felipeacerbi.pickyourmeal.repository.model.Recipe
import com.felipeacerbi.pickyourmeal.util.inflater

class RecipesAdapter : ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipeDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder(
            RecipeListItemBinding.inflate(parent.inflater(), parent, false)
        )

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class RecipeViewHolder(
        private val binding: RecipeListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            position: Int
        ) = with (binding) {
            val recipe = getItem(position)

            recipeTitle.text = recipe.title
            recipeHeadline.text = recipe.headline

            recipePicture.load(recipe.picture)
        }
    }

    private class RecipeDiffUtil : DiffUtil.ItemCallback<Recipe>() {

        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
            oldItem == newItem
    }
}