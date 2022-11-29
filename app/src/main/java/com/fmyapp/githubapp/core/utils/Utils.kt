package com.fmyapp.githubapp.core.utils

import android.content.Context
import android.view.Gravity
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

fun AppCompatActivity.setupToolbar(
    toolbar: Toolbar,
    titleString: String,
    backClickHandler: (() -> Unit)? = null
) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setDisplayShowTitleEnabled(true)
    supportActionBar?.title = titleString
    toolbar.setNavigationOnClickListener {
        backClickHandler?.run { this() } ?: this.onBackPressedDispatcher.onBackPressed()
    }
}

fun AppCompatActivity.showSnackBar(
    view: View,
    message: String,
    actionString: String = "OKE",
    listener: View.OnClickListener
) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction(actionString, listener).show()
}

fun Fragment.showSnackBar(
    view: View,
    message: String,
    actionString: String = "OKE",
    listener: View.OnClickListener
) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).setAction(actionString, listener).show()
}

fun ImageView.loadRectangleImageWithUrl(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .fitCenter()
        .into(this)
}

fun ImageView.loadCircleImageWithUrl(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(this)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun AppCompatActivity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager =
        getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.showPopUpMenu(
    view: View,
    menuItems: List<String>,
    action: (menuId: Int) -> Unit = {}
) {
    val popUpMenu = PopupMenu(requireContext(), view)
    menuItems.forEachIndexed { index, s ->
        popUpMenu.menu.add(Menu.NONE, index, index, s)
    }
    popUpMenu.setOnMenuItemClickListener {
        action(it.itemId)
        false
    }
    popUpMenu.show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).apply {
        this.setGravity(Gravity.CENTER, 0, 0)
        this.show()
    }
}