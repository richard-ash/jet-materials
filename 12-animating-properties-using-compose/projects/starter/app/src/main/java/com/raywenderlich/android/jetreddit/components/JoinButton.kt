package com.raywenderlich.android.jetreddit.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun JoinButton(onClick: (Boolean) -> Unit = {}) {
    var buttonState: JoinButtonState by remember { mutableStateOf(value = JoinButtonState.IDLE) }

    val shape = RoundedCornerShape(CornerSize(12.dp))
    val transition = updateTransition(targetState = buttonState, label = "JoinButtonTransition")
    val duration = 600
    val buttonBackgroundColor: Color by transition.animateColor(transitionSpec = {
        tween(
            durationMillis = duration
        )
    }, label = "Button Background Color") {
        when (it) {
            JoinButtonState.PRESSED -> Color.White
            JoinButtonState.IDLE -> Color.Blue
        }
    }
    val buttonWidth: Dp by transition.animateDp(
        transitionSpec = { tween(durationMillis = duration) },
        label = "Button Width"
    ) {
        when (it) {
            JoinButtonState.IDLE -> 70.dp
            JoinButtonState.PRESSED -> 32.dp
        }
    }
    val textMaxWidth: Dp by transition.animateDp(
        transitionSpec = { tween(durationMillis = duration) },
        label = "Text Max Width"
    ) {
        when (it) {
            JoinButtonState.IDLE -> 40.dp
            JoinButtonState.PRESSED -> 0.dp
        }
    }
    val iconTintColor: Color by transition.animateColor(
        transitionSpec = { tween(durationMillis = duration) },
        label = "Icon Tint Color"
    ) {
        when (it) {
            JoinButtonState.PRESSED -> Color.Blue
            JoinButtonState.IDLE -> Color.White
        }
    }
    val iconAsset: ImageVector
    val clickable: () -> Unit

    if (buttonState == JoinButtonState.PRESSED) {
        iconAsset = Icons.Default.Check
        clickable = {
            onClick.invoke(false)
            buttonState = JoinButtonState.IDLE
        }
    } else {
        iconAsset = Icons.Default.Add
        clickable = {
            onClick.invoke(true)
            buttonState = JoinButtonState.PRESSED
        }
    }

    Box(
        modifier = Modifier
            .clip(shape)
            .border(width = 1.dp, color = Color.Blue, shape = shape)
            .background(color = buttonBackgroundColor)
            .size(width = 40.dp, height = 24.dp)
            .clickable { clickable.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = iconAsset,
                contentDescription = "Plus Icon",
                tint = iconTintColor,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "Join",
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 1,
                modifier = Modifier
                    .widthIn(min = 0.dp, max = textMaxWidth)
            )
        }

    }
}

enum class JoinButtonState {
    IDLE,
    PRESSED
}

@Preview
@Composable
fun JoinButtonPreview() {
    JoinButton()
}