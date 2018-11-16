package br.com.douglasqueiroz.mavelapp.view

interface ContractBase {

    interface View {

        fun callView()

        fun showErrorMessage(msgStringId: Int)

        fun showMessage(msgStringId: Int)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter {

        fun loadData()
    }
}