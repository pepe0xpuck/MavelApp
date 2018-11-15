package br.com.douglasqueiroz.mavelapp.view.home

import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.request.CharacterRequest
import br.com.douglasqueiroz.mavelapp.request.impl.CharacterRequestImpl
import rx.Subscriber
import rx.schedulers.Schedulers

class HomePresenter(val mView: HomeContract.View): HomeContract.Presenter {

    private val mCharacterRequest: CharacterRequest by lazy { CharacterRequestImpl() }
    private lateinit var mCharacters: List<Character>

    override fun loadData() {

        mCharacterRequest.getCharacters()
            .observeOn(Schedulers.io())
            .subscribe(object : Subscriber<List<Character>>() {

                override fun onCompleted() {
                    mView.hideProgress()
                    mView.showList(mCharacters)
                }

                override fun onError(e: Throwable) {
                    mView.hideProgress()
                    // TODO: Create a default connection error message
                    mView.showErrorMessge("")
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