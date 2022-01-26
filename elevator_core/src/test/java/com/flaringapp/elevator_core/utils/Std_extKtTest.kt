package com.flaringapp.elevator_core.utils

import org.junit.Test
import org.junit.Assert.*
import java.util.concurrent.locks.ReentrantLock

class Std_extKtTest {

    @Test
    fun `MutableMap_removeAll removes all entries by selector`() {
        val map = mutableMapOf(
            1 to "Test1",
            2 to "Test2",
            3 to "Test3",
            4 to "4",
            5 to "5",
        )

        map.removeAll { _, value ->
            value.contains("Test")
        }

        assertEquals(2, map.size)
        assertTrue(map.containsKey(4))
        assertTrue(map.containsKey(5))
        assertEquals("4", map[4])
        assertEquals("5", map[5])
    }

    @Test
    fun `MutableMap_retailAll retains all entries by selector`() {
        val map = mutableMapOf(
            1 to "Test1",
            2 to "Test2",
            3 to "Test3",
            4 to "4",
            5 to "5",
        )

        map.retainAll { _, value ->
            value.contains("Test")
        }

        assertEquals(3, map.size)
        assertTrue(map.containsKey(1))
        assertTrue(map.containsKey(2))
        assertTrue(map.containsKey(3))
        assertEquals("Test1", map[1])
        assertEquals("Test2", map[2])
        assertEquals("Test3", map[3])
    }

    @Test
    fun `Lock_use actually locks the lock`() {
        val lock = ReentrantLock()
        lock.use {
            assert(lock.isLocked)
        }
        assert(!lock.isLocked)
    }
}