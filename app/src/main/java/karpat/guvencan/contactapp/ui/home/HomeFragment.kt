package karpat.guvencan.contactapp.ui.home

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import karpat.guvencan.contactapp.R
import karpat.guvencan.contactapp.base.BaseFragment
import karpat.guvencan.contactapp.data.local.Contact
import karpat.guvencan.contactapp.databinding.HomeFragmentBinding
import karpat.guvencan.contactapp.ui.main.SharedViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override val layoutId = R.layout.home_fragment

    override val viewModel: HomeViewModel by viewModels()

    val sharedViewModel: SharedViewModel by activityViewModels()

    private val contactAdapter by lazy {
        ContactAdapter(itemClickListener).apply {
            withLoadStateFooter(LoaderStateAdapter())
        }
    }

    override fun observeValues() {
        lifecycleScope.launchWhenCreated {
            viewModel.getData().collectLatest {
                contactAdapter.submitData(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            contactAdapter.loadStateFlow.distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }.collect()
        }

    }

    override fun createView() {
        with(binding) {

            recyclerView.apply {
                setHasFixedSize(true)
                adapter = contactAdapter
            }

            floatingAdd.setOnClickListener {
                sharedViewModel.selectedItem = null
                findNavController().navigate(HomeFragmentDirections.toEditFragment())
            }

            search.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    makeSearch()
                    true
                } else {
                    false
                }
            }
            search.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    makeSearch()
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun makeSearch() {
        binding.search.text.toString().let { query ->
            getData(query)
        }
    }

    private fun getData(query: String? = null) {
        lifecycleScope.launchWhenCreated {
            viewModel.getData(query).collectLatest {
                contactAdapter.submitData(it)
            }
        }
    }

    private val itemClickListener = fun(item: Contact?) {
        sharedViewModel.selectedItem = item
        findNavController().navigate(HomeFragmentDirections.toDetailFragment())
    }

}