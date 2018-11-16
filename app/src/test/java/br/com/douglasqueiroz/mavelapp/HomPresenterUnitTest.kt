package br.com.douglasqueiroz.mavelapp

import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.request.CharacterRequest
import br.com.douglasqueiroz.mavelapp.view.home.HomeContract
import br.com.douglasqueiroz.mavelapp.view.home.HomePresenter
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import rx.Observable
import java.lang.RuntimeException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HomPresenterUnitTest {

    @Mock
    lateinit var mView: HomeContract.View


    @Mock
    lateinit var mCharacterRequest: CharacterRequest

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun loadData_withSuccess() {

        val characters = arrayListOf(Character(1, "teste", ""))

        doReturn(Observable.just(characters)).`when`(mCharacterRequest).getCharacters()

        val homePresenter = HomePresenter(mView, mCharacterRequest)

        homePresenter.loadData()

        verify(mView, times(1)).showProgress()

        verify(mView, times(1)).showList(characters)

        verify(mView, never()).showNoDataView()

        verify(mView, times(1)).hideProgress()

        verify(mView, never()).callView()

        verify(mView, never()).showErrorMessage(Mockito.anyInt())

        verify(mView, never()).showMessage(Mockito.anyInt())
    }

    @Test
    fun loadData_withError() {

        doReturn(Observable.just(RuntimeException())).`when`(mCharacterRequest).getCharacters()

        val homePresenter = HomePresenter(mView, mCharacterRequest)

        homePresenter.loadData()

        verify(mView, times(1)).showProgress()

        verify(mView, never()).showList(Mockito.anyList())

        verify(mView, never()).showNoDataView()

        verify(mView, times(1)).hideProgress()

        verify(mView, never()).callView()

        verify(mView, times(1)).showErrorMessage(Mockito.anyInt())

        verify(mView, never()).showMessage(Mockito.anyInt())
    }

    @Test
    fun loadData_withNoData() {

        doReturn(Observable.just(ArrayList<Character>())).`when`(mCharacterRequest).getCharacters()

        val homePresenter = HomePresenter(mView, mCharacterRequest)

        homePresenter.loadData()

        verify(mView, times(1)).showProgress()

        verify(mView, never()).showList(Mockito.anyList())

        verify(mView, times(1)).showNoDataView()

        verify(mView, times(1)).hideProgress()

        verify(mView, never()).callView()

        verify(mView, never()).showErrorMessage(Mockito.anyInt())

        verify(mView, never()).showMessage(Mockito.anyInt())
    }
}
