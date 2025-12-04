package com.example.cursofirebaselite.Presentation.initial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cursofirebaselite.ui.theme.Black
import com.example.cursofirebaselite.ui.theme.Gray
import com.example.cursofirebaselite.R
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cursofirebaselite.ui.theme.BackgroundButton
import com.example.cursofirebaselite.ui.theme.Green
import com.example.cursofirebaselite.ui.theme.ShapeButton
import com.google.firebase.annotations.concurrent.Background

@Preview
@Composable
fun InitialScreen(navigateToLogin: () -> Unit = {}, navigateToSignUp: () -> Unit= {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Gray, Black), startY = 0f, endY = 600f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = R.drawable.spotify), contentDescription = "",
            Modifier.weight(0.3f)
        )

        Spacer(modifier = Modifier.weight(1f))
        Text(
            "Millions of Songs",
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "Free on Spotify",
            color = Color.White,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {navigateToSignUp()},
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text(text = "Sign Up Free", color = Black, fontWeight = Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            Modifier.clickable { },
            "Continue with Google",
            painterResource(id = R.drawable.google)
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            Modifier.clickable { },
            "Continue with Facebook",
            painterResource(id = R.drawable.facebook)
        )
        Text(
            text = "Log In",
            color = Color.White,
            modifier = Modifier.padding(24.dp). clickable{navigateToLogin()},
            fontWeight = Bold
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun CustomButton(modifier: Modifier, tittle: String, painter: Painter) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 32.dp)
            .background(BackgroundButton)
            .border(2.dp, ShapeButton, CircleShape),
        contentAlignment = Alignment.CenterStart
    ) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .padding(start = 16.dp)
                .size(20.dp)
        )
        Text(
            text = tittle,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = Bold
        )

    }
}


