package ir.ahe.abbas.benchmark

import androidx.benchmark.macro.FrameTimingMetric
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.benchmark.perfetto.PerfettoConfig
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleStartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "ir.ahe.abbas.composeperformance",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }

    @Test
    fun testList() {
        benchmarkRule.measureRepeated(
            packageName = "ir.ahe.abbas.composeperformance",
            metrics = listOf(FrameTimingMetric()),
            iterations = 5,
            startupMode = StartupMode.COLD
        ) {
            startActivityAndWait()
            waitForComposeObject(LIST_ID, 3000)
            scrollForComposeObject(LIST_ID, Direction.DOWN, times = 1)
        }
    }


    private fun MacrobenchmarkScope.waitForComposeObject(resourceId: String, timeout: Long) {
        device.wait(Until.hasObject(By.res(resourceId)), timeout)
    }


    private fun MacrobenchmarkScope.scrollForComposeObject(
        resourceId: String,
        direction: Direction,
        times: Int = 12
    ) {
        try {
            val obj = device.findObject(By.res(resourceId)) ?: return
            if (direction == Direction.UP)
                obj.setGestureMargin(device.displayHeight / 5)

            repeat(times) {
                device.waitForIdle()
                obj.scroll(direction, PERCENT_VALUE, SPEED_VALUE)
            }


            device.waitForIdle()
        } catch (_: Exception) {
        }

    }

    companion object {
        private const val SPEED_VALUE = 10000
        private const val TIMEOUT_FOR_FINDING_OBJECT = 3000L
        private const val PERCENT_VALUE = 1.0f
        private const val LIST_ID = "list_id"
    }
}