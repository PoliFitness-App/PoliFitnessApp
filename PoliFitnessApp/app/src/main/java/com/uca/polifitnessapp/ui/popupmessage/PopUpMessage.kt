package com.uca.polifitnessapp.ui.popupmessage


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uca.polifitnessapp.R
import kotlinx.coroutines.launch



//Pop-Up cards composable function with parameters to change the content of the card

@Composable
fun PopupMessageComposable(
    painterResource: Painter,
    title: String,
    titleColor: Color,
    description: String,
    cardColors: CardColors ,
    buttonColor: ButtonColors,
    buttonText: String,
){

    Card(
        modifier = Modifier
            .width(412.dp)
            .height(850.dp),
        shape = RoundedCornerShape(60.dp, 60.dp, 0.dp, 0.dp),
        colors = cardColors,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 0.dp, 20.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {

            Image(
                painter = painterResource,
                contentDescription = null,
            )

            Text(text = title,
                color = titleColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(200.dp)

            )



            Text(
                text = description,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(303.dp)
            )


            Spacer(modifier = Modifier.height(10.dp))



            Button(
                onClick = { "/*TODO*/ "},
                shape = RoundedCornerShape(10.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 10.dp,
                    disabledElevation = 0.dp
                ),
                modifier = Modifier
                    .width(315.dp)
                    .height(56.dp),
                colors = buttonColor,

                )
            {
                Text(
                    text = buttonText,
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }


    }
}

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun viewScreen(){

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState() // Crear el BottomSheetScaffoldState

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = { SheetContent() } // Contenido del BottomSheet

    ){

    }



}


// Content of the BottomSheet
@Composable
fun SheetContent(){
    PopupMessageComposable(
        painterResource = painterResource(id = R.drawable.errorimage),
        title = stringResource(R.string.title_error_card),
        titleColor = MaterialTheme.colorScheme.error,
        description = stringResource(R.string.description_error_card),
        cardColors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
        buttonColor = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
        buttonText =  stringResource(R.string.button_text_error_card)
    )

}






@Preview(showBackground = true)
@Composable
fun PopUpCardScreen(){

    // Error Card
    PopupMessageComposable(
        painterResource = painterResource(id = R.drawable.errorimage),
        title = stringResource(R.string.title_error_card),
        titleColor = MaterialTheme.colorScheme.error,
        description = stringResource(R.string.description_error_card),
        cardColors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
        buttonColor = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
        buttonText =  stringResource(R.string.button_text_error_card)
    )

    //Profile updated successfully

    PopupMessageComposable(
        painterResource = painterResource(id = R.drawable.updatedimagecard),
        title = stringResource(R.string.title_updated_profile_card),
        titleColor = MaterialTheme.colorScheme.primary,
        description = stringResource(R.string.description_updated_card),
        cardColors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        buttonColor = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        buttonText =  "Listo"
    )

    //Contact message sent successfully

    PopupMessageComposable(
        painterResource = painterResource(id = R.drawable.contactcardimage),
        title = stringResource(R.string.title_contact_card),
        titleColor = MaterialTheme.colorScheme.primary,
        description = stringResource(R.string.contact_card_description),
        cardColors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        buttonColor = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        buttonText =  "Listo"
    )



}





// Content of the BottomSheetScaffold
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Content(

)
{
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                coroutineScope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            }
        ) {
            Text(text = "Abrir BottomSheet")
        }
    }
}



