package com.example.carcollectionapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CarCollectionApp()
        }
    }
}

@Composable
fun CarCollectionApp() {
    var currentIndex by remember { mutableIntStateOf(0) }
    var showDetails by remember { mutableStateOf(false) }

    val currentCar = carList[currentIndex]

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Изображение
        Image(
            painter = painterResource(id = currentCar.imageResId),
            contentDescription = currentCar.title,
            modifier = Modifier
                .size(300.dp)
                .clip(RoundedCornerShape(16.dp))
                .clickable { showDetails = !showDetails }, // Долгое нажатие открывает описание
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Название
        Text(text = currentCar.title, fontSize = 24.sp)

        // Производитель и год выпуска
        Text(text = "Производитель: ${currentCar.manufacturer}", fontSize = 18.sp)
        Text(text = "Год выпуска: ${currentCar.year}", fontSize = 18.sp)

        // Детальное описание (отображается при нажатии на картинку)
        if (showDetails) {
            Text(
                text = currentCar.detailedInfo,
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопки "Предыдущий" и "Следующий"
        Row {
            Button(
                onClick = { if (currentIndex > 0) currentIndex-- },
                enabled = currentIndex > 0
            ) {
                Text("Предыдущий")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = { if (currentIndex < carList.size - 1) currentIndex++ },
                enabled = currentIndex < carList.size - 1
            ) {
                Text("Следующий")
            }
        }
    }
}

data class CarItem(
    val title: String,        // Название автомобиля
    val imageResId: Int,      // ID изображения в drawable
    val manufacturer: String, // Производитель
    val year: Int,            // Год выпуска
    val detailedInfo: String  // Описание
)

// Список машин с изображениями из drawable
val carList = listOf(
    CarItem("Ferrari LaFerrari", R.drawable.ferrari, "Ferrari", 2013, "Гибридный суперкар."),
    CarItem("Lamborghini Aventador", R.drawable.lamborghini, "Lamborghini", 2011, "Флагманский суперкар."),
    CarItem("Bugatti Chiron", R.drawable.bugatti, "Bugatti", 2016, "Гиперкар мощностью 1500 л.с."),
    CarItem("Tesla Model S Plaid", R.drawable.tesla, "Tesla", 2021, "Электрический седан с ускорением 0-100 км/ч за 2.1 секунды.")
)

@Preview(showBackground = true)
@Composable
fun PreviewCarCollectionApp() {
    CarCollectionApp()
}


