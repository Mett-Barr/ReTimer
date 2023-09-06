import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ReTimerTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun testReTimerCallback() = runBlocking {
        var callbackInvoked = false

        val timer = ReTimer(1000L, this) {
            callbackInvoked = true
        }

        timer.startOrRestart()
        kotlinx.coroutines.delay(1100L)  // Wait slightly longer than the timer interval
        assertTrue(callbackInvoked, "The callback should have been invoked after 1 second")
    }

    @Test
    fun testReTimerCancel() = runBlocking {
        var callbackInvoked = false

        val timer = ReTimer(1000L, this) {
            callbackInvoked = true
        }

        timer.startOrRestart()
        timer.cancel()
        kotlinx.coroutines.delay(1100L)
        assertFalse(callbackInvoked, "The callback should not have been invoked because it was cancelled")
    }
}
