package ui

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.awt.Desktop
import java.net.URI

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExecutableNotFoundDialog(showDialog: Boolean, setShowDialog: (Boolean) -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text("Error")
            },
            text = {
                Text("This app depends upon scrcpy command line utility, which is not yet installed on this system, or it is not enlisted in system path!")
            },
            confirmButton = {
                Button(
                    onClick = {
                        Desktop.getDesktop().browse(URI.create("https://github.com/Genymobile/scrcpy"))
                        setShowDialog(false)
                    },
                ) {
                    Text("Resolve!")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        // Change the state to close the dialog
                        setShowDialog(false)
                    },
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}
