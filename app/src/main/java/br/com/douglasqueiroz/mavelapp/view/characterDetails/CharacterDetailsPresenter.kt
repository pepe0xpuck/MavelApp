package br.com.douglasqueiroz.mavelapp.view.characterDetails

import android.support.v7.app.AppCompatActivity
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.view.PresenterBase

class CharacterDetailsPresenter(private val ctx: AppCompatActivity,
                                private val view: CharacterDetailsContract.View): PresenterBase(),
    CharacterDetailsContract.Presenter {

    override fun loadData() {

        val character = ctx.intent.getParcelableExtra<Character>(CharacterDetailsActivity.CHARACTER_BUNDLE_KEY)
        view.showCharacter(character)
    }
}