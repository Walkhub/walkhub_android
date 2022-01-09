package com.semicolon.data

import org.junit.Before
import org.mockito.MockitoAnnotations

abstract class BaseTest {
    @Before
    open fun initTest() {
        MockitoAnnotations.openMocks(this)
        init()
    }

    abstract fun init()
}