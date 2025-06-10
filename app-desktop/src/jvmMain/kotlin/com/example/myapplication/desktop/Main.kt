
import com.example.news.MainViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.context.startKoin
import ru.braveowlet.simple_mvi_example.core.network.networkModule
import java.awt.Color
import java.awt.Graphics
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities

fun main() {

    val panel = DrawingPanel()

    SwingUtilities.invokeLater {
        val frame = JFrame("Flow + Swing Demo")
        frame.setSize(800, 600)
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.contentPane.add(panel)
        frame.isVisible = true

        // CoroutineScope для UI-слоя
        val scope = CoroutineScope(Dispatchers.Default)
        val viewModel = MainViewModel()

        scope.launch {

           viewModel.stateInt.collectLatest { newX ->
                panel.xPos = newX
                panel.repaint()
            }
        }
    }
}


class DrawingPanel : JPanel() {
    var xPos: Int = 0

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.color = Color.RED
        g.fillOval(xPos, height / 2, 30, 30)
    }
}


private fun initKoin() {


    startKoin {
        modules(
            networkModule
        )
    }
}
