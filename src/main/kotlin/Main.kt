import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking {
    val timer = ReTimer(2000, CoroutineScope(Dispatchers.IO)) {
        println("Timer expired!")
    }

    println("Press Enter to start/restart timer. Timer will expire in 5 seconds if you don't press Enter again.")
    while (true) {
        readlnOrNull() // 等待用戶按下Enter鍵
        timer.startOrRestart()
        println("Timer restarted!")
    }
}