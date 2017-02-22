package domain

import domain.exception.MatchNotInChampionship
import domain.exception.UserNotInGroup
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertFailsWith

/**
 * @author claudio on 21/02/17.
 * @email <claudioed.oliveira@gmail.com>
 * surfacts
 *
 */
@RunWith(JUnitPlatform::class)
class PredictionSpec : Spek({

    describe("prediction unit tests") {

        val owner = User(UUID.randomUUID().toString(), "jao", "jao@jao.com")
        val friend = User(UUID.randomUUID().toString(), "manuel", "manuel@manuel.com")
        val matchOne = Match(UUID.randomUUID().toString(), "PALMEIRAS", "SANTOS", LocalDateTime.now().plusDays(7))
        val matchTwo = Match(UUID.randomUUID().toString(), "PALMEIRAS", "UNIAO", LocalDateTime.now().plusDays(14))
        val championship = Championship(UUID.randomUUID().toString(), listOf(matchOne, matchTwo))
        val group = Group("dobar", championship, owner)
        group.newUser(friend)

        on("add new user") {
            val colleague = User(UUID.randomUUID().toString(), "rui", "rui@rui.com")
            it("should add new user in current group") {
                assertTrue(group.newUser(colleague))
            }
        }

        on("user already in group") {
            val colleague = User("joaquim", "joaquim", "joaquim@joaquim.com")
            it("should false on second addition") {
                assertTrue(group.newUser(colleague))
                assertFalse(group.newUser(colleague))
            }
        }

        on("user not in group") {
            val colleague = User(UUID.randomUUID().toString(), "marcos", "marcos@marcos.com")
            it("should error because user not in group") {
                assertFailsWith<UserNotInGroup> {
                    group.receivePrediction(Prediction(matchOne.id, colleague.id, "1", "0"))
                }
            }
        }

        on("user in group and make a new prediction") {
            it("new prediction in group") {
                group.receivePrediction(Prediction(matchOne.id, friend.id, "1", "0"))
                assertTrue(group.predictions.count { friend.id == it.userId } > 0)
            }
        }

        on("user in group and make a new prediction on wrong match") {
            it("error cause match is not present") {
                assertFailsWith<MatchNotInChampionship> {
                    group.receivePrediction(Prediction(UUID.randomUUID().toString(), friend.id, "1", "0"))
                }
            }
        }

    }

})