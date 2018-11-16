package br.com.douglasqueiroz.mavelapp.request

import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.model.Wrapper
import rx.Observable

interface CharacterRequest {

    fun getCharacters(): Observable<Wrapper<List<Character>>>
}