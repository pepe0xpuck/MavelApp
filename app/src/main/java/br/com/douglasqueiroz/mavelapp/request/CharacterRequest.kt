package br.com.douglasqueiroz.mavelapp.request

import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.model.Wrapper
import rx.Observable

interface CharacterRequest {

    fun getCharacters(offset: Int, limit: Int, query: String? = null): Observable<Wrapper<List<Character>>>
}