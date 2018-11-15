package br.com.douglasqueiroz.mavelapp

import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.request.impl.CharacterRequestImpl
import br.com.douglasqueiroz.mavelapp.view.ContractBase
import br.com.douglasqueiroz.mavelapp.view.ViewBase
import br.com.douglasqueiroz.mavelapp.view.home.HomeContract
import br.com.douglasqueiroz.mavelapp.view.home.HomePresenter

class HomeActivity : ViewBase(R.layout.activity_home), HomeContract.View {

    private val presenter: HomeContract.Presenter by lazy { HomePresenter(this, CharacterRequestImpl()) }

    override fun getPresenter(): ContractBase.Presenter {
        return presenter
    }

    override fun showList(caracters: List<Character>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showNoDataView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
