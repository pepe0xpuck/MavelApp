package br.com.douglasqueiroz.mavelapp.view

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable

interface ContractBase {

    interface View {

        fun navigateTo(type: Class<out Activity>, flag: Int, bundle: Bundle?)

        fun getPutExtra(key: String): Class<out Parcelable>?

        fun showErrorMessage(msgStringId: Int)

        fun showMessage(msgStringId: Int)

        fun showProgress()

        fun hideProgress()
    }

    interface Presenter {

    }
}