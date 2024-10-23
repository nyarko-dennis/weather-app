package com.dev.dna.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.dna.weatherapp.ui.theme.WeatherAppTheme
import com.dev.dna.weatherapp.ui.theme.fontFamily

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                WeatherScreen()
            }
        }
    }
}

@Preview
@Composable
fun WeatherScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor("#59469d")),
                        Color(android.graphics.Color.parseColor("#643d37"))
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "Mostly Cloudy",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 48.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = fontFamily
                    )

                    Image(
                        painter = painterResource(id = R.drawable.cloudy_sunny),
                        contentDescription = "Cloud",
                        modifier = Modifier
                            .size(150.dp)
                            .padding(top = 8.dp),

                        )

                    // Display date and time
                    Text(
                        text = "Mon June 17 | 12:00 PM",
                        fontSize = 19.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = fontFamily
                    )

                    // Display temperature
                    Text(
                        text = "25°C",
                        fontSize = 63.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = fontFamily
                    )

                    // Display location
                    Text(
                        text = "H:27 L:18",
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        textAlign = TextAlign.Center,
                        fontFamily = fontFamily
                    )

                    // Box containing weather details like rain, wind, humidity
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 16.dp)
                            .background(
                                color = colorResource(id = R.color.purple),
                                shape = RoundedCornerShape(25.dp)
                            )
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp)
                                    .padding(horizontal = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                WeatherDetailItem(
                                    icon = R.drawable.rain,
                                    value = "20%",
                                    label = "Rain"
                                )

                                WeatherDetailItem(
                                    icon = R.drawable.wind,
                                    value = "5km/h",
                                    label = "Wind"
                                )

                                WeatherDetailItem(
                                    icon = R.drawable.humidity,
                                    value = "80%",
                                    label = "Humidity"
                                )
                            }
                        }

                    }

                }

                // Displaying hourly weather using lazy row
                item {
                    //Displaying Today label
                    Text(
                        text = "Today",
                        fontSize = 20.sp,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 8.dp),
                        fontFamily = fontFamily
                    )

                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(hourlyWeatherData.size) { index ->
                            HourlyWeatherItem(hourlyWeatherData = hourlyWeatherData[index])
                        }
                    }
                }

            }
        }
    }
}


@Composable
fun WeatherDetailItem(icon: Int, value: String, label: String) {
    Column(
        modifier = Modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(34.dp)
        )

        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = fontFamily,
            textAlign = TextAlign.Center
        )

        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = fontFamily,
            textAlign = TextAlign.Center
        )
    }
}

//Sample hourly weather data
val hourlyWeatherData = listOf(
    HourlyWeatherData("12:00 PM", "25°C", "sunny"),
    HourlyWeatherData("01:00 PM", "26°C", "rainy"),
    HourlyWeatherData("02:00 PM", "27°C", "cloudy"),
    HourlyWeatherData("03:00 PM", "28°C", "stormy"),
    HourlyWeatherData("04:00 PM", "29°C", "windy"),
    HourlyWeatherData("05:00 PM", "30°C", "sunny"),
    HourlyWeatherData("06:00 PM", "31°C", "rainy"),
    HourlyWeatherData("07:00 PM", "32°C", "cloudy"),
    HourlyWeatherData("08:00 PM", "33°C", "stormy"),
    HourlyWeatherData("09:00 PM", "34°C", "windy"),
    HourlyWeatherData("10:00 PM", "35°C", "sunny"),
    HourlyWeatherData("11:00 PM", "36°C", "rainy"),
    HourlyWeatherData("12:00 AM", "37°C", "cloudy"),
    HourlyWeatherData("01:00 AM", "38°C", "stormy"),
    HourlyWeatherData("02:00 AM", "39°C", "windy"),
    HourlyWeatherData("03:00 AM", "40°C", "sunny"),
    HourlyWeatherData("04:00 AM", "41°C", "rainy"),
    HourlyWeatherData("05:00 AM", "42°C", "cloudy"),
    HourlyWeatherData("06:00 AM", "43°C", "stormy"),
    HourlyWeatherData("07:00 AM", "44°C", "windy"),
    HourlyWeatherData("08:00 AM", "45°C", "sunny"),
    HourlyWeatherData("09:00 AM", "46°C", "rainy"),
    HourlyWeatherData("10:00 AM", "47°C", "cloudy"),
    HourlyWeatherData("11:00 AM", "48°C", "stormy")
)

val dailyWeatherData = listOf(
    DailyWeatherData("Tuesday", "25°C", "sunny"),
    DailyWeatherData("Wednesday", "26°C", "rainy"),
    DailyWeatherData("Thursday", "27°C", "cloudy"),
    DailyWeatherData("Friday", "28°C", "stormy"),
    DailyWeatherData("Saturday", "29°C", "windy"),
    DailyWeatherData("Sunday", "30°C", "sunny"),
    DailyWeatherData("Monday", "31°C", "rainy")
)

//View holder for hourly weather data
@Composable
fun HourlyWeatherItem(hourlyWeatherData: HourlyWeatherData) {
    Column(
        modifier = Modifier
            .width(90.dp)
            .wrapContentHeight()
            .padding(4.dp)
            .background(
                color = colorResource(id = R.color.purple),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = hourlyWeatherData.hour,
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = fontFamily,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(
                id = when (hourlyWeatherData.picPath) {
                    "cloudy" -> R.drawable.cloudy_sunny
                    "rainy" -> R.drawable.rain
                    "sunny" -> R.drawable.sunny
                    "windy" -> R.drawable.wind
                    "stormy" -> R.drawable.storm
                    else -> R.drawable.cloudy_sunny
                }
            ),
            contentDescription = "Cloud",
            modifier = Modifier
                .size(45.dp)
                .padding(8.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "${hourlyWeatherData.temperature}°",
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = fontFamily,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun DailyWeatherItem(dailyWeather: DailyWeatherData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = dailyWeather.day,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
        )
        Text(
            text = dailyWeather.temperature,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
        )
        Image(
            painter = painterResource(
                id = when (dailyWeather.weatherType) {
                    "cloudy" -> R.drawable.cloudy_sunny
                    "rainy" -> R.drawable.rain
                    "sunny" -> R.drawable.sunny
                    "windy" -> R.drawable.wind
                    "stormy" -> R.drawable.storm
                    else -> R.drawable.cloudy_sunny
                }
            ),
            contentDescription = dailyWeather.weatherType,
            modifier = Modifier.size(24.dp)
        )
    }
}

