package domain

import domain.exception.MatchNotInChampionship
import java.time.LocalDateTime

/**
 * @author claudio on 20/02/17.
 * @email <claudioed.oliveira@gmail.com>
 * surfacts
 *
 */
data class Team(val id: String, val simpleName: String, val name: String, val country: String)

data class Period(val start: LocalDateTime, val end: LocalDateTime)

data class Match(val id: String, val home: Team, val away: Team, val at: LocalDateTime)

data class MatchResult(val matchId: String, val homeResult: String, val awayResult: String)

data class Championship(val duration: Period, val name: String, val matches: List<Match>) {

    val results: MutableList<MatchResult> = mutableListOf()

    fun addResult(result: MatchResult): Unit {
        if(MatchInChampionship(this) isSatisfied result){
           results.add(result)
        }else{
            throw MatchNotInChampionship(result.matchId)
        }
    }


}