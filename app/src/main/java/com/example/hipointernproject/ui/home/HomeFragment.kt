package com.example.hipointernproject.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hipointernproject.R
import com.example.hipointernproject.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

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
        viewModel.getAllMembers()
        observeUiState()
    }

    private fun observeUiState() {
        viewModel.memberHomeUiState.observe(viewLifecycleOwner){
            when(it){
                is HomeUiState.Success->{
                    handleSuccessUiData(it.data)
                }
                is HomeUiState.Error->{

                }
                is HomeUiState.Loading->{

                }
            }
        }
    }

    private fun handleSuccessUiData(data: List<HomeUiData>) {
        adapter.updateItems(data)
    }
}