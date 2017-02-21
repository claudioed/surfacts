package domain

import infra.Specification

/**
 * @author claudio on 20/02/17.
 * @email <claudioed.oliveira@gmail.com>
 * surfacts
 *
 */
class MatchInChampionship(val championship: Championship) : Specification<MatchResult> {

    override fun isSatisfied(instance: MatchResult): Boolean {
        return championship.matches.count {instance.matchId == it.id} > 0
    }

}