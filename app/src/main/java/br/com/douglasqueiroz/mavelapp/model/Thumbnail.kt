package br.com.douglasqueiroz.mavelapp.model

import android.os.Parcel
import android.os.Parcelable

data class Thumbnail(val path: String?, val extension: String?): Parcelable {

    fun getFullPath(): String {
        return "$path.$extension"
    }

    constructor(parcel: Parcel): this (
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(path)
        parcel?.writeString(extension)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Thumbnail> {
        override fun createFromParcel(parcel: Parcel): Thumbnail {
            return Thumbnail(parcel)
        }

        override fun newArray(size: Int): Array<Thumbnail?> {
            return arrayOfNulls(size)
        }
    }
}