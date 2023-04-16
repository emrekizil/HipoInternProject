package com.example.hipointernproject.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.hipointernproject.databinding.FragmentHomeBinding
import com.example.hipointernproject.utility.observeTextChanges
import com.example.hipointernproject.utility.okWith
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding

    private val viewModel by viewModels<HomeViewModel>()

    private val adapter = MemberRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater).apply {
            memberRecyclerView.adapter = adapter
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUiState()
        observeTextChanges()
        setUi()
    }

    private fun observeUiState() {
        viewModel.getAllMembers()
        viewModel.memberHomeUiState.observe(viewLifecycleOwner){
            when(it){
                is HomeUiState.Success->{
                    handleSuccessUiData(it.data)
                }
                is HomeUiState.Error->{
                    Toast.makeText(requireContext(), getString(it.message), Toast.LENGTH_SHORT)
                        .show()
                }
                is HomeUiState.Loading->{
                    Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun handleSuccessUiData(data: List<HomeUiData>) {
        adapter.updateItems(data)
    }
    private fun observeTextChanges(){
        binding.editTextView.observeTextChanges()
            .filter { it okWith MINIMUM_SEARCH_LENGTH }
            .debounce(SEARCH_DEBOUNCE_TIME_IN_MILLISECONDS)
            .distinctUntilChanged()
            .onEach{
               viewModel.filterMembers(it)
            }.launchIn(lifecycleScope)
    }

    private fun setUi(){
        binding.button.setOnClickListener {
            viewModel.addMember(21,
                "emrekizil",
                "Intern",
                0, //I hope will be 1 :)
                "Istanbul",
                "Emre Kızıl")
        }
    }
    companion object {
        private const val MINIMUM_SEARCH_LENGTH = -1
        private const val SEARCH_DEBOUNCE_TIME_IN_MILLISECONDS = 200L
    }
}