package com.example.angelpractica1.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.angelpractica1.R
import com.example.angelpractica1.model.MainActivityModel

class MainActivity : ComponentActivity() {
    val dato: MainActivityModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Componentes()
        }
    }
    @Composable
    fun Componentes(){
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(all = 30.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Cotizador de autos nuevos", fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(30.dp))
            add_nombre(dato = dato)
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Marca", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                add_marca(dato = dato)
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Enganche", fontSize = 19.sp, fontWeight = FontWeight.Bold)
                add_enganche(dato = dato)
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(text = "Financiamiento (Anual)", fontSize = 19.sp, fontWeight = FontWeight.Bold)
                add_financiamiento(dato = dato)
            }

            Spacer(modifier = Modifier.size(10.dp))

            add_datos(dato = dato)


        }

    }

    @Composable
    fun add_nombre(dato: MainActivityModel) {
        var _nombre = remember {
            mutableStateOf("")
        }
        Column(
            Modifier.fillMaxWidth(),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Nombre", fontSize = 19.sp, fontWeight = FontWeight.Bold )
                TextField(
                    value = _nombre.value, onValueChange = {
                        _nombre.value = it
                        dato.setName(_nombre.value)
                    },
                    Modifier.width(250.dp),
                    keyboardOptions = KeyboardOptions(KeyboardCapitalization.None, true, KeyboardType.Text),
                )
            }

        }

    }

    @Composable
    fun add_marca(dato: MainActivityModel) {
        var expanded by remember { mutableStateOf(false) }
        val labels = listOf(
            "Honda Accord $678,026.22","Vw Touareg $879,266.15","BMW X5 $1,025,366.87","Mazda CX7 $988,641.02")
        Box {
            Button(onClick = { expanded = !expanded },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text("Marca")
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = true },
            ) {
                labels.forEach { label ->
                    DropdownMenuItem(onClick = {
                        dato.setMarca(labels.indexOf(label))
                        expanded = false
                    }
                    ) {
                        Text(text = label)
                    }
                }
            }
        }
    }
    @Composable
    fun add_enganche(dato: MainActivityModel) {
        var expanded by remember { mutableStateOf(false) }
        val labels = listOf("20%", "40%", "60%")
        Box {
            Button(onClick = { expanded = !expanded },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text("Porcentaje")
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = true },
            ) {
                labels.forEach { label ->
                    DropdownMenuItem(onClick = {
                        dato.setPorcentaje(labels.indexOf(label))
                        expanded = false
                    }
                    ) {
                        Text(text = label)
                    }
                }
            }
        }
    }
    @Composable
    fun add_financiamiento(dato: MainActivityModel) {
        var expanded by remember { mutableStateOf(false) }
        val labels = listOf("1 a??o al 7.5%", "2 a??os al 9.5%", "3 a??os al 10.3%", "4 a??os al 12.6%", "5 a??os al 13.5%")
        Box {
            Button(onClick = { expanded = !expanded },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text("Plazo")
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = true },
            ) {
                labels.forEach { label ->
                    DropdownMenuItem(onClick = {
                        dato.setFinanciamiento(labels.indexOf(label))
                        expanded = false
                    }
                    ) {
                        Text(text = label)
                    }
                }
            }
        }
    }
}

@Composable
fun add_datos(dato: MainActivityModel) {

    Card(
        Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Nombre : ")
                Text(text = "${dato.nombre.value.toString()}", fontWeight = FontWeight.Bold)
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start

            ) {
                Text(text = "Veh??culo : ")
                Text(text = "${dato.marca.value.toString()}", fontWeight = FontWeight.Bold)

            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Enganche : ")
                Text(
                    text = "(${dato.porcentaje.value.toString()}%) de $ ${dato.enganche.value.toString()}",
                    fontWeight = FontWeight.Bold
                )

            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Monto a financiar: ")
                Text(
                    text = "$ ${dato.financiamiento.value.toString()}",
                    fontWeight = FontWeight.Bold
                )

            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Financiamiento a ")
                Text(text = "${dato.plazo.value}", fontWeight = FontWeight.Bold)

            }
            Text(text = "Monto de intereses por ${dato.anios.value.toString()} a??os: ")
            Text(text = "$ ${dato.interes.value.toString()}", fontWeight = FontWeight.Bold)
            Text(text = "Monto a financiar + intereses: ")
            Text(text = "$ ${dato.financiamiento.value.toString()} + $ ${dato.interes.value.toString()} = ${dato.total.value.toString()}", fontWeight = FontWeight.Bold)
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Pagos mensuales por: ")
                Text(
                    text = "$ ${dato.pagomensual.value.toString()}",
                    fontWeight = FontWeight.Bold
                )

            }
            Text(text = "Costo total ( Enganche + Financiamiento ): ")
            Text(text = "$ ${dato.enganche.value.toString()} + $ ${dato.total.value.toString()} = $ ${dato.enganche.value + dato.total.value}", fontWeight = FontWeight.Bold)
        }
    }
}

