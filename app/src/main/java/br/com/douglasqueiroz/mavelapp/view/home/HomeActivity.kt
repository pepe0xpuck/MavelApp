package br.com.douglasqueiroz.mavelapp

import br.com.douglasqueiroz.mavelapp.view.ContractBase
import br.com.douglasqueiroz.mavelapp.view.ViewBase
import br.com.douglasqueiroz.mavelapp.view.home.HomeContract
import br.com.douglasqueiroz.mavelapp.view.home.HomePresenter

class HomeActivity : ViewBase(R.layout.activity_home) {

    private val presenter: HomeContract.Presenter by lazy { HomePresenter() }

    override fun getPresenter(): ContractBase.Presenter {
        return presenter
    }
}
