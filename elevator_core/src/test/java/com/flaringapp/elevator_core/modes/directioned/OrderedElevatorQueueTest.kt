package com.flaringapp.elevator_core.modes.directioned

import com.flaringapp.elevator_core.person.PersonId
import org.junit.Before

import org.junit.Test
import org.junit.Assert.*

class OrderedElevatorQueueTest {

    private var queue = OrderedElevatorQueue()

    @Before
    fun setUp() {
        queue = OrderedElevatorQueue()
    }

    @Test
    fun `Empty queue has size 0`() {
        val size = queue.size

        assertEquals(0, size)
    }

    @Test
    fun `Queue has correct size after few persons have entered`() {
        queue.enter(PersonId(1))
        queue.enter(PersonId(2))

        val size = queue.size

        assertEquals(2, size)
    }

    @Test
    fun `Queue has correct size after few persons have entered and left`() {
        queue.enter(PersonId(1))
        queue.enter(PersonId(2))
        queue.exit()

        val size = queue.size

        assertEquals(1, size)
    }

    @Test
    fun `Queue has correct size after few persons have entered and all left`() {
        queue.enter(PersonId(1))
        queue.enter(PersonId(2))
        queue.exit()
        queue.exit()

        val size = queue.size

        assertEquals(0, size)
    }

    @Test
    fun `Queue is initially empty`() {
        val isEmpty = queue.isEmpty()

        assertTrue(isEmpty)
    }

    @Test
    fun `Queue is not empty after adding a person`() {
        queue.enter(PersonId(1))

        assertFalse(queue.isEmpty())
    }

    @Test
    fun `Queue is empty after adding and removing a person`() {
        queue.enter(PersonId(1))
        queue.exit()

        assertTrue(queue.isEmpty())
    }

    @Test
    fun `Head of queue is first person entered the queue`() {
        queue.enter(PersonId(1))
        queue.enter(PersonId(2))

        val head = queue.head()

        assertEquals(head, PersonId(1))
    }

    @Test
    fun `First person entered the queue is first to exit the queue`() {
        queue.enter(PersonId(1))
        queue.enter(PersonId(2))

        val personLeft = queue.exit()

        assertEquals(personLeft, PersonId(1))
    }
}