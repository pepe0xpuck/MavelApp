package br.com.douglasqueiroz.mavelapp.request.impl

import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.request.CharacterRequest
import rx.Observable

class CharacterRequestImpl: CharacterRequest {

    override fun getCharacters(): Observable<List<Character>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}