package br.com.douglasqueiroz.mavelapp

import br.com.douglasqueiroz.mavelapp.helper.HashHelper
import org.junit.Assert
import org.junit.Test

class HashHelperUnitTest {

    @Test
    fun testGenerateHash() {

        val time = System.currentTimeMillis()

        val hash = HashHelper.generateHash(time)

        Assert.assertNotNull(hash)
    }
}