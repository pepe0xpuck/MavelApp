package br.com.douglasqueiroz.mavelapp.request

import br.com.douglasqueiroz.mavelapp.model.Character
import rx.Observable

interface CharacterRequest {

    fun getCharacters(): Observable<List<Character>>
}