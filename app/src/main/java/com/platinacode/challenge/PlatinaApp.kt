package com.platinacode.challenge

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.platinacode.challenge.ui.theme.PlatinaCodingChallengeTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlatinaApp : Application() {

}