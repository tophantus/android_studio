package com.example.test

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun changeText_sameActivity() {
        val mStringToBeTyped = "Espresso"

        // Set content để hiển thị UI cần kiểm thử
        composeTestRule.setContent {
            MyApp()
        }

        // Tìm thành phần UI bằng testTag và thực hiện hành động
        composeTestRule.onNodeWithTag("editTextUserInput")
            .performTextInput(mStringToBeTyped)
        composeTestRule.onNodeWithTag("changeTextBt").performClick()

        // Kiểm tra kết quả
        composeTestRule.onNodeWithTag("textToBeChanged")
            .assertTextEquals(mStringToBeTyped)
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.test", appContext.packageName)
    }
}

