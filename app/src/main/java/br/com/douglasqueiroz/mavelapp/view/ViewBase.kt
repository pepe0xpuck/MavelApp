package br.com.douglasqueiroz.mavelapp.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class ViewBase(var layoutId: Int): AppCompatActivity(), ContractBase.View {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

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