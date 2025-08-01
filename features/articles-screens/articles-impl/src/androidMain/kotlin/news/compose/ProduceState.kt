package news.compose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay


@Composable
fun ProduceStateScreen(
) {
    val count by produceState(initialValue = 0) {


            value++

        awaitDispose {
           println(" awaitDispose")
        }
    }

    Text(text = "count = $count")


    val count2 = produceState( 0 ) {
        while (true) {
            delay(1000)
            value++
        }
    }
}