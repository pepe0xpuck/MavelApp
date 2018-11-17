package br.com.douglasqueiroz.mavelapp.request.impl

import br.com.douglasqueiroz.mavelapp.BuildConfig
import br.com.douglasqueiroz.mavelapp.helper.HashHelper
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.model.Wrapper
import br.com.douglasqueiroz.mavelapp.request.CharacterRequest
import br.com.douglasqueiroz.mavelapp.request.MavelAppApi
import br.com.douglasqueiroz.mavelapp.request.MavelAppRequestConfig
import rx.Observable

class CharacterRequestImpl: CharacterRequest {

    override fun getCharacters(query: String?): Observable<Wrapper<List<Character>>> {

        val timeStamp = System.currentTimeMillis()

        return MavelAppRequestConfig.createRetrofit()
            .create(MavelAppApi::class.java)
            .getCharacters(BuildConfig.PUBLIC_KEY,
                            HashHelper.generateHash(timeStamp),
                            timeStamp,
                            query)
    }
}