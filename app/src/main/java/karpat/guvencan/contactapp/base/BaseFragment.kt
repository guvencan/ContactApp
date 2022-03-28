package karpat.guvencan.contactapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import karpat.guvencan.contactapp.BR

abstract class BaseFragment<BINDING : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected lateinit var binding: BINDING

    protected abstract val viewModel: VM

    protected val baseActivity by lazy { requireActivity() as BaseActivity<*, *>? }

    var isViewCreated = false

    protected abstract fun createView()

    protected abstract fun observeValues()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, this@BaseFragment.viewModel)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createView()
        if (!isViewCreated) {
            isViewCreated = true
            observeValues()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()
    }
}