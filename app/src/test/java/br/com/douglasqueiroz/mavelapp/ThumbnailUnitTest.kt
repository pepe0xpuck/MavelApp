package br.com.douglasqueiroz.mavelapp

import br.com.douglasqueiroz.mavelapp.model.Thumbnail
import org.junit.Assert
import org.junit.Test

class ThumbnailUnitTest {

    @Test
    fun testFullPath () {

        val name = "name"

        val extension = "extension"

        val thumbnail = Thumbnail(name, extension)

        Assert.assertEquals(thumbnail.getFullPath(), "$name.$extension")
    }
}