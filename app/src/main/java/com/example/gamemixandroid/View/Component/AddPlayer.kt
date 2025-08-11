package com.example.gamemixandroid.View.Component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamemixandroid.ViewModel.SetGameViewModel
import com.example.gamemixandroid.ui.theme.NoName
import com.example.gamemixandroid.ui.theme.Secondary



@Composable
fun AddPlayer(
    viewModel: SetGameViewModel = viewModel(),
    maxPlayers: Int = 10,
    newPlayerName: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onClick: @Composable () -> Unit,
    modif: Modifier
) {
    Row(
        modifier = modif.fillMaxWidth(0.8f)
            .semantics { contentDescription = "Ajout d'un joueur" },
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = newPlayerName,
            onValueChange = onValueChange,
            placeholder = { Text("Nom du nouveau joueur") },
            singleLine = true,
            modifier = Modifier
                .weight(1f)
                .testTag("TextField_AddPlayer")
                .semantics { contentDescription = "Champ texte : entrer le nom du joueur" },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = NoName,
                unfocusedContainerColor = NoName,
                errorContainerColor = Color.Red,
            ),
        )

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = {
                viewModel.addPlayer(newPlayerName.text, maxPlayers)
                onValueChange(TextFieldValue())
            },
            modifier = Modifier
                .sizeIn(minWidth = 48.dp, minHeight = 48.dp) // Accessibilité tactile
                .size(50.dp)
                .testTag("Button_AddPlayer")
                .semantics { contentDescription = "Bouton : ajouter le joueur" },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                Secondary,
                disabledBackgroundColor = Secondary.copy(alpha = 0.5f)
            ),
            enabled = newPlayerName.text.isNotBlank() && viewModel.players.size < maxPlayers
        ) {
            Text("+", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}