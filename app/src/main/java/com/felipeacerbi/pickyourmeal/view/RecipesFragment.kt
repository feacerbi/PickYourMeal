package com.felipeacerbi.pickyourmeal.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.felipeacerbi.pickyourmeal.R
import com.felipeacerbi.pickyourmeal.databinding.FragmentRecipesBinding
import com.felipeacerbi.pickyourmeal.util.observe
import com.felipeacerbi.pickyourmeal.viewmodel.RecipesViewModel
import com.felipeacerbi.pickyourmeal.viewmodel.RecipesViewModel.Action
import com.felipeacerbi.pickyourmeal.viewmodel.RecipesViewModel.Action.ForceRefresh
import com.felipeacerbi.pickyourmeal.viewstate.RecipesViewEffect.ErrorMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding

    private val viewModel: RecipesViewModel by viewModels()

    private val recipesAdapter = RecipesAdapter()
    private val dateAdapter = DateAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRecipesBinding.inflate(inflater, container, false).apply {
        binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        bindViews()
    }

    private fun setupViews() = with (binding) {
        list.adapter = ConcatAdapter(dateAdapter, recipesAdapter)
        progress.setColorSchemeResources(R.color.colorPrimary)
        progress.setOnRefreshListener {
            perform(ForceRefresh)
        }
    }

    private fun bindViews() = with (binding) {
        observe(viewModel.getStateStream()) {
            recipesAdapter.submitList(it.recipes)
            dateAdapter.update(it.currentDate)
            progress.isRefreshing = it.loading
            empty.isVisible = it.showEmpty
        }

        observe(viewModel.getEffectStream()) {
            when (it) {
                is ErrorMessage -> showMessage(it.error)
            }
        }
    }

    private fun showMessage(message: Int) {
        Toast.makeText(requireContext(), getString(message), Toast.LENGTH_SHORT).show()
    }

    private fun perform(action: Action) {
        viewModel.perform(action)
    }
}