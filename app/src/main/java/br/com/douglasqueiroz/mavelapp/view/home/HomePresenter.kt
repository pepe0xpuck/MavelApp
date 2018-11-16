package br.com.douglasqueiroz.mavelapp.view.home

import br.com.douglasqueiroz.mavelapp.R
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.model.Wrapper
import br.com.douglasqueiroz.mavelapp.request.CharacterRequest
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class HomePresenter(private val mView: HomeContract.View, private val mCharacterRequest: CharacterRequest): HomeContract.Presenter {

    private var mCharacters: List<Character>? = null

    override fun loadData() {

        mCharacterRequest.getCharacters()
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

//        mView.showList(arrayListOf(Character(1, "Teste", "teste")))
    }

    override fun refreshList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCharacterListItemClick(index: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}