package com.example.perfectserve.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perfectserve.adapter.BusinessListingAdapter
import com.example.perfectserve.databinding.FragmentBusinessListingBinding
import com.example.perfectserve.model.Businesses

class BusinessListingFragment: Fragment() {

    private var _binding: FragmentBusinessListingBinding? = null
    private lateinit var businessListingAdapter: BusinessListingAdapter
    private lateinit var recyclerView: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBusinessListingBinding.inflate(inflater, container, false)
        var businessDetail = LandingFragmentArgs.fromBundle(requireArguments()).businessDetail
        recyclerView = binding.businessRecyclerView
        setRecyclerView(businessDetail.businesses)
        return binding.root
    }

    private fun setRecyclerView(businessList: ArrayList<Businesses>) {
        businessListingAdapter = BusinessListingAdapter(requireContext())
        val categoryLinearLayoutManager = LinearLayoutManager(activity)
        categoryLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = categoryLinearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            categoryLinearLayoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = businessListingAdapter
        businessListingAdapter.setBusinessList(businessList)
    }
}