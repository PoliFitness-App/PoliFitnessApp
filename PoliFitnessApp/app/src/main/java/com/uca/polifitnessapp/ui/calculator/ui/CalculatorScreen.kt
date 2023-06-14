package com.uca.polifitnessapp.ui.calculator.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Height
import androidx.compose.material.icons.outlined.Male
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.Straighten
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uca.polifitnessapp.R
import com.uca.polifitnessapp.ui.calculator.viewmodel.CalculatorViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculatorScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .padding(30.dp, 0.dp, 20.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.width(90.dp))
        CalculatorView(viewModel = CalculatorViewModel())


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HeaderText( viewModel: CalculatorViewModel = CalculatorViewModel()){
    Column {

        Text(
            text = "Calculadora IMC e ICC",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .padding(16.dp, 8.dp, 16.dp, 8.dp)
                .align(Alignment.Start),
        )

        
    }
}

@Composable
fun HeaderImageCards( viewModel: CalculatorViewModel = CalculatorViewModel()
){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {


        CalculatorCard(viewModel = viewModel)

        Spacer(modifier = Modifier.width(20.dp))

        Image(
            painter = painterResource(id = R.drawable.calculator_header_img),
            contentDescription = null,
            modifier = Modifier
                .width(139.dp)
                .height(178.dp)
        )
        
    }
}


@Composable
fun CalculatorCard( viewModel: CalculatorViewModel = CalculatorViewModel()){

    Column() {

        //IMC CARD
        ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .height(80.dp)
                .width(152.dp),
            elevation = CardDefaults.elevatedCardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "%.2f".format(viewModel.bmi),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 16.sp,
                )

                //
                Text(text = viewModel.messageIbm,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .padding(0.dp, 8.dp, 0.dp, 0.dp),
                    fontSize = 14.sp,
                )

                //
                Text(text = "IMC",
                    fontWeight = FontWeight.ExtraLight,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    textAlign = TextAlign.Center
                )
            }

        }

        //ICC CARD
        ElevatedCard(
            modifier = Modifier
                .padding(10.dp)
                .height(80.dp)
                .width(152.dp),
            elevation = CardDefaults.elevatedCardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(10.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "%.2f".format(viewModel.icc),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 16.sp,
                )

                //
                Text(text = viewModel.messageIcc,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .padding(0.dp, 8.dp, 0.dp, 0.dp),
                    fontSize = 14.sp,
                )

                //
                Text(text = "ICC",
                    fontWeight = FontWeight.ExtraLight,
                    color = MaterialTheme.colorScheme.scrim,
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(8.dp, 8.dp, 8.dp, 8.dp),
                    textAlign = TextAlign.Center
                )
            }

        }

    }

}






@Composable
fun CalculatorView(viewModel: CalculatorViewModel = CalculatorViewModel()){

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(25.dp, 0.dp, 0.dp, 0.dp)
    ) {

        HeaderText()


        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {


            // Tarjetas con los resultados IMC e ICC

            Column() {

                //IMC CARD
                ElevatedCard(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(80.dp)
                        .width(152.dp),
                    elevation = CardDefaults.elevatedCardElevation(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(text = "%.2f".format(viewModel.bmi),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 16.sp,
                        )

                        //
                        Text(text = viewModel.messageIbm,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.tertiary,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .padding(0.dp, 8.dp, 0.dp, 0.dp),
                            fontSize = 14.sp,
                        )

                        //
                        Text(text = "IMC",
                            fontWeight = FontWeight.ExtraLight,
                            color = MaterialTheme.colorScheme.scrim,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(8.dp, 8.dp, 8.dp, 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                }

                //ICC CARD
                ElevatedCard(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(80.dp)
                        .width(152.dp),
                    elevation = CardDefaults.elevatedCardElevation(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(10.dp),
                ){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(text = "%.2f".format(viewModel.icc),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 16.sp,
                        )

                        //
                        Text(text = viewModel.messageIcc,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.tertiary,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier
                                .padding(0.dp, 8.dp, 0.dp, 0.dp),
                            fontSize = 14.sp,
                        )

                        //
                        Text(text = "ICC",
                            fontWeight = FontWeight.ExtraLight,
                            color = MaterialTheme.colorScheme.scrim,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(8.dp, 8.dp, 8.dp, 8.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                }

            }

            Spacer(modifier = Modifier.width(20.dp))

            Image(
                painter = painterResource(id = R.drawable.calculator_header_img),
                contentDescription = null,
                modifier = Modifier
                    .width(139.dp)
                    .height(178.dp)
            )

        }


        /*GenderField(
            gender
        )*/

        //BirthdayField(birthdate, onDateChange = {})


        Row(horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically,) {

            weightField(
                modifier = Modifier.align(Alignment.CenterVertically),
                viewModel.weightState,
                ImeAction.Next,
                viewModel::updateWeight
            )
            kgicon()

        }

        Row(horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically,) {

            heightField(
                modifier = Modifier.align(Alignment.CenterVertically),
                viewModel.heightState,
                ImeAction.Next,
                viewModel::updateHeight
            )
            cmicon()

        }

        Row(horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically,) {

            waistField(
                modifier = Modifier.align(Alignment.CenterVertically),
                viewModel.waistState,
                ImeAction.Next,
                viewModel::updateWaist
            )
            cmicon()
        }

        Row(horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically,) {

            hipField(
                modifier = Modifier.align(Alignment.CenterVertically),
                viewModel.hipState,
                ImeAction.Done,
                viewModel::updateHip
            )
            cmicon()
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(16.dp, 12.dp)
                .fillMaxWidth(),
        ) {
            CalculateButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "Calculate",
                onClick = {
                    coroutineScope.launch {
                        viewModel.calculate()
                    }
                }
            )

        }




    }

}



// -----------

// HEADER IMAGE

// ------------


@Composable
fun HeaderImage(
    modifier: Modifier
){
    Box(
        modifier = Modifier
            .size(200.dp)
    ){
        Image(painter = painterResource(id = R.drawable.editprofileimg),
            modifier = Modifier.fillMaxSize(),
            alignment = Alignment.Center,
            contentDescription = "Imagen " )
    }

}

// -----------

// HEADER TEXT

// ------------




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun weightField(
    modifier: Modifier,
    state: ValueState,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit
){
    val focusManager = LocalFocusManager.current

    TextField(
        value = state.value,
        isError = state.error != null,
        onValueChange = { onValueChange(it) },
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.MonitorWeight,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Peso",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction),
        modifier = modifier
            .width(240.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)

        )
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun heightField(
    modifier: Modifier,
    state: ValueState,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit
){
    val focusManager = LocalFocusManager.current

    TextField(
        value = state.value,
        isError = state.error != null,
        onValueChange = { onValueChange(it) },
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Height,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Altura",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction),
        modifier = modifier
            .width(240.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)

        )
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun waistField(
    modifier: Modifier,
    state: ValueState,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit
){
    TextField(
        value = state.value,
        isError = state.error != null,
        onValueChange = { onValueChange(it) },
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Straighten,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Perímetro de cintura",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction),
        modifier = modifier
            .width(240.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)

        )
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun hipField(
    modifier: Modifier,
    state: ValueState,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit
){
    TextField(
        value = state.value,
        isError = state.error != null,
        onValueChange = { onValueChange(it) },
        shape = MaterialTheme.shapes.small,
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Straighten,
                contentDescription = "null",
                tint = Color(0xFF565E71)
            )
        },
        label = {
            Text(
                text = "Perímetro de cadera",
                color = Color(0xFF565E71),
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = imeAction),
        modifier = modifier
            .width(240.dp),
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFF565E71),
            unfocusedBorderColor = Color.Transparent,
            containerColor = Color(0xFFD7E2FF)

        )
    )

}

@Composable
fun kgicon(){
    ElevatedCard(
        modifier = Modifier
            .height(56.dp)
            .width(48.dp),
        shape = RoundedCornerShape(10.dp),colors = CardDefaults.cardColors( containerColor = Color(0xFF034189))
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "KG", color = Color.White)

        }

    }
}

@Composable
fun cmicon(){
    ElevatedCard(
        modifier = Modifier
            .height(56.dp)
            .width(48.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors( containerColor = Color(0xFF034189))
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "CM", color = Color.White)

        }

    }
}



@Composable
fun CalculateButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
)
{
    val focusManager = LocalFocusManager.current

    Button(
        onClick = { focusManager.clearFocus(); onClick()},
        shape = RoundedCornerShape(10.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 20.dp,
            pressedElevation = 10.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier
            .width(315.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF034189)
        ),
    )
    {
        Text(
            text,
            fontSize = 16.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )

    }


}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenderField(
    gender: String,
    onGenderChange: (String) -> Unit
) {


    var isExpanded by remember {
        mutableStateOf(false)
    }

    var gender by remember {
        mutableStateOf("")
    }

    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it}
    ) {
        TextField(
            value = gender,
            onValueChange = {onGenderChange(it)},
            shape = MaterialTheme.shapes.small,
            readOnly = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Male,
                    contentDescription = "null",
                    tint = Color(0xFF565E71)
                )
            },
            label = {
                Text(
                    text = "Escoge tu genero",
                    color = Color(0xFF565E71),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF565E71),
                unfocusedBorderColor = Color.Transparent,
                //containerColor = Color(0xFFD7E2FF)

            ),
            modifier = Modifier
                .menuAnchor()
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFFD7E2FF))
                .width(300.dp)
        )



        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
        ) {
            DropdownMenuItem(
                text = { Text(text = "Femenino")},
                onClick = {
                    gender = "Femenino"
                    isExpanded = false

                }
            )

            DropdownMenuItem(
                text = { Text(text = "Masculino")},
                onClick = {
                    gender = "Masculino"
                    isExpanded = false

                }
            )

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayField(
    dateCalendar: String,
    onDateChange: (String) -> Unit) {

    val openDialog = remember { mutableStateOf(false) }
    var date by remember { mutableStateOf(dateCalendar) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())

    if(openDialog.value) {
        DatePickerDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        date = SimpleDateFormat("dd/MM/yyyy").format(datePickerState.selectedDateMillis)
                            .toString()
                    },
                ) {
                    Text(
                        text = "Aceptar",
                        color = Color(0xFF034189)
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text(
                        text = "Cancelar",
                        color = Color(0xFF034189)
                    )
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = Color(0xFF034189),
                    todayDateBorderColor = Color(0xFF034189),
                )
            )

        }

    }
    LaunchedEffect(date){
        onDateChange(date)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { openDialog.value = true },
    ){
        TextField(
            value = date,
            onValueChange = {},
            shape = MaterialTheme.shapes.small,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = "null",
                    tint = Color(0xFF565E71)
                )
            },
            label = {
                Text(
                    text = "Fecha de nacimiento",
                    color = Color(0xFF565E71),
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            },
            modifier = Modifier
                .pointerInput(Unit) {}
                .width(300.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                ) { openDialog.value = true },
            readOnly = true,
            enabled = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF565E71),
                unfocusedBorderColor = Color.Transparent,
                containerColor = Color(0xFFD7E2FF)

            )
        )
    }



}



