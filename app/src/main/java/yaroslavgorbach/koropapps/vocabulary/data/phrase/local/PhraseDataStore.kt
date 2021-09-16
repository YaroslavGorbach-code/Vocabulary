package yaroslavgorbach.koropapps.vocabulary.data.phrase.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.koropapps.vocabulary.business.phrase.factory.PhraseFactory
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase
import java.util.*

private val Context.phraseDataStore: DataStore<Preferences> by preferencesDataStore(name = "phrase")

class PhraseDataStore {
    companion object {
        private const val DEFAULT_LONG_ARG = 1L
        private const val DEFAULT_STRING_ARG = ""

        private val DATE_KEY = longPreferencesKey("DATE_KEY")
        private val PHRASE_KEY = stringPreferencesKey("PHRASE_KEY")
        private val EXPLANATION_KEY = stringPreferencesKey("EXPLANATION_KEY")
    }

    suspend fun insert(context: Context, phrase: Phrase) {
        context.phraseDataStore.edit { prefs ->
            prefs[DATE_KEY] = phrase.date.time
            prefs[PHRASE_KEY] = phrase.phrase
            prefs[EXPLANATION_KEY] = phrase.explanation
        }
    }

    fun observe(context: Context): Flow<Phrase> {
        return context.phraseDataStore.data
            .map { preferences ->
                val date = Date(preferences[DATE_KEY] ?: DEFAULT_LONG_ARG)
                val phrase = preferences[PHRASE_KEY] ?: DEFAULT_STRING_ARG
                val explanation = preferences[EXPLANATION_KEY] ?: DEFAULT_STRING_ARG

                Phrase(phrase = phrase, explanation = explanation, date = date)
            }
    }
}