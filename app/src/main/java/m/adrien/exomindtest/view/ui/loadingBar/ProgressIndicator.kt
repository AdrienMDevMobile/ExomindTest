package m.adrien.exomindtest.view.ui.loadingBar

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope

@Composable
fun ProgressIndicator(
    progress: Float, //paramètre obligatoire
    modifier: Modifier = Modifier, //premier paramètre optionel : convention
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color? = null,
    isLeftToRight: Boolean = true,
) {
    Log.d("progress" , "progress $progress")
    check(progress in 0f..1f) { "Invalid progress $progress" } //Permet de plus propremenet rejeter en cas d'erreur

    Canvas( //Canvas = zone de dessin
        modifier
            .progressSemantics(value = progress) //Gère l'animation appliquée au canvas
            .fillMaxHeight()
    ) {
        //Dessiner l'arrière plan
        backgroundColor?.let {
            drawSegments(1f, backgroundColor, size.height, isLeftToRight)
        }
        //Dessiner la zone à point
        drawSegments(progress, color, size.height, isLeftToRight)
    }
}

private fun DrawScope.drawSegments(
    progress: Float,
    color: Color,
    segmentHeight: Float,
    isLeftToRight: Boolean = true,
) {
    val width = size.width
    val start: Float
    val end: Float

    if (isLeftToRight) {
        start = 0f
        end = width * progress
    } else {
        start = width
        end = (width * progress) * (-1)
    }

    drawRect(
        color,
        Offset(start, 0f),
        Size(end, segmentHeight)
    )
}