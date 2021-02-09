import androidx.compose.desktop.ComposePanel
import java.awt.Frame
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.swing.JFrame

fun main() {

    val commandExecutor = Runtime.getRuntime().exec("adb devices -l")
    val reader = BufferedReader(InputStreamReader(commandExecutor.inputStream))
    val devices = mutableListOf<MobileDevice>()

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

    val window = JFrame()
    val composeView = ComposePanel()
    with(window) {
        isResizable = false
        title = "Scrcpy Compose"
        contentPane.add(composeView)
        composeView.setContent {
            ExecutionFrame(devices) {
                window.state = Frame.ICONIFIED
            }
        }
        setSize(300, 600)
        isVisible = true
    }
}

data class MobileDevice(val productId: String, val productName: String)
