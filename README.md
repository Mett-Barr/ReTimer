# ReTimer
A restartable timer, ideal for deferring tasks until after a certain period of inactivity.
## Features
- *Restartable**: Restart the timer with startOrRestart.
- *Cancelable**: Prevent callback with cancel.
## How to use
```kotlin
// Initialize your ReTimer
val timer = ReTimer(3000L, CoroutineScope(Dispatchers.Main), {
println("Timer timed out!")
})
// Start or restart
timer.startOrRestart()
// Cancel
timer.cancel()
```