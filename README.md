# ReTimer
A restartable timer suitable for deferring tasks or for tasks that require a reset of the timing.
## Features
- **Restartable**: Restart the timer with startOrRestart.
- **Cancelable**: Prevent callback with cancel.
## How to use
```kotlin
// Initialize your ReTimer
val timer = ReTimer(
    interval = 3000L, 
    scope = CoroutineScope(Dispatchers.Main),
    onStartOrRestart = {
        println("Timer Start!")
    },
    onTimeout = { 
        println("Timer timed out!") 
    })
// Start or restart
timer.startOrRestart()
// If needed, cancel the timer before it times out
timer.cancel()
```