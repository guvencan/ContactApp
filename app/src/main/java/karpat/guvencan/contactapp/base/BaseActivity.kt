package karpat.guvencan.contactapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import karpat.guvencan.contactapp.R

abstract class BaseActivity<BINDING : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected lateinit var binding: BINDING

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.apply {
            lifecycleOwner = this@BaseActivity
        }
    }


    fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this).apply {
            setTitle(getString(R.string.title))
            setMessage(message)
        }.show()
    }

}