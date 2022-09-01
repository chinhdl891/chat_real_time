package com.example.chatrealtime.dialog

import android.content.Context
import android.widget.Toast
import com.example.chatrealtime.R
import com.example.chatrealtime.base.BaseDialog
import com.example.chatrealtime.databinding.DialogCreateAccBinding

class CreateAccDialog(context: Context, var onCreate: (String) -> Unit) :
    BaseDialog<DialogCreateAccBinding>(context) {
    override fun setUpdata() {

    }

    override fun setClick() {
        mBinding.btnCreateUser.setOnClickListener {
            val useName = mBinding.tvCreateUser.text.toString().trim()
            if (useName.length > 3) {
                onCreate(useName)
                dismiss()
            } else {
                Toast.makeText(context, "Ten khong hop le", Toast.LENGTH_SHORT).show()
            }
        }
        mBinding.btnCreateUserCancel.setOnClickListener { dismiss() }
    }

    override fun setResizeView() {

    }

    override fun getLayoutResource(): Int {
        return R.layout.dialog_create_acc
    }

}