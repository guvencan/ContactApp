package karpat.guvencan.contactapp.ui.detail

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import karpat.guvencan.contactapp.R
import karpat.guvencan.contactapp.base.BaseFragment
import karpat.guvencan.contactapp.databinding.DetailFragmentBinding
import karpat.guvencan.contactapp.ui.home.HomeFragmentDirections
import karpat.guvencan.contactapp.ui.main.SharedViewModel

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailFragmentBinding, SharedViewModel>() {

    override val layoutId = R.layout.detail_fragment

    override val viewModel: SharedViewModel by activityViewModels()

    override fun createView() {
        binding.floatingEdit.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.toEditFragment())
        }
        binding.floatingDelete.setOnClickListener {
            viewModel.delete()
            findNavController().navigate(DetailFragmentDirections.toHomeFragment())
        }
    }

    override fun observeValues() {}

}