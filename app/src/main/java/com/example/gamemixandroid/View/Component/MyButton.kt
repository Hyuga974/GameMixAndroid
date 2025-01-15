package com.example.gamemixandroid.View.Component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.graphics.Shape
import androidx.compose.foundation.shape.RoundedCornerShape
import com.example.gamemixandroid.ui.theme.Primary
import com.example.gamemixandroid.ui.theme.Secondary

@Composable
fun CustomButton(
    text: String = "Test",
    onClick: () -> Unit,
    backgroundColor: Color = Secondary,
    textColor: Color = Color.White,
    fontSize: TextUnit = 18.sp,
    height: Int = 60,
    width : Float = 0.0f,
    cornerRadius: Int = 20,
    modif : Modifier = Modifier
) {

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth(if (width == 0.0f) 1.0f else width)
            .height(height.dp)
            .then(modif),
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor)
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            color = textColor,
            modifier = Modifier.then(modif)
        )
    }
}
