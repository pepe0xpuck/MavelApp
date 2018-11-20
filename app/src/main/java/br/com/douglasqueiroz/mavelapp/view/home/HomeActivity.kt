package br.com.douglasqueiroz.mavelapp.view.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.View
import android.widget.TextView
import br.com.douglasqueiroz.mavelapp.R
import br.com.douglasqueiroz.mavelapp.helper.bindView
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.request.impl.CharacterRequestImpl
import br.com.douglasqueiroz.mavelapp.ui.adapter.CharacterAdapter
import br.com.douglasqueiroz.mavelapp.view.ContractBase
import br.com.douglasqueiroz.mavelapp.view.ViewBase
import android.view.MenuItem
import br.com.douglasqueiroz.mavelapp.ui.helper.EndlessRecyclerViewOnScrollListener


class HomeActivity : ViewBase(), HomeContract.View, SearchView.OnQueryTextListener, CharacterAdapter.OnClick {

    private val mPresenter: HomeContract.Presenter by lazy { HomePresenter(this, CharacterRequestImpl()) }
    private val mNoDataTextView: TextView by bindView(R.id.text_view_home_no_data_msg)
    private val mCharactersRecyclerView: RecyclerView by bindView(R.id.recycle_view_home_list)
    private val mCharacterAdapter: CharacterAdapter by lazy { CharacterAdapter(emptyList(), this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val layoutManager = LinearLayoutManager(this)
        mCharactersRecyclerView.setHasFixedSize(true)
        mCharactersRecyclerView.layoutManager = layoutManager
        mCharactersRecyclerView.addOnScrollListener(setupScrollListener(layoutManager))
        mCharactersRecyclerView.adapter = mCharacterAdapter

        mPresenter.loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.action_search)
        searchView.setOnQueryTextListener(this)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                mPresenter.searchCharacter(null)

                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun getPresenter(): ContractBase.Presenter {
        return mPresenter
    }

    override fun showList(characters: List<Character>) {
        thereIsNoData(true)
        mCharacterAdapter.updateData(characters)
    }

    override fun showNoDataView() {
        thereIsNoData(false)
    }

    private fun thereIsNoData(isThere: Boolean) {

        if (isThere) {

            mCharactersRecyclerView.visibility = View.VISIBLE
            mNoDataTextView.visibility = View.INVISIBLE
        } else {

            mCharactersRecyclerView.visibility = View.INVISIBLE
            mNoDataTextView.visibility = View.VISIBLE
        }
    }

    private fun setupScrollListener(layoutManager: LinearLayoutManager?): EndlessRecyclerViewOnScrollListener {

        return object : EndlessRecyclerViewOnScrollListener(layoutManager) {

            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                mPresenter.loadNextPage(totalItemsCount)
            }
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        mPresenter.searchCharacter(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false
    }

    override fun onClick(character: Character) {
        mPresenter.onCharacterListItemClick(character)
    }
}
