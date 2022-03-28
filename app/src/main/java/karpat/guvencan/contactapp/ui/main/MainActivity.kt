package karpat.guvencan.contactapp.ui.main

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import karpat.guvencan.contactapp.R
import karpat.guvencan.contactapp.base.BaseActivity
import karpat.guvencan.contactapp.databinding.MainActivityBinding

//Reference: https://developer.android.com/topic/libraries/architecture/paging/v3-network-db

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding, SharedViewModel>() {

    override val layoutId: Int = R.layout.main_activity

    override val viewModel: SharedViewModel by viewModels()

}