package com.bqubique.noble_reminder.view

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WifiOff

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import com.bqubique.noble_reminder.ConnectivityObserver
import com.bqubique.noble_reminder.NetworkConnectivityObserver
import com.bqubique.noble_reminder.R
import com.bqubique.noble_reminder.theme.AppTheme
import com.bqubique.noble_reminder.view.home.HomeScaffold
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectivityObserver = NetworkConnectivityObserver(applicationContext)

        setContent {
            val networkStatus by connectivityObserver.observe()
                .collectAsState(initial = ConnectivityObserver.Status.Unavailable)

            NobleReminder(networkStatus = networkStatus)
        }


    }

//    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
//        if (event!!.action == MotionEvent.ACTION_SCROLL &&
//            event.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)
//        ) {
//            // Don't forget the negation here
//            val delta = -event.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
//                    ViewConfigurationCompat.getScaledVerticalScrollFactor(
//                        ViewConfiguration.get(baseContext), baseContext
//                    )
//
//            Log.i("MainActivity", delta.roundToInt().toString())
//
//            true
//        } else {
//            false
//        }
//
//        return super.onGenericMotionEvent(event)
//    }
}

@Composable
fun NobleReminder(networkStatus: ConnectivityObserver.Status) {

    AppTheme {
        if (networkStatus == ConnectivityObserver.Status.Unavailable) {
            NoConnectionAnimation()
        } else if (networkStatus == ConnectivityObserver.Status.Available) {
            HomeScaffold()
        }
    }
}

@Composable
fun NoConnectionAnimation() {
    val infiniteTransition = rememberInfiniteTransition()
    val formFactor = stringResource(id = R.string.form_factor)
    val isSquare by remember { mutableStateOf(isSquare(formFactor)) }

    val size by infiniteTransition.animateValue(
        initialValue = 50.dp,
        targetValue = 200.dp,
        animationSpec = infiniteRepeatable(tween(1000), repeatMode = RepeatMode.Reverse),
        typeConverter = Dp.VectorConverter
    )

    val color by infiniteTransition.animateColor(
        initialValue = Color.Black,
        targetValue = MaterialTheme.colors.primary,
        animationSpec = infiniteRepeatable(tween(1000), repeatMode = RepeatMode.Reverse),
    )


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Box(
            modifier = Modifier
                .clip(if (isSquare) RoundedCornerShape(30.dp) else CircleShape)
                .background(color)
                .size(size), contentAlignment = Alignment.Center
        ) {
            Box() {
                Icon(
                    Icons.Outlined.WifiOff,
                    contentDescription = "No connection available",
                    tint = Color.Black
                )
            }
        }

    }


}

private fun isSquare(resource: String): Boolean = resource.contains("Square")

