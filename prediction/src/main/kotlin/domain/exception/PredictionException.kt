package domain.exception

/**
 * @author claudio on 21/02/17.
 * @email <claudioed.oliveira@gmail.com>
 * surfacts
 *
 */
class MatchNotInChampionship(val matchId:String): RuntimeException()

class UserNotInGroup(val userId:String,val groupId:String): RuntimeException()