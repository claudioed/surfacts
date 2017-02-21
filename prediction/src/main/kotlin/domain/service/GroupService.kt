package domain.service

import domain.Group
import java.time.LocalDateTime

/**
 * @author claudio on 20/02/17.
 * @email <claudioed.oliveira@gmail.com>
 * surfacts
 *
 */
interface GroupService {

    fun viewGroup(groupId:String,userId:String,date:LocalDateTime):Group

    fun myGroups(userId:String,date:LocalDateTime):Group

}