package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.extensions.DateUnit
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.formatStd
import ru.skillbranch.devintensive.extensions.toUserView
import ru.skillbranch.devintensive.models.*
import java.time.LocalDateTime
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val user1 = User("1");
        val user2 = User("2", "John", "Wick");
        val user3 = User(
            "3", "John", "Silver",
            null, lastVisit = Date(), isOnline = true
        );

        user1.printMe()
        user2.printMe()
        user3.printMe()
    }

    @Test
    fun test_factory() {
        val user1 = User.makeUser("Sendel Const")
        val user2 = User.makeUser("Fallout RPG")
        val user3 = User.makeUser("")
        val user4 = user1.copy(id = "4", lastName = "Frank", lastVisit = Date())
        println(user1)
        println(user2)
        println(user3)
        println(user4)
    }

    @Test
    fun test_decomposition() {
        val user = User.makeUser("Sendel Fallout")
        val (id, firstName, lastName) = user

        println("$id, $firstName, $lastName")
        println("${user.component1()}, ${user.component2()}, ${user.component3()}")

    }

    @Test
    fun test_copy() {
        val user = User.makeUser("Sendel Fallout")
        val user2 = user.copy()

        if (user == user2) {
            println("equals\n ${user.hashCode()} $user \n ${user2.hashCode()} $user2")
        } else {
            println("not equals\n ${user.hashCode()} $user \n ${user2.hashCode()} $user2")
        }

        if (user === user2) {
            println("equals address ${System.identityHashCode(user)} ${System.identityHashCode(user2)}")
        } else {
            println(
                "not equals address ${System.identityHashCode(user)} ${System.identityHashCode(
                    user2
                )}"
            )
        }
    }

    @Test
    fun test_copy2() {
        val user = User.makeUser("Sendel Fallout")
        val user2 = user.copy(lastVisit = Date().add(3, DateUnit.MINUTE))
        val user3 = user.copy(lastName = "Warhammer", lastVisit = Date().add(10, DateUnit.HOUR))

        println(
            """
            ${user.lastVisit?.formatStd()} 
            ${user2.lastVisit?.formatStd()} 
            ${user3.lastVisit?.formatStd()}   
            """.trimIndent()
        )
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Константин Sendel")
        val newUser = user.copy(lastVisit = Date().add(-7, DateUnit.SECOND))
        println(newUser)

        val userView = newUser.toUserView()
        userView.printMe()

    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Константин Sendel")
        val txtMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any message", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "url", type = "image")

        println(txtMessage.formatMessage())
        println(imageMessage.formatMessage())

    }
}
