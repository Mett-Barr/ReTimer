[英文](README.md) | [中文](README_zh.md)
# 重啟計時器
一個可以重啟的計時器，適用於延遲執行任務，或是需要重新計時的任務。

## 功能
- **可重啟**：使用 `startOrRestart` 重新啟動計時器。
- **可取消**：透過 `cancel` 來避免執行回撥。

## 如何使用
```kotlin
// 初始化您的重啟計時器
val timer = ReTimer(
    timeoutIntervalMs = 3000L, // 注意這裡的參數名稱
    scope = CoroutineScope(Dispatchers.Main),
    onStartOrRestart = {
        println("計時器開始!")
    },
    onTimeout = { 
        println("計時器時間到!") 
    })

// 啟動或重新啟動計時器
timer.startOrRestart()

// 如果需要的話，可以在計時器時間到之前取消
timer.cancel()
```