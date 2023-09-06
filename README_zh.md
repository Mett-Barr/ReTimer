# 重啟計時器
一個可以重啟的計時器，適用於延遲執行任務，直到一段時間的不活動後。

## 功能
- **可重啟**：使用 `startOrRestart` 重新開始計時器。
- **可取消**：使用 `cancel` 防止調用回撥。

## 如何使用
```kotlin
// 初始化您的 ReTimer
val timer = ReTimer(3000L, CoroutineScope(Dispatchers.Main), {
println("計時器時間到!")
})
// 開始或重啟
timer.startOrRestart()
// 取消
timer.cancel()
```