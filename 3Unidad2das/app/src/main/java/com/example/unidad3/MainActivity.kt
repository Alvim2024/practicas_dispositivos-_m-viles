package com.example.unidad3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// -------------------- MAIN ACTIVITY --------------------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PantallaPrincipal()
        }
    }
}

@Composable
fun PantallaPrincipal() {
    val context = LocalContext.current
    var texto by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Intents",
            style = MaterialTheme.typography.headlineMedium
        )

        // -------- Intent implícito: Navegador --------
        Button(onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com")
            )
            context.startActivity(intent)
        }) {
            Text("Navegador")
        }

        // -------- Intent implícito: Mapa --------
        Button(onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:19.4326,-99.1332")
            )
            context.startActivity(intent)
        }) {
            Text("Mapa")
        }

        // -------- Intent implícito: WhatsApp --------
        Button(onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://wa.me/5210000000000")
            )
            context.startActivity(intent)
        }) {
            Text("WhatsApp")
        }

        // -------- Nombre del alumno --------
        Text(text = "Pepe")

        // -------- Caja de texto --------
        OutlinedTextField(
            value = texto,
            onValueChange = { texto = it },
            label = { Text("Escribe un mensaje") }
        )

        // -------- Intent explícito: Segunda Activity --------
        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("mensaje", texto)
            context.startActivity(intent)
        }) {
            Text("Saludar")
        }

        // -------- Intent explícito: Tercera Activity --------
        Button(onClick = {
            val intent = Intent(context, ThirdActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Respuesta")
        }
    }
}

// -------------------- SECOND ACTIVITY --------------------
class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mensaje = intent.getStringExtra("mensaje") ?: ""

        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Mensaje recibido:")
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mensaje,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}

// -------------------- THIRD ACTIVITY --------------------
class ThirdActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "¡Saludo recibido correctamente!",
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        }
    }
}
