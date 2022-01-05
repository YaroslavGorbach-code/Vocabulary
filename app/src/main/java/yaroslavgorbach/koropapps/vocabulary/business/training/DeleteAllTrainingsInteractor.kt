package yaroslavgorbach.koropapps.vocabulary.business.training

import yaroslavgorbach.koropapps.vocabulary.data.training.repo.RepoTraining

class DeleteAllTrainingsInteractor(private val repoTraining: RepoTraining) {
    operator fun invoke() = repoTraining.clearTrainings()
}