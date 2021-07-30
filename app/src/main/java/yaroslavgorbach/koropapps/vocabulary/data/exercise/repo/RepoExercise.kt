package yaroslavgorbach.koropapps.vocabulary.data.exercise.repo

interface RepoExercise {
    fun getLetters(): List<String>
    fun getWordsFillings(): List<String>
    fun getWordsNouns(): List<String>
    fun getWordsCategory(): List<String>
}