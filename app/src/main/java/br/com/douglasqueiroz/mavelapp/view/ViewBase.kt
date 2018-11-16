package br.com.douglasqueiroz.mavelapp.view

import android.support.v7.app.AppCompatActivity

abstract class ViewBase: AppCompatActivity(), ContractBase.View {


    override fun onStart() {
        super.onStart()

        getPresenter().loadData()
    }

    override fun callView() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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