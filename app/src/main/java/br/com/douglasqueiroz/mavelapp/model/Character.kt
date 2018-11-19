package br.com.douglasqueiroz.mavelapp.model

import android.os.Parcel
import android.os.Parcelable

data class Character (val id: Int, val name: String?, val description: String?, val thumbnail: Thumbnail): Parcelable {

    constructor(parcel: Parcel): this (
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        Thumbnail(parcel)
    )

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeInt(id)
        parcel?.writeString(name)
        parcel?.writeString(description)
        thumbnail.writeToParcel(parcel, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }
}