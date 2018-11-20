package br.com.douglasqueiroz.mavelapp.ui.helper

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.douglasqueiroz.mavelapp.R

class LoadDialog: DialogFragment() {

    companion object {
        const val TAG = "LoadDialog.class"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isCancelable = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.progress_content, container, false)
    }

    override fun show(manager: FragmentManager, tag: String) {

        if (isAdded)
            return

        try {

            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commit()

        } catch (e: IllegalStateException) {

            Log.d(TAG, "Exception", e)
        }

    }
}