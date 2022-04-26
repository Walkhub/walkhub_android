package com.semicolon.walkhub.ui.register.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.checkbox.MaterialCheckBox
import com.semicolon.walkhub.R

class MultiLevelCheckBox(context: Context, attributeSet: AttributeSet) :
    MaterialCheckBox(context, attributeSet) {
    private val parentId: Int
    private val parentCheckBox get() = rootView.findViewById<MultiLevelCheckBox>(parentId)

    private val checkedChildrenId = mutableListOf<Int>()
    private val childrenId = mutableListOf<Int>()
    private val childrenCheckBox get() = childrenId.map { rootView.findViewById<MultiLevelCheckBox>(it) }

    init {
        context.theme.obtainStyledAttributes(
            attributeSet, R.styleable.MultiLevelCheckBox, 0, 0
        ).apply {
            try {
                parentId = getResourceId(R.styleable.MultiLevelCheckBox_parentCheckBox, -1)
            } finally {
                recycle()
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        parentCheckBox?.childrenId?.add(id)
    }

    override fun performClick(): Boolean {
        val willBeChecked = !isChecked
        updateChildren(willBeChecked)
        updateParent(willBeChecked)
        return super.performClick()
    }

    private fun updateChildren(checked: Boolean) {
        childrenCheckBox.forEach {
            it.isChecked = checked
            it.updateChildren(checked)
        }
        with(checkedChildrenId) {
            if (checked) {
                clear()
                addAll(childrenId)
            } else {
                clear()
            }
        }
    }

    private fun updateParent(checked: Boolean) {
        parentCheckBox?.notifyChildChange(id, checked)
    }

    private fun notifyChildChange(childCheckBoxId: Int, checked: Boolean) {
        with(checkedChildrenId) {
            if (checked) {
                add(childCheckBoxId)
                takeIf { size == childrenId.size }?.let {
                    isChecked = true
                    updateParent(true)
                }
            } else {
                remove(childCheckBoxId)
                isChecked = false
                updateParent(false)
            }
        }
    }
}