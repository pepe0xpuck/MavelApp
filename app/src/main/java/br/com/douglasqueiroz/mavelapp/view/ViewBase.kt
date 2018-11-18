package br.com.douglasqueiroz.mavelapp.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity

abstract class ViewBase: AppCompatActivity(), ContractBase.View {


    override fun onStart() {
        super.onStart()

        getPresenter().loadData()
    }

    override fun navigateTo(type: Class<out Activity>, flag: Int, bundle: Bundle?) {

        val intent = Intent(this, type)
            .setFlags(flag)
            .putExtras(bundle)

        startActivity(intent)
    }

    override fun getPutExtra(key: String): Class<out Parcelable>? {
        return null
    }

    override fun showErrorMessage(msgStringId: Int) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(msgStringId: Int) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    protected abstract fun getPresenter(): ContractBase.Presenter

}