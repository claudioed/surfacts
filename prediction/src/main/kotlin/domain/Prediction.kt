package domain

import domain.exception.MatchNotInChampionship
import domain.exception.UserNotInGroup
import infra.Specification
import java.time.LocalDateTime

/**
 * @author claudio on 20/02/17.
 * @email <claudioed.oliveira@gmail.com>
 * surfacts
 *
 */
data class User(val id: String, val name: String, val email: String)

data class Prediction(val matchId: String, val userId: String, val home: String, val away: String)

data class RegisteredPrediction(val matchId: String, val userId: String, val home: String, val away: String, val registeredAt: LocalDateTime)

data class Match(val id: String, val home: String, val away: String, val at: LocalDateTime)

data class Championship(val id: String, val matches: List<Match>) {

    fun containsMatch(matchId: String): Boolean = matches.count { matchId == it.id } > 0

}

data class Group(val name: String, val championship: Championship, val owner: User) {

    val users: MutableSet<User> = mutableSetOf()

    val predictions: MutableSet<RegisteredPrediction> = mutableSetOf()

    val validUsersSpec: Specification<Prediction> = ContainsUser(this)

    val validMatchSpec: Specification<Prediction> = ContainsMatch(this.championship)

    fun receivePrediction(candidatePrediction: Prediction): Unit {
        if (validUsersSpec isSatisfied candidatePrediction) {
            if (validMatchSpec isSatisfied candidatePrediction) {
                this.predictions.add(RegisteredPrediction(candidatePrediction.matchId, candidatePrediction.userId, candidatePrediction.home, candidatePrediction.away, LocalDateTime.now()))
            } else {
                throw MatchNotInChampionship(candidatePrediction.matchId)
            }
        } else {
            throw UserNotInGroup(candidatePrediction.userId, candidatePrediction.matchId)
        }
    }

    fun newUser(user:User): Boolean = users.add(user)

}