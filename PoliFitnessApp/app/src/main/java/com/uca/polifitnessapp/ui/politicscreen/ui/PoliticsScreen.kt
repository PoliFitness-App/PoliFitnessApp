@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
)

package com.uca.polifitnessapp.ui.politicscreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.navigation.components.BackButton
import com.uca.polifitnessapp.ui.theme.md_theme_light_outline
import com.uca.polifitnessapp.ui.theme.md_theme_light_scrim


@Composable
fun privacyPoliticsScreen(
    onBackPress: () -> Unit,
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {

        item {
            BackButton(
                modifier = Modifier,
                onBackPress
            )
            Spacer(modifier = Modifier.height(25.dp))
            politicTittle()
            Spacer(modifier = Modifier.height(25.dp))
            politicsDescription()
            Spacer(modifier = Modifier.height(25.dp))
            infoCollectedTittle()
            Spacer(modifier = Modifier.height(25.dp))
            infoCollectedTittleDescription()
            Spacer(modifier = Modifier.height(25.dp))
            infoUsageTittle()
            Spacer(modifier = Modifier.height(25.dp))
            infoUsageDescription()
            Spacer(modifier = Modifier.height(25.dp))
            infoProtectionTittle()
            Spacer(modifier = Modifier.height(25.dp))
            infoProtectionDescritpion()
            Spacer(modifier = Modifier.height(25.dp))
            changePoliticsTittle()
            Spacer(modifier = Modifier.height(25.dp))
            changePoliticsDescription()
            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

@Composable
fun politicTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Política de privacidad de Centro Deportivo App",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun politicsDescription() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "La aplicación \"Centro Deportivo App\" es propiedad de la Universidad Centroamericana José Simeón Cañas (UCA) con sede  en Bulevar Los Próceres, Antiguo Cuscatlán, La Libertad, El Salvador, Centroamérica. Si tiene alguna pregunta sobre nuestras políticas de privacidad, puede comunicarse con nosotros por correo electrónico a politnessuca@gmail.com",
        fontWeight = FontWeight(236),
        fontSize = 12.sp,
        color = md_theme_light_outline
    )
}

@Composable
fun infoCollectedTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "La aplicación \"Centro Deportivo  App\" recopila algunos datos personales de los usuarios",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun infoCollectedTittleDescription() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Centro Deportivo App recopila información personal del usuario como: su altura, genero, fecha de nacimiento, peso, indice de masa corporal, perimetro de cintura y cadera; Además otros datos relacionados a las cuentas de los usuarios como: el nombre y apellido del usuario y su dirección de correo electrónico",
        fontWeight = FontWeight(236),
        fontSize = 12.sp,
        color = md_theme_light_outline
    )
}

@Composable
fun infoUsageTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Finalidad de la recopilación de datos",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun infoUsageDescription() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Los datos personales se recopilan con el fin de proporcionar rutinas personalizadas, calcular el índice de masa corporal y proporcionar noticias sobre el polideportivo.",
        fontWeight = FontWeight(236),
        fontSize = 12.sp,
        color = md_theme_light_outline
    )
}

@Composable
fun infoProtectionTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Protección de datos de los usuarios",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun infoProtectionDescritpion() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Como encargados de la protección de la información personal de nuestros usuarios" +
                "no compartiremos ningún tipo de información personal con terceros sin el consentimiento" +
                "de los usuarios, excepto cuando sea requerido por la ley.",
        fontWeight = FontWeight(236),
        fontSize = 12.sp,
        color = md_theme_light_outline
    )
}

@Composable
fun changePoliticsTittle(){
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Cambios en nuestra política de privacidad",
        style = TextStyle(fontWeight = FontWeight.Bold),
        fontSize = 20.sp,
    )
}

@Composable
fun changePoliticsDescription() {
    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Cada vez que se actualice nuestra política de privacidad te notificaremos" +
                "de los cambios realizados",
        fontWeight = FontWeight(236),
        fontSize = 12.sp,
        color = md_theme_light_outline
    )

    Spacer(modifier = Modifier.height(25.dp))

    Text(modifier = Modifier
        .fillMaxWidth()
        .padding(35.dp, 0.dp),
        text = "Si tienes alguna pregunta o inquietud sobre nuestra política de privacidad, no dudes en contactarnos." ,
        fontWeight = FontWeight(236),
        fontSize = 12.sp,
        color = md_theme_light_outline
    )
}
