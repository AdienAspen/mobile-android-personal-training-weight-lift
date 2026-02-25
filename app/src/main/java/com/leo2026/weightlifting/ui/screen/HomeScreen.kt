package com.leo2026.weightlifting.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leo2026.weightlifting.data.entity.UserEntity
import com.leo2026.weightlifting.ui.viewmodel.WorkoutViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomeScreen(
    viewModel: WorkoutViewModel,
    onStartWorkout: () -> Unit,
    onNavigateToHistory: () -> Unit
) {
    val user by viewModel.userProfile.collectAsState()
    val completedSessions by viewModel.completedSessions.collectAsState()
    var showRegistration by remember { mutableStateOf(false) }

    LaunchedEffect(user) {
        if (user == null) {
            showRegistration = true
        }
    }

    val motivationalPhrases = remember {
        listOf(
            "La consistencia es la clave para desatar todo tu potencial. Una repetición a la vez.",
            "El único entrenamiento malo es el que no se hizo.",
            "Tu cuerpo puede soportar casi cualquier cosa. Es a tu mente a la que tienes que convencer.",
            "La fuerza no viene de ganar. Tus luchas desarrollan tus fortalezas.",
            "La acción es la clave fundamental de todo éxito.",
            "No te detengas cuando estés cansado. Detente cuando hayas terminado.",
            "Lo difícil no es poner tu cuerpo en forma. Lo difícil es poner tu mente en forma.",
            "Un campeón es alguien que se levanta cuando no puede.",
            "Lo que duele hoy te hace más fuerte mañana.",
            "El reloj sigue corriendo. ¿Te estás convirtiendo en la persona que quieres ser?",
            "Tanto si piensas que puedes como si piensas que no puedes, tienes razón.",
            "El éxito comienza con la autodisciplina.",
            "No tienes que ser extremo, solo consistente.",
            "La disciplina es hacer lo que hay que hacer, incluso si no quieres hacerlo.",
            "Las cosas grandes nunca vienen de las zonas de confort.",
            "Exígete a ti mismo, porque nadie más lo va a hacer por ti.",
            "El éxito es lo que sucede después de haber sobrevivido a todos tus errores.",
            "La única persona que deberías intentar ser mejor es la persona que eras ayer.",
            "Tu futuro se crea por lo que haces hoy, no mañana.",
            "La motivación es lo que te pone en marcha. El hábito es lo que te mantiene.",
            "No disminuyas la meta. Aumenta el esfuerzo.",
            "El sudor es simplemente grasa llorando.",
            "Estás a un entrenamiento de distancia de un buen estado de ánimo.",
            "Si no te desafía, no te cambiará.",
            "La diferencia entre lo imposible y lo posible reside en la determinación de una persona.",
            "Hoy haré lo que otros no harán, para que mañana pueda lograr lo que otros no pueden.",
            "El fitness no trata de ser mejor que otra persona. Trata de ser mejor de lo que eras ayer.",
            "El valor de un hombre se mide por lo que hace cuando nadie lo ve.",
            "Ve un paso más allá. Nunca hay tráfico en la milla extra.",
            "Pierdes el 100% de los tiros que no haces.",
            "Piensa en tus entrenamientos como reuniones importantes que programaste contigo mismo. Los jefes no cancelan.",
            "Para ser el número uno, tienes que entrenar como si fueras el número dos.",
            "El trabajo duro vence al talento cuando el talento no trabaja duro.",
            "Cree en ti mismo y en todo lo que eres.",
            "La única forma de hacer un gran trabajo es amar lo que haces.",
            "No se trata de tener tiempo, se trata de hacerse tiempo.",
            "Definición de un entrenamiento realmente bueno: cuando odias empezarlo, pero amas terminarlo.",
            "Tu salud es una inversión, no un gasto.",
            "La fuerza es el producto de la lucha. Debes hacer lo que otros no para lograr lo que otros no podrán.",
            "El único límite para nuestra realización de mañana serán nuestras dudas de hoy.",
            "Cree que puedes y estarás a mitad de camino.",
            "Haz algo hoy que tu yo del futuro te agradezca.",
            "La voluntad de ganar, el deseo de triunfar, el impulso de alcanzar tu máximo potencial... estas son las claves para la excelencia personal.",
            "Empieza donde estás. Usa lo que tienes. Haz lo que puedes.",
            "Establecer metas es el primer paso para convertir lo invisible en visible.",
            "No mires el reloj; haz lo que él hace. Sigue adelante.",
            "Mantén tus ojos en las estrellas y tus pies en la tierra.",
            "Nunca eres demasiado viejo para establecer otra meta o soñar un nuevo sueño.",
            "Todo lo que siempre has querido está al otro lado del miedo.",
            "El éxito es caminar de fracaso en fracaso sin perder el entusiasmo.",
            "El poder de la imaginación nos hace infinitos.",
            "Cualquier cosa que valga la pena tener requiere trabajo duro.",
            "No cuentes los días, haz que los días cuenten.",
            "La paciencia y la perseverancia tienen un efecto mágico ante el cual las dificultades desaparecen.",
            "No mires hacia atrás, no vas por ese camino.",
            "Si quieres algo que nunca has tenido, debes estar dispuesto a hacer algo que nunca has hecho.",
            "Cáete siete veces, levántate ocho.",
            "Pequeños progresos cada día suman grandes resultados.",
            "Transforma tu 'no puedo' en 'puedo'.",
            "Tu única competencia es el espejo."
        )
    }

    val randomPhrase = remember { motivationalPhrases.random() }

    Scaffold(
        containerColor = Color(0xFF121212),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1E1E1E))
                    .padding(16.dp)
            ) {
                Text(
                    text = "RENDIMIENTO",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFFF6D00)
                )
                Text(
                    text = user?.let { "${it.firstName} ${it.lastName}" } ?: "INVITADO",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Alcanza tus\nmetas hoy.",
                    style = MaterialTheme.typography.displayMedium,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
                )
            }

            item {
                Button(
                    onClick = onStartWorkout,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00)),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Black)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        "INICIAR ENTRENAMIENTO",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black,
                        color = Color.Black
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "HISTORIAL",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    TextButton(onClick = onNavigateToHistory) {
                        // View All -> Ver Todo
                        Text("VER TODO", color = Color(0xFFFF6D00))
                    }
                }
            }

            if (completedSessions.isEmpty()) {
                item {
                    Text(
                        "Aún no hay sesiones registradas.",
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            } else {
                items(completedSessions.take(2)) { session ->
                    val date = SimpleDateFormat("dd MMM", Locale.getDefault()).format(Date(session.startTime))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color(0xFFFF6D00))
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(session.name, fontWeight = FontWeight.Bold, color = Color.White)
                                Text(date, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E).copy(alpha = 0.5f)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFFF6D00).copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = "“",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFFFF6D00).copy(alpha = 0.5f),
                            modifier = Modifier.height(30.dp)
                        )
                        Text(
                            text = randomPhrase,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Medium,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                            color = Color.White,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
            
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }
    }

    if (showRegistration) {
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { },
            containerColor = Color(0xFF1E1E1E),
            titleContentColor = Color(0xFFFF6D00),
            title = { Text("BIENVENIDO A LEO-2026") },
            text = {
                Column {
                    Text("Por favor identifícate para personalizar tu experiencia.", color = Color.White, modifier = Modifier.padding(bottom = 16.dp))
                    TextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text("Nombre") },
                        colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Apellido") },
                        colors = TextFieldDefaults.colors(focusedContainerColor = Color.Transparent, unfocusedContainerColor = Color.Transparent)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (firstName.isNotBlank() && lastName.isNotBlank()) {
                            viewModel.updateProfile(firstName, lastName)
                            showRegistration = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D00), contentColor = Color.Black)
                ) {
                    Text("REGISTRAR", fontWeight = FontWeight.Black)
                }
            }
        )
    }
}
