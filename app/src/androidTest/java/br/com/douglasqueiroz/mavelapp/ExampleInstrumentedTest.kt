package br.com.douglasqueiroz.mavelapp

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.request.impl.CharacterRequestImpl

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import rx.Subscriber
import rx.observers.TestSubscriber
import rx.plugins.RxJavaPlugins
import rx.schedulers.TestScheduler

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    private val requester by lazy { CharacterRequestImpl() }

    @Test
    fun getCharacter() {

    }
}
