package br.com.douglasqueiroz.mavelapp.view.home

import android.content.Context
import android.content.Intent
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

class HomePresenter(private val ctx: Context,
                    private val mView: HomeContract.View,
                    private val mCharacterRequest: CharacterRequest): PresenterBase(), HomeContract.Presenter {

    private var mCharacters: List<Character>? = null

    override fun loadData() {
        loadCharacters()
    }

    override fun searchCharacter(query: String?) {
        loadCharacters(query)
    }

    override fun refreshList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCharacterListItemClick(character: Character) {

        val bundle = Bundle()
        bundle.putParcelable(CharacterDetailsActivity.CHARACTER_BUNDLE_KEY, character)

        mView.navigateTo(CharacterDetailsActivity::class.java, 1, bundle)
    }

    private fun loadCharacters(query: String? = null) {

        mCharacterRequest.getCharacters(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Subscriber<Wrapper<List<Character>>>() {

                override fun onStart() {
                    super.onStart()

                    mView.showProgress()
                }

                override fun onCompleted() {
                    mView.hideProgress()

                    mCharacters?.let {

                        if (it.isEmpty()) {

                            mView.showNoDataView()
                        }else {

                            mView.showList(it)
                        }
                    }
                }

                override fun onError(e: Throwable) {

                    mView.hideProgress()

                    mView.showErrorMessage(R.string.default_error_msg)
                }

                override fun onNext(wrapper: Wrapper<List<Character>>) {

                    mCharacters = wrapper.data?.results
                }
            })
    }
}