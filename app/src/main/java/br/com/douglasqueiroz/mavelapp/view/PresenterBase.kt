package br.com.douglasqueiroz.mavelapp.view

import android.content.Intent

abstract class PresenterBase: ContractBase.Presenter {

    override fun prepareIntent(intent: Intent): Intent {
        return intent
    }

}