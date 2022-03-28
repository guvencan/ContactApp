package karpat.guvencan.contactapp.ui.edit

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import karpat.guvencan.contactapp.R
import karpat.guvencan.contactapp.base.BaseFragment
import karpat.guvencan.contactapp.data.local.Contact
import karpat.guvencan.contactapp.databinding.EditFragmentBinding
import karpat.guvencan.contactapp.ui.main.SharedViewModel

@AndroidEntryPoint
class EditFragment : BaseFragment<EditFragmentBinding, SharedViewModel>() {

    override val layoutId = R.layout.edit_fragment

    override val viewModel: SharedViewModel by activityViewModels()

    override fun createView() {
        binding.btnSave.setOnClickListener { _ ->
            val contact = viewModel.selectedItem ?: Contact(0)
            viewModel.selectedItem = contact.apply {
                name = binding.etName.text.toString()
                surname = binding.etSurname.text.toString()
                department = binding.etDepartment.text.toString()
                company_name = binding.etDepartment.text.toString()
                email = binding.etEmail.text.toString()
            }
            viewModel.save()
            findNavController().navigate(EditFragmentDirections.toHomeFragment())
        }
    }


    override fun observeValues() {}

}