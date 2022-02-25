package com.example.perfectserve.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.perfectserve.R
import com.example.perfectserve.databinding.FragmentLandingBinding
import com.example.perfectserve.viewmodel.LandingScreenViewModel

class LandingFragment: Fragment() {

    private lateinit var landingScreenViewModel: LandingScreenViewModel
    private var _binding: FragmentLandingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        landingScreenViewModel =
            ViewModelProvider(this)[LandingScreenViewModel::class.java]

        _binding = FragmentLandingBinding.inflate(inflater, container, false)

        val root: View = binding.root
        val navController = findNavController()
        binding.searchButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            landingScreenViewModel.getBusinessListFromNetwork(binding.enterLocationTv.text.toString(), requireContext())
        }

        landingScreenViewModel.businessDetailMutableLiveData?.observe(viewLifecycleOwner, {
            val bundle = bundleOf(KEY_BUSINESS_DETAILS to it)
            navController.navigate(R.id.action_landingFragment_to_businessListingFragment, bundle)
            binding.progressBar.visibility = View.VISIBLE
        })

        landingScreenViewModel.errorMessageCode.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.VISIBLE
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val TAG = LandingFragment::class.java.canonicalName
        const val KEY_BUSINESS_DETAILS = "businessDetail"
    }
}