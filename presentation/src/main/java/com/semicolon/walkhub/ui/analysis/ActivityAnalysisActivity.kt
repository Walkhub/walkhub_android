package com.semicolon.walkhub.ui.analysis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.usecase.user.PostUserSignInUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ActivityAnalysisActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityAnalysis {
                finish()
            }
        }
    }
}
