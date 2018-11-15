package br.com.douglasqueiroz.mavelapp.view

interface ContractBase {

    interface View {

        fun callView()

        fun showErrorMessge(message: String)

        fun showMessge(message: String)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter {

        fun loadData()
    }
}