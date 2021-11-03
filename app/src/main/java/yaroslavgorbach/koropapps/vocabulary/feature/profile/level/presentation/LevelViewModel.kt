package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.achievements.AchieveAchievementInteractor
import yaroslavgorbach.koropapps.vocabulary.business.achievements.ObserveAchievementsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.GetAllExercisesStatisticsValueInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.GetStatisticsCommonInfoInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.ObserveStatisticDaysInteractor
import yaroslavgorbach.koropapps.vocabulary.business.training.ObserveCurrentTrainingWithExercisesInteractor
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseCategory
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.getExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsDailyTrainingTimeEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.data.training.local.model.TrainingWithExercisesEntity
import yaroslavgorbach.koropapps.vocabulary.feature.common.mapper.ExerciseNameToExerciseCategoryMapper
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.LevelInfoUi
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LevelViewModel @Inject constructor(
    private val getStatisticsCommonInfoInteractor: GetStatisticsCommonInfoInteractor,
    private val observeAchievementsInteractor: ObserveAchievementsInteractor,
    private val achieveAchievementsInteractor: AchieveAchievementInteractor,
    private val getAllExercisesStatisticsValueInteractor: GetAllExercisesStatisticsValueInteractor,
    private val observeStatisticDaysInteractor: ObserveStatisticDaysInteractor,
    private val observeCurrentTrainingWithExercisesInteractor: ObserveCurrentTrainingWithExercisesInteractor
) : ViewModel() {

    companion object {
        private const val ALPHABET_NUMBER_OF_LETTERS_FOR_ACHIEVEMENTS = 29
        private const val ONE_HOUR_IN_MINUTE = 60

        private const val DICTIONARY_ADJECTIVES_WORDS_NORM = 46
        private const val DICTIONARY_NOUNS_WORDS_NORM = 54
        private const val DICTIONARY_VERBS_WORDS_NORM = 42
    }

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _LevelInfo: MutableLiveData<LevelInfoUi> = MutableLiveData()

    val levelInfoUi: LiveData<LevelInfoUi>
        get() = _LevelInfo

    val achievements: LiveData<List<Achievement>>
        get() = observeAchievementsInteractor().asLiveData()

    init {
        getLevelInfo()
        getStatisticInfoAndAchieveAchievements()
    }

    private fun achieveAchievements(
        commonInfo: StatisticsCommonInfoEntity,
        allExercisesValues: List<StatisticsExerciseValueEntity>,
        daysStatisticsDailyTrainingTimeEntities: List<StatisticsDailyTrainingTimeEntity>,
        trainingWithExercises: TrainingWithExercisesEntity
    ) {
        val isTongueTwisterEasyCompleted = allExercisesValues.any {
            it.exerciseNameRes == ExerciseName.TONGUE_TWISTERS_EASY.id && it.value > 0
        }

        val isTongueTwisterHardCompleted = allExercisesValues.any {
            it.exerciseNameRes == ExerciseName.TONGUE_TWISTERS_HARD.id && it.value > 0
        }

        val isTongueTwisterVeryHardCompleted = allExercisesValues.any {
            it.exerciseNameRes == ExerciseName.TONGUE_TWISTERS_VERY_HARD.id && it.value > 0
        }

        val isAlphabetVerbsCompleted = allExercisesValues.any {
            it.exerciseNameRes == ExerciseName.ALPHABET_VERBS.id && it.value > ALPHABET_NUMBER_OF_LETTERS_FOR_ACHIEVEMENTS
        }

        val isAlphabetNounsCompleted = allExercisesValues.any {
            it.exerciseNameRes == ExerciseName.ALPHABET_NOUN.id && it.value > ALPHABET_NUMBER_OF_LETTERS_FOR_ACHIEVEMENTS
        }

        val isAlphabetAdjectivesCompleted = allExercisesValues.any {
            it.exerciseNameRes == ExerciseName.ALPHABET_ADJECTIVES.id && it.value > ALPHABET_NUMBER_OF_LETTERS_FOR_ACHIEVEMENTS
        }

        val isAtLeastOneCommunicatingExerciseCompleted = allExercisesValues.any {
            ExerciseNameToExerciseCategoryMapper().map(getExerciseName(it.exerciseNameRes)) == ExerciseCategory.COMMUNICATION
        }

        val isAtLeastOneVocabularyExerciseCompleted = allExercisesValues.any {
            ExerciseNameToExerciseCategoryMapper().map(getExerciseName(it.exerciseNameRes)) == ExerciseCategory.VOCABULARY
        }

        val isAtLeastOneDictionExerciseCompleted = allExercisesValues.any {
            ExerciseNameToExerciseCategoryMapper().map(getExerciseName(it.exerciseNameRes)) == ExerciseCategory.DICTION_AND_ARTICULATION
        }

        val isDictionaryVerbsCompleteOverNorm = allExercisesValues.any {
            getExerciseName(it.exerciseNameRes) == ExerciseName.DICTIONARY_VERBS && it.value >= DICTIONARY_VERBS_WORDS_NORM
        }

        val isDictionaryAdjectivesCompleteOverNorm = allExercisesValues.any {
            getExerciseName(it.exerciseNameRes) == ExerciseName.DICTIONARY_ADJECTIVES && it.value >= DICTIONARY_ADJECTIVES_WORDS_NORM
        }

        val isDictionaryNounCompleteOverNorm = allExercisesValues.any {
            getExerciseName(it.exerciseNameRes) == ExerciseName.DICTIONARY_NOUN && it.value >= DICTIONARY_NOUNS_WORDS_NORM
        }

        if (commonInfo.dailyTrainingsCompleted > 0) {
            achieveAchievementsInteractor(AchievementName.FIRST_DAILY_TRAINING_COMPLETE)
        }

        if (trainingWithExercises.training.daysWithoutInterruption >= 7) {
            achieveAchievementsInteractor(AchievementName.SEVEN_DAILY_TRAININGS_IN_A_ROW_COMPLETE)
        }

        if (trainingWithExercises.training.daysWithoutInterruption >= 15) {
            achieveAchievementsInteractor(AchievementName.FIFTEEN_DAILY_TRAININGS_IN_A_ROW_COMPLETE)
        }

        if (trainingWithExercises.training.daysWithoutInterruption >= 30) {
            achieveAchievementsInteractor(AchievementName.THIRTY_DAILY_TRAININGS_IN_A_ROW_COMPLETE)
        }

        if (isTongueTwisterEasyCompleted && isTongueTwisterHardCompleted && isTongueTwisterVeryHardCompleted) {
            achieveAchievementsInteractor(AchievementName.ALL_TONGUE_TWISTERS_COMPLETE)
        }

        if (isAlphabetVerbsCompleted && isAlphabetNounsCompleted && isAlphabetAdjectivesCompleted) {
            achieveAchievementsInteractor(AchievementName.ALL_ALPHABET_EXERCISES_COMPLETE)
        }

        if (daysStatisticsDailyTrainingTimeEntities.any { it.summaryTrainingTimeMinutes > ONE_HOUR_IN_MINUTE }) {
            achieveAchievementsInteractor(AchievementName.SPENT_MORE_THEN_HOUR_ON_TRAINING)
        }

        if (isAtLeastOneDictionExerciseCompleted) {
            achieveAchievementsInteractor(AchievementName.FIRST_DICTION_COMPLETE)
        }

        if (isAtLeastOneCommunicatingExerciseCompleted) {
            achieveAchievementsInteractor(AchievementName.FIRST_IMPROVISATION_COMPLETE)
        }

        if (isAtLeastOneVocabularyExerciseCompleted) {
            achieveAchievementsInteractor(AchievementName.FIRST_VOCABULARY_COMPLETE)
        }

        if (isDictionaryVerbsCompleteOverNorm) {
            achieveAchievementsInteractor(AchievementName.DICTIONARY_VERBS_OVER_NORM)
        }

        if (isDictionaryNounCompleteOverNorm) {
            achieveAchievementsInteractor(AchievementName.DICTIONARY_NOUNS_OVER_NORM)
        }

        if (isDictionaryAdjectivesCompleteOverNorm) {
            achieveAchievementsInteractor(AchievementName.DICTIONARY_ADJECTIVES_OVER_NORM)
        }

        if (commonInfo.exercisesCompleted > 10) {
            achieveAchievementsInteractor(AchievementName.MORE_THEN_TEN_EXERCISES_COMPLETE)
        }

        if (commonInfo.exercisesCompleted > 50) {
            achieveAchievementsInteractor(AchievementName.MORE_THEN_FIFTY_EXERCISES_COMPLETE)
        }

        if (commonInfo.exercisesCompleted > 100) {
            achieveAchievementsInteractor(AchievementName.MORE_THEN_ONE_HUNDRED_EXERCISES_COMPLETE)
        }

        if (commonInfo.exercisesCompleted > 1000) {
            achieveAchievementsInteractor(AchievementName.MORE_THEN_ONE_THOUSAND_EXERCISES_COMPLETE)
        }
    }

    private fun getLevelInfo() {
        getStatisticsCommonInfoInteractor()
            .observeOn(AndroidSchedulers.mainThread())
            .map(::LevelInfoUi)
            .subscribe(_LevelInfo::setValue)
            .let(disposables::add)
    }

    private fun getStatisticInfoAndAchieveAchievements() {
        Single.zip(
            getStatisticsCommonInfoInteractor(),
            getAllExercisesStatisticsValueInteractor(),
            observeStatisticDaysInteractor().first(emptyList()),
            observeCurrentTrainingWithExercisesInteractor().firstOrError(),
            this::achieveAchievements
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .let(disposables::add)
    }


    override fun onCleared() {
        super.onCleared()
        if (disposables.isDisposed.not()) {
            disposables.dispose()
        }
    }
}