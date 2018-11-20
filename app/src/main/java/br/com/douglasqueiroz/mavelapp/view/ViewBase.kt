package br.com.douglasqueiroz.mavelapp.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import br.com.douglasqueiroz.mavelapp.R
import br.com.douglasqueiroz.mavelapp.ui.helper.LoadDialog

abstract class ViewBase: AppCompatActivity(), ContractBase.View {

    private val mLoadDialog: LoadDialog by lazy { LoadDialog() }

    override fun navigateTo(type: Class<out Activity>, flag: Int, bundle: Bundle) {

        val intent = Intent(this, type)
            .setFlags(flag)
            .putExtras(bundle)

        startActivity(intent)
    }

    override fun getPutExtra(key: String): Class<out Parcelable>? {
        return null
    }

    override fun showErrorMessage(msgStringId: Int) {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setTitle(R.string.default_error_title)
            .setMessage(msgStringId)
            .setPositiveButton(android.R.string.ok, null)
            .create()
            .show()
    }

    override fun showMessage(msgStringId: Int) {
        AlertDialog.Builder(this)
            .setCancelable(false)
            .setMessage(msgStringId)
            .setPositiveButton(android.R.string.ok, null)
            .create()
            .show()
    }

    override fun showProgress() {
        mLoadDialog.show(supportFragmentManager, LoadDialog.TAG)
    }

    override fun hideProgress() {
        mLoadDialog.dismissAllowingStateLoss()
    }

    protected abstract fun getPresenter(): ContractBase.Presenter

}