package br.com.douglasqueiroz.mavelapp.view.characterDetails

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import br.com.douglasqueiroz.mavelapp.R
import br.com.douglasqueiroz.mavelapp.helper.bindView
import br.com.douglasqueiroz.mavelapp.model.Character
import br.com.douglasqueiroz.mavelapp.view.ContractBase
import br.com.douglasqueiroz.mavelapp.view.ViewBase
import com.squareup.picasso.Picasso

class CharacterDetailsActivity : ViewBase(), CharacterDetailsContract.View {

    companion object {
        const val CHARACTER_BUNDLE_KEY = "character_bundle_key"
    }

    private val mPresenter: CharacterDetailsContract.Presenter by lazy { CharacterDetailsPresenter(this) }
    private val mCharacterImageView by bindView<ImageView>(R.id.image_view_character_details_character_image)
    private val mCharacterNameTextView by bindView<TextView>(R.id.text_view_character_details_character_name)
    private val mCharacterDescriptionTextView by bindView<TextView>(R.id.text_view_character_details_character_description)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        super.onStart()
        mPresenter.loadCharacter(this)
    }

    override fun getPresenter(): ContractBase.Presenter {
        return mPresenter
    }

    override fun showCharacter(character: Character) {

        mCharacterNameTextView.text = character.name
        mCharacterDescriptionTextView.text = character.description
        Picasso
            .get()
            .load(character.thumbnail.getFullPath())
            .into(mCharacterImageView)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        item?.let {

            when(it.itemId) {
                android.R.id.home -> { onBackPressed() }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
