package br.com.douglasqueiroz.mavelapp.view.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import br.com.douglasqueiroz.mavelapp.R
import br.com.douglasqueiroz.mavelapp.helper.bindView
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.request.impl.CharacterRequestImpl
import br.com.douglasqueiroz.mavelapp.ui.adapter.CharacterAdapter
import br.com.douglasqueiroz.mavelapp.view.ContractBase
import br.com.douglasqueiroz.mavelapp.view.ViewBase

class HomeActivity : ViewBase(), HomeContract.View {

    private val presenter: HomeContract.Presenter by lazy { HomePresenter(this, CharacterRequestImpl()) }
    private val noDataTextView: TextView by bindView(R.id.text_view_home_no_data_msg)
    private val charactersRecyclerView: RecyclerView by bindView(R.id.recycle_view_home_list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        charactersRecyclerView.setHasFixedSize(true)
        charactersRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun getPresenter(): ContractBase.Presenter {
        return presenter
    }

    override fun showList(caracters: List<Character>) {
        thereIsNoData(true)

        charactersRecyclerView.adapter = CharacterAdapter(caracters)
    }

    override fun showNoDataView() {
        thereIsNoData(false)
    }

    private fun thereIsNoData(isThere: Boolean) {

        if (isThere) {

            charactersRecyclerView.visibility = View.VISIBLE
            noDataTextView.visibility = View.INVISIBLE
        } else {

            charactersRecyclerView.visibility = View.INVISIBLE
            noDataTextView.visibility = View.VISIBLE
        }
    }
}
