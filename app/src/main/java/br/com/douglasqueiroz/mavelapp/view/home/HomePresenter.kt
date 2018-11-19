package br.com.douglasqueiroz.mavelapp.view.home

import android.os.Bundle
import br.com.douglasqueiroz.mavelapp.R
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.model.Wrapper
import br.com.douglasqueiroz.mavelapp.request.CharacterRequest
import br.com.douglasqueiroz.mavelapp.view.PresenterBase
import br.com.douglasqueiroz.mavelapp.view.characterDetails.CharacterDetailsActivity
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomePresenter(private val mView: HomeContract.View,
                    private val mCharacterRequest: CharacterRequest): PresenterBase(), HomeContract.Presenter {

    private var mCharacters = emptyList<Character>()
    private var mSearchQuery: String? = null

    override fun loadData() {
        loadCharacters()
    }

    override fun searchCharacter(query: String?) {
        mSearchQuery = query
        loadCharacters(query)
    }

    override fun onCharacterListItemClick(character: Character) {

        val bundle = Bundle()
        bundle.putParcelable(CharacterDetailsActivity.CHARACTER_BUNDLE_KEY, character)

        mView.navigateTo(CharacterDetailsActivity::class.java, 1, bundle)
    }

    override fun loadNextPage(offset: Int) {
        loadCharacters(mSearchQuery, offset)
    }

    private fun loadCharacters(query: String? = null, offset: Int = 0 , limit: Int = 20) {

        mCharacterRequest.getCharacters(offset, limit, query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Wrapper<List<Character>>>() {

                override fun onStart() {
                    super.onStart()

                        mView.showProgress()
                }

                override fun onCompleted() {
                    mView.hideProgress()

                    if (mCharacters.isEmpty()) {

                        mView.showNoDataView()
                    }else {

                        mView.showList(mCharacters)
                    }
                }

                override fun onError(e: Throwable) {

                    mView.hideProgress()

                    mView.showErrorMessage(R.string.default_error_msg)
                }

                override fun onNext(wrapper: Wrapper<List<Character>>) {

                    wrapper.data?.results?.let {

                        if (offset > 0) {
                            mCharacters += it
                        }else {
                            mCharacters = it
                        }
                    }
                }
            })
    }
}