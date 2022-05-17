package com.semicolon.walkhub.ui.profile.setting.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.semicolon.walkhub.R
import com.semicolon.walkhub.viewmodel.profile.setting.ModifyProfileViewModel

class DialogAddFragment : DialogFragment(), View.OnClickListener {

    private val vm: ModifyProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.change_class_dialog, container, false)
        processBundle(view)



        return view
    }

    private fun processBundle(view: View) {
        val bundle = arguments
    }

    fun getInstance(): DialogAddFragment {
        return DialogAddFragment()
    }

    override fun onClick(p0: View?) {
        dismiss()
    }

}