package yaroslavgorbach.koropapps.vocabulary.feature.profile.level.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import yaroslavgorbach.koropapps.vocabulary.business.achievements.AchieveAchievementInteractor
import yaroslavgorbach.koropapps.vocabulary.business.achievements.ObserveAchievementsInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.GetAllExercisesStatisticsValueInteractor
import yaroslavgorbach.koropapps.vocabulary.business.statistics.GetStatisticsCommonInfoInteractor
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.Achievement
import yaroslavgorbach.koropapps.vocabulary.data.achievements.local.model.AchievementName
import yaroslavgorbach.koropapps.vocabulary.data.exercises.local.model.ExerciseName
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsCommonInfoEntity
import yaroslavgorbach.koropapps.vocabulary.data.statistics.local.model.StatisticsExerciseValueEntity
import yaroslavgorbach.koropapps.vocabulary.feature.profile.level.model.OratorLevelInfoUi
import javax.inject.Inject

class LevelViewModel @Inject constructor(
    private val getStatisticsCommonInfoInteractor: GetStatisticsCommonInfoInteractor,
    private val observeAchievementsInteractor: ObserveAchievementsInteractor,
    private val achieveAchievementsInteractor: AchieveAchievementInteractor,
    private val getAllExercisesStatisticsValueInteractor: GetAllExercisesStatisticsValueInteractor
) : ViewModel() {

    companion object {
        private const val ALPHABET_NUMBER_OF_LETTERS_FOR_ACHIEVEMENTS = 29
    }

    private val disposables: CompositeDisposable = CompositeDisposable()

    private val _oratorLevelInfo: MutableLiveData<OratorLevelInfoUi> = MutableLiveData()

    val oratorLevelInfoUi: LiveData<OratorLevelInfoUi>
        get() = _oratorLevelInfo

    val achievements: LiveData<List<Achievement>>
        get() = observeAchievementsInteractor().asLiveData()

    init {
        getLevelInfo()
        getStatisticInfoAndAchieveAchievements()
    }

    private fun achieveAchievements(
        commonInfo: StatisticsCommonInfoEntity,
        allExercisesValues: List<StatisticsExerciseValueEntity>
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

        if (commonInfo.dailyTrainingsCompleted > 0) {
            achieveAchievementsInteractor(AchievementName.FIRST_DAILY_TRAINING_COMPLETE)
        }

        if (commonInfo.dailyTrainingsCompleted > 6) {
            achieveAchievementsInteractor(AchievementName.SEVEN_DAILY_TRAININGS_COMPLETE)
        }

        if (isTongueTwisterEasyCompleted && isTongueTwisterHardCompleted && isTongueTwisterVeryHardCompleted) {
            achieveAchievementsInteractor(AchievementName.ALL_TONGUE_TWISTERS_COMPLETE)
        }

        if (isAlphabetVerbsCompleted && isAlphabetNounsCompleted && isAlphabetAdjectivesCompleted) {
            achieveAchievementsInteractor(AchievementName.ALL_ALPHABET_EXERCISES_COMPLETE)
        }
    }

    private fun getLevelInfo() {
        getStatisticsCommonInfoInteractor()
            .observeOn(AndroidSchedulers.mainThread())
            .map(::OratorLevelInfoUi)
            .subscribe(_oratorLevelInfo::setValue)
            .let(disposables::add)
    }

    private fun getStatisticInfoAndAchieveAchievements() {
        getStatisticsCommonInfoInteractor().zipWith(
            getAllExercisesStatisticsValueInteractor(),
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