package com.uca.polifitnessapp.ui.calculator.ui


import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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


@Preview
@Composable
fun CalculatorScreen(){

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.white)
            )
            .verticalScroll(scrollState)
            .padding(30.dp, 0.dp, 30.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(40.dp))
        HeaderText()
        Spacer(modifier = Modifier.height(30.dp))

        CalculatorView( viewModel = CalculatorViewModel())

        Spacer(modifier = Modifier.height(90.dp))


    }
}




@Composable
fun HeaderText(

) {
        Text(
            text = "Calculadora IMC e ICC",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineSmall,
        )

}


@Composable
fun CalculatorView(viewModel: CalculatorViewModel = CalculatorViewModel()){

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {



        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
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

            Spacer(modifier = Modifier.width(10.dp))

            Image(
                painter = painterResource(id = R.drawable.calculator_header_img),
                contentDescription = null,
                modifier = Modifier
                    .width(139.dp)
                    .height(178.dp)
            )
        }


        GenderField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            viewModel.genderState,
            viewModel::updateGender
        )

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
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(),
        ) {

            CalculateButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = "Calcular",
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
            .width(48.dp)
            .clickable {  }
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
    modifier: Modifier,
    state: ValueState,
    onValueChange: (String) -> Unit,
) {


    var isExpanded by remember {
        mutableStateOf(false)
    }

    /*var gender by remember {
        mutableStateOf("")
    }*/


    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it}
    ) {
        TextField(
            value = state.value,
            onValueChange = {onValueChange(it)},
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
                    state.value = "Femenino"
                    isExpanded = false

                }
            )

            DropdownMenuItem(
                text = { Text(text = "Masculino")},
                onClick = {
                    state.value = "Masculino"
                    isExpanded = false

                }
            )

        }

    }

}




