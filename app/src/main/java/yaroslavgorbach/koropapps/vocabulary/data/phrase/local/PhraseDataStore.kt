package yaroslavgorbach.koropapps.vocabulary.data.phrase.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import yaroslavgorbach.koropapps.vocabulary.data.phrase.local.model.Phrase
import java.util.*

private val Context.phraseDataStore: DataStore<Preferences> by preferencesDataStore(name = "phrase")

class PhraseDataStore {
    companion object {
        private val PHRASE_DATE_KEY = longPreferencesKey("PHRASE_DATE_KEY")
        private val PHRASE_STRING_KEY = stringPreferencesKey("PHRASE_STRING_KEY")
    }

    suspend fun insert(context: Context, phrase: Phrase) {
        context.phraseDataStore.edit { prefs ->
            prefs[PHRASE_DATE_KEY] = phrase.date.time
            prefs[PHRASE_STRING_KEY] = phrase.phrase
        }
    }

    fun observe(context: Context): Flow<Phrase> {
        return context.phraseDataStore.data
            .map { preferences ->
                val date = Date(preferences[PHRASE_DATE_KEY] ?: 0L)
                val phraseString = preferences[PHRASE_STRING_KEY] ?: ""
                Phrase(phraseString, date)
            }
    }
}