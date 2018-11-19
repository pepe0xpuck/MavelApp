package br.com.douglasqueiroz.mavelapp.view.characterDetails

import android.support.v7.app.AppCompatActivity
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.view.ContractBase

interface CharacterDetailsContract {

    interface View: ContractBase.View {

        fun showCharacter(character: Character)
    }

    interface Presenter: ContractBase.Presenter {

        fun loadCharacter(ctx: AppCompatActivity)
    }
}