package com.semicolon.walkhub.ui.splash

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.semicolon.walkhub.R
import com.semicolon.walkhub.ui.HomeActivity
import com.semicolon.walkhub.ui.MainActivity
import com.semicolon.walkhub.ui.base.Title2
import com.semicolon.walkhub.viewmodel.splash.SplashViewModel
import kotlinx.coroutines.launch

@Composable
fun Splash() {
    val viewModel: SplashViewModel = hiltViewModel()
    viewModel.autoLogin()
    Splash(viewModel)
}


@Composable
fun Splash(
    viewModel: SplashViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.eventFlow.collect {
                when (it) {
                    SplashViewModel.Event.AutoLoginSuccess -> startMainActivity(context)
                    SplashViewModel.Event.NeedLogin -> startHomeActivity(context)
                }
            }
        }
    }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val image = rememberImagePainter(
            request = ImageRequest.Builder(context)
                .data(R.drawable.logo_ok)
                .build()
        )
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                modifier = Modifier.size(180.dp),
                painter = image,
                contentDescription = null
            )
            Column {
                Spacer(Modifier.size(142.dp))
                Title2(
                    text = "Walkhub",
                    color = Color.White
                )
            }
        }
    }
}

fun startHomeActivity(context: Context) {
    val intent = Intent(context, HomeActivity::class.java)
    context.startActivity(intent)
}

fun startMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun PreviewSplash() {
    Splash()
}