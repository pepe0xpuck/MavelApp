package br.com.douglasqueiroz.mavelapp

import android.support.test.filters.LargeTest
import android.support.test.runner.AndroidJUnit4
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.model.DataResult
import br.com.douglasqueiroz.mavelapp.model.Thumbnail
import br.com.douglasqueiroz.mavelapp.model.Wrapper
import br.com.douglasqueiroz.mavelapp.request.CharacterRequest
import br.com.douglasqueiroz.mavelapp.view.home.HomeContract
import br.com.douglasqueiroz.mavelapp.view.home.HomePresenter
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import rx.Observable
import java.lang.RuntimeException


@RunWith(AndroidJUnit4::class)
@LargeTest
class HomePresenterTest {

    @Mock
    lateinit var mView: HomeContract.View

    @Mock
    lateinit var mCharacterRequest: CharacterRequest

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @After
    fun tearDown() {
        Mockito.reset(mView)
        Mockito.reset(mCharacterRequest)
    }

    @Test
    fun loadData_withSuccess() {

        val character = Character(1, null, null, Thumbnail(null, null))

        val characters = listOf(character)

        val data = DataResult<List<Character>>()
        data.results = characters

        val wrapper = Wrapper<List<Character>>()
        wrapper.data = data

        doReturn(Observable.just(wrapper)).`when`(mCharacterRequest).getCharacters(0, 20, null)

        val homePresenter = HomePresenter(mView, mCharacterRequest)

        homePresenter.loadData()

        verify(mView, times(1)).showProgress()

        verify(mView, times(1)).hideProgress()

        verify(mView, times(1)).showList(characters)

        verify(mView, never()).showNoDataView()

        verify(mView, never()).showErrorMessage(Mockito.anyInt())

        verify(mView, never()).showMessage(Mockito.anyInt())
    }

    @Test
    fun loadData_withError() {

        doReturn(Observable.just(RuntimeException())).`when`(mCharacterRequest).getCharacters(0, 20, null)

        val homePresenter = HomePresenter(mView, mCharacterRequest)

        homePresenter.loadData()

        verify(mView, times(1)).showProgress()

        verify(mView, times(1)).hideProgress()

        verify(mView, times(1)).showErrorMessage(R.string.default_error_msg)

        verify(mView, never()).showList(Mockito.anyList())

        verify(mView, never()).showNoDataView()

        verify(mView, never()).showMessage(Mockito.anyInt())
    }

    @Test
    fun loadData_withNoData() {

        val characters = emptyList<Character>()

        val data = DataResult<List<Character>>()
        data.results = characters

        val wrapper = Wrapper<List<Character>>()
        wrapper.data = data

        doReturn(Observable.just(wrapper)).`when`(mCharacterRequest).getCharacters(0, 20, null)

        val homePresenter = HomePresenter(mView, mCharacterRequest)

        homePresenter.loadData()

        verify(mView, times(1)).showProgress()

        verify(mView, times(1)).hideProgress()

        verify(mView, times(1)).showNoDataView()

        verify(mView, never()).showList(Mockito.anyList())

        verify(mView, never()).showErrorMessage(Mockito.anyInt())

        verify(mView, never()).showMessage(Mockito.anyInt())
    }
}