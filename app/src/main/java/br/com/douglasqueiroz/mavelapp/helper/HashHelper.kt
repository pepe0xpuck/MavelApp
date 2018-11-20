package br.com.douglasqueiroz.mavelapp.helper

import android.util.Log
import br.com.douglasqueiroz.mavelapp.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class HashHelper {

    companion object {
        fun generateHash(timeStamp: Long): String {

            try {
                val md = MessageDigest.getInstance("MD5")

                val string = "$timeStamp${BuildConfig.PRIVATE_KEY}${BuildConfig.PUBLIC_KEY}"

                val messageDigest = md.digest(string.toByteArray(Charsets.UTF_8))

                val number = BigInteger(1, messageDigest)

                var md5 = number.toString(16)

                while (md5.length < 32) {
                    md5 = "0$md5"
                }

                return md5

            } catch (e: NoSuchAlgorithmException) {
                Log.e("DataManager", "Error hashing required parameters: " + e.message)
                return ""
            }

        }
    }
}