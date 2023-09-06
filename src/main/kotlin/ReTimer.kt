import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.System


/**
 * A timer that can be restarted, useful for tasks that need to be deferred until
 * a certain period of inactivity (e.g., user input events).
 *
 * @property interval The timeout interval in milliseconds after which [onTimeout] will be triggered.
 * @property scope The [CoroutineScope] in which the timer will run.
 * @property onStartOrRestart The action to perform when the timer is started or restarted.
 * @property onTimeout The action to perform when the timer expires.
 */
class ReTimer(
    private val interval: Long,
    private val scope: CoroutineScope,
    var onStartOrRestart: () -> Unit,
    var onTimeout: () -> Unit
) {
    private var nextTimeoutTime = System.currentTimeMillis() + interval
    @Volatile private var isMonitoring = false
    @Volatile private var isCancelled = false

    /**
     * Starts or restarts the timer. If the timer is already running, it will be reset
     * to its initial state and then restarted. If not, it starts the timer.
     */
    fun startOrRestart() {
        isCancelled = false
        nextTimeoutTime = System.currentTimeMillis() + interval
        if (!isMonitoring) {
            isMonitoring = true
            scope.launch {
                delay(interval)
                monitorTimeout()
            }
        }
        onStartOrRestart()
    }

    /**
     * Monitors the timeout to check if the timer has expired.
     * If it has, [onTimeout] will be invoked (if not cancelled) and monitoring will stop.
     */
    private suspend fun monitorTimeout() {
        while (isMonitoring) {
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextTimeoutTime) {
                if (!isCancelled) {
                    onTimeout.invoke()
                }
                isMonitoring = false
            } else {
                delay(nextTimeoutTime - currentTime)
            }
        }
    }

    /**
     * Cancels the callback invocation. The timer will still reach its end, but [onTimeout]
     * will not be called once it does.
     */
    fun cancel() {
        isCancelled = true
    }
}