package domain

import infra.Specification

/**
 * @author claudio on 20/02/17.
 * @email <claudioed.oliveira@gmail.com>
 * surfacts
 *
 */
class ContainsMatch(val championship: Championship) : Specification<Prediction> {

    override fun isSatisfied(instance: Prediction): Boolean {
        return this.championship.containsMatch(instance.matchId)
    }

}

class ContainsUser(val group: Group) : Specification<Prediction> {

    override fun isSatisfied(instance: Prediction): Boolean {
        return this.group.users.count { instance.userId == it.id } > 0
    }

}