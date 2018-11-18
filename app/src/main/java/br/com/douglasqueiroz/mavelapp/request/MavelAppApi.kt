package br.com.douglasqueiroz.mavelapp.request

import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.model.Wrapper
import org.jetbrains.annotations.Nullable
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface MavelAppApi {

    @GET("v1/public/characters")
    fun getCharacters(@Query("apikey") publicKey: String,
                      @Query("hash") hash: String,
                      @Query("ts") timestamp: Long,
                      @Nullable @Query("nameStartsWith") query: String?,
                      @Nullable @Query("offset") offset: Int,
                      @Nullable @Query("limit") limit: Int)
            :Observable<Wrapper<List<Character>>>
}