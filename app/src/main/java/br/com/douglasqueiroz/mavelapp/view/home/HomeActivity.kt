package br.com.douglasqueiroz.mavelapp.view.home

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.text.TextUtils
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


class HomeActivity : ViewBase(), HomeContract.View, SearchView.OnQueryTextListener, CharacterAdapter.OnClick {

    private val mPresenter: HomeContract.Presenter by lazy { HomePresenter(this, this, CharacterRequestImpl()) }
    private val mNoDataTextView: TextView by bindView(R.id.text_view_home_no_data_msg)
    private val mCharactersRecyclerView: RecyclerView by bindView(R.id.recycle_view_home_list)
    private var mSearchQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mCharactersRecyclerView.setHasFixedSize(true)
        mCharactersRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_menu, menu)

        val searchView = MenuItemCompat.getActionView(menu?.findItem(R.id.action_search)) as android.support.v7.widget.SearchView
        searchView.queryHint = getString(R.string.action_search)
        searchView.setOnQueryTextListener(this)
        MenuItemCompat.setOnActionExpandListener(menu?.findItem(R.id.action_search),
            object : MenuItemCompat.OnActionExpandListener {
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

        mCharactersRecyclerView.adapter = CharacterAdapter(characters, this)
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
