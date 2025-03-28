package m.adrien.exomindtest.view.ui.loadingBar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CheckMarker(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Canvas( //Canvas = zone de dessin
        modifier
            .progressSemantics(value = progress) //Gère l'animation appliquée au canvas
            .fillMaxHeight()
    ) {
        drawMyCircle(progress, color)
        drawCheckSign(progress, color) //top = 10f, bottom = size.height - 30
    }
}

private fun DrawScope.drawCheckSign(
    progress: Float,
    color: Color,
    start: Float = 0f,
    end: Float = size.width,
    top: Float = size.height * 0.1f,
    bottom: Float = size.height - size.height * 0.1f,
) {
    val length = end - start
    val height = bottom - top
    val firstTriangleHorizontalOffSet = length * 0.15f
    val firstTriangleVerticalOffSet = height * 0.1f
    val secondTriangleOverTheTop = height * 0.2f
    val barWidth = height * 0.05f
    val secondTriangleHorizontalOffset = length / 2
    val rectangleHorizontalOffset = length * 0.1f


    clipPath(
        Path().apply {

            moveTo(start, top -2) // déplacez-vous vers le coin supérieur gauche
            lineTo(end, top -2) // ligne vers le coin supérieur droit
            lineTo(
                end / 2 - firstTriangleHorizontalOffSet,
                bottom - firstTriangleVerticalOffSet
            ) // ligne vers le presque milieu du bas
            close() // fermer la forme pour compléter le triangle
            moveTo(start, top + secondTriangleOverTheTop) //triangle gauche
            lineTo(start, bottom+2)
            lineTo(end / 2 - firstTriangleHorizontalOffSet - barWidth, bottom+2)
            close()
            moveTo(end, top + barWidth*2)
            lineTo(end, bottom)
            lineTo(end / 2 - barWidth*2, bottom+2)
            close()
        },
        ClipOp.Difference
    ) {
        drawRect(
            color,
            Offset(start + rectangleHorizontalOffset, top),
            Size(((end - start) - rectangleHorizontalOffset) * progress, bottom - top)
        )
    }
}

private fun DrawScope.drawMyCircle(
    progress: Float,
    color: Color,
) {
    val sweep = 360 * progress

    clipPath(
        Path().apply {
            addOval(
                Rect(
                    //Offset(size.width/2, size.height/2),
                    Offset(size.width * 0.05f, size.height * 0.05f),
                    Size(size.width * 0.9f, size.height * 0.9f)
                )
            )
        },
        ClipOp.Difference
    ) {
        drawArc(color, 0.9f, sweep, true)
    }
}

@Preview
@Composable
fun previewCheck() {
    Surface(color = Color.Cyan, modifier = Modifier.size(50.dp)) {
        CheckMarker(progress = 1f)
    }
}

@Preview
@Composable
fun previewCheckBig() {
    Surface(color = Color.Cyan, modifier = Modifier.size(150.dp)) {
        CheckMarker(progress = 1f)
    }
}

@Preview
@Composable
fun previewCheckHalf() {
    Surface(color = Color.Cyan, modifier = Modifier.size(50.dp)) {
        CheckMarker(progress = 0.5f)
    }
}