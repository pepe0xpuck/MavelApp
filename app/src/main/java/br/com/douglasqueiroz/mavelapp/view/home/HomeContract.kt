package br.com.douglasqueiroz.mavelapp.view.home

import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.view.ContractBase

interface HomeContract {

    interface View: ContractBase.View {

        fun showList(characters: List<Character>)

        fun showNoDataView()
    }

    interface Presenter: ContractBase.Presenter {

        fun refreshList()

        fun searchCharacter(query: String?)

        fun onCharacterListItemClick(index: Int)
    }
}