package domain

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

    val users: MutableList<User> = mutableListOf()

    val predictions: MutableList<RegisteredPrediction> = mutableListOf()

    fun addPrediction(candidatePrediction: Prediction): Unit {
        if(ContainsUser(this).isSatisfied(candidatePrediction)){
            if (ContainsMatch(this.championship).isSatisfied(candidatePrediction)){
                this.predictions.add(RegisteredPrediction(candidatePrediction.matchId,candidatePrediction.userId,candidatePrediction.home,candidatePrediction.away, LocalDateTime.now()))
            }else{

            }
        }else{

        }
    }

}