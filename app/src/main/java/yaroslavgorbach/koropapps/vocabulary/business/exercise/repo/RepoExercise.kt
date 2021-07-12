package yaroslavgorbach.koropapps.vocabulary.business.exercise.repo

interface RepoExercise {
    fun getLetters(): List<String>
    fun getWords(): List<String>
}