@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class,
)

package com.uca.polifitnessapp.ui.contactscreen.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.popupmessage.PopupMessageComposable
import com.uca.polifitnessapp.ui.popupmessage.SheetContent
import com.uca.polifitnessapp.ui.navigation.components.BackButton
import com.uca.polifitnessapp.ui.theme.md_theme_light_outline
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Composable
fun Contact(
    onBackPress: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        BackButton(
            modifier = Modifier
                .align(Alignment.Start)
                .padding(8.dp,16.dp,8.dp,16.dp)
            ,
            onBackPress
        )
        Spacer(modifier = Modifier.height(25.dp))

        ContactTitle()
        Spacer(modifier = Modifier.height(25.dp))

        ContactDescription()
        Spacer(modifier = Modifier.height(25.dp))


        SendEmail()
    }
}

@Composable
fun ContactTitle() {
    Text(
        modifier = Modifier
            .width(380.dp)
            .padding(20.dp, 0.dp),
        text = "¿Tienes alguna pregunta?",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun ContactDescription() {
    Text(
        modifier = Modifier
            .width(380.dp)
            .padding(20.dp, 0.dp),
        text = "Introduzca su dirección de correo electrónico y nos pondremos en contacto con usted si tiene algún problema.",
        fontWeight = FontWeight(236),
        fontSize = 12.sp,
        color = md_theme_light_outline
    )
}

@Composable
fun EmailField() {
    val textState = remember { mutableStateOf("") }

    TextField(
        value = textState.value,
        onValueChange = { newText ->
            textState.value = newText
        },
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.MailOutline,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Correo electrónico",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        modifier = Modifier
            .width(315.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)
        )
    )
}

@Composable
fun SendEmail() {

    val recipientEmailState = remember { mutableStateOf("") }
    val context = LocalContext.current
    var isCardVisible by remember { mutableStateOf(false) }
    val density = LocalDensity.current
    var isBottomSheetVisible by remember { mutableStateOf(false) }




    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = recipientEmailState.value,
            onValueChange = {
                recipientEmailState.value = it
            },
            shape = MaterialTheme.shapes.small,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.MailOutline,
                    contentDescription = "null",
                    tint = Color(0xFF565E71)
                )
            },
            label = {
                Text(
                    text = "Correo electrónico",
                    color = Color(0xFF565E71),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            },

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .width(315.dp),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF565E71),
                unfocusedBorderColor = Color.Transparent,
                containerColor = Color(0xFFD7E2FF)
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {

                val recipientEmail = recipientEmailState.value

                CoroutineScope(Dispatchers.IO).launch {
                    val result = runCatching { sendEmail(context,recipientEmailState.value)}

                    if(result.isSuccess){
                        sendEmail(context,recipientEmailState.value)
                        withContext(Dispatchers.Main){
                            run { isBottomSheetVisible = true }
                            Toast.makeText(context,"Correo enviado ", Toast.LENGTH_SHORT).show()
                        }

                    }else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(context, "Error al enviar correo ", Toast.LENGTH_SHORT)
                                .show()
                        }
                        result.exceptionOrNull()?.printStackTrace()
                    }

                }



            },
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 20.dp,
                pressedElevation = 10.dp,
                disabledElevation = 0.dp
            ),
            modifier = Modifier
                .width(315.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF034189)
            ),
        )
        {
            Text(
                text = "Enviar",
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }

        if (isBottomSheetVisible) {
            LaunchedEffect(Unit) {
                isBottomSheetVisible = true
            }

            SheetContent(

            )
        }


    }

}



fun sendEmail(context: Context, recipientEmail: String) {
    val username = "centrodeportivoapp@outlook.com"
    val password = "20R$03giFo17"

    val properties = Properties()
    properties["mail.smtp.auth"] = "true"
    properties["mail.smtp.starttls.enable"] = "true"
    properties["mail.smtp.host"] = "smtp-mail.outlook.com"
    properties["mail.smtp.port"] = "587"

    val session = Session.getInstance(properties, object : Authenticator() {
        override fun getPasswordAuthentication(): PasswordAuthentication {
            return PasswordAuthentication(username, password)
        }
    })

    try {
        val message = MimeMessage(session)
        message.setFrom(InternetAddress(username))
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail))
        message.subject = "Respuesta a tu solicitud de ayuda - Centro Deportivo App"
        message.setText(
            """Estimado/a,
Estimado/a,

Gracias por contactar a nuestro equipo de servicio de ayuda en Centro Deportivo App. Para brindarte la mejor asistencia posible, necesitamos obtener más detalles sobre tu consulta.

Por favor, proporciona una descripción detallada de tu consulta o problema. Si es relevante, adjunta capturas de pantalla para ayudarnos a entender mejor la situación. Asegúrate también de incluir tu información de contacto actualizada.

Una vez que recibamos esta información, nos dedicaremos a investigar tu caso y te brindaremos una solución satisfactoria en el menor tiempo posible.

Agradecemos tu colaboración y paciencia. Estamos aquí para ayudarte en todo momento.

Saludos cordiales,

Servicio de ayuda
Centro Deportivo App
centrodeportivoapp@support.com"""
        )

        Transport.send(message)

        println("Correo enviado exitosamente.")

    } catch (e: MessagingException) {
        e.printStackTrace()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SendEmailScreen() {
    val recipientEmailState = remember { mutableStateOf("") }
    val context = LocalContext.current
    var isCardVisible by remember { mutableStateOf(false) }


    Column {
        TextField(
            value = recipientEmailState.value,
            onValueChange = { recipientEmailState.value = it },
            label = { Text("Correo electrónico") }
        )

        Button(
            onClick = {
                val recipientEmail = recipientEmailState.value

                run { isCardVisible = true }

                CoroutineScope(Dispatchers.IO).launch {
                    sendEmail(context, recipientEmail)

                }

                Toast.makeText(context, "Enviado", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text("Enviar correo")
        }

    }
}

//TODO: Realizar el bottomSheet al enviar y averiguar como y donde enviaremos el email del usuario
