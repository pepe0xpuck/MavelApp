package br.com.douglasqueiroz.mavelapp.view.home

import br.com.douglasqueiroz.mavelapp.R
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.request.CharacterRequest
import rx.Subscriber
import rx.schedulers.Schedulers

class HomePresenter(private val mView: HomeContract.View, private val mCharacterRequest: CharacterRequest): HomeContract.Presenter {

    private var mCharacters: List<Character>? = null

    override fun loadData() {

        mCharacterRequest.getCharacters()
            .observeOn(Schedulers.io())
            .subscribe(object : Subscriber<List<Character>>() {

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

                override fun onNext(characters: List<Character>) {

                    mCharacters = characters
                }
            })
    }

    override fun refreshList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCharacterListItemClick(index: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}