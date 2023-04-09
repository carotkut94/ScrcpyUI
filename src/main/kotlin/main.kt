import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import java.awt.Frame
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() = singleWindowApplication(
    title = "Scrcpy GUI",
    exitProcessOnExit = true,
    resizable = true,
    state = WindowState(size = DpSize(300.dp, 500.dp))
) {

    val commandExecutor = Runtime.getRuntime().exec("adb devices -l")
    val reader = BufferedReader(InputStreamReader(commandExecutor.inputStream))
    val devices = mutableListOf<MobileDevice>()
    // explicitly clear the application events
    reader.forEachLine { output ->
        if (output.contains("product")) {
            val commandOutPut = output.split(" ").toMutableList()
            commandOutPut.removeAll { it.isEmpty() }
            devices.add(
                MobileDevice(
                    commandOutPut[0],
                    "${commandOutPut[3].split(":")[1]} ${commandOutPut[4].split(":")[1]}"
                )
            )
        }
    }
    ExecutionFrame(devices) {
        window.state = Frame.ICONIFIED
    }
}


data class MobileDevice(val productId: String, val productName: String)
