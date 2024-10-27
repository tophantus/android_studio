package com.example.appsetting

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    val dataStorePreferences = DataStorePreferences(context)
    val scope = rememberCoroutineScope()

    val wakeForMeals by dataStorePreferences.wakeForMeals.collectAsState(initial = false)
    val favoriteCity by dataStorePreferences.favoriteCity.collectAsState(initial = "")
    val travelClass by dataStorePreferences.travelClass.collectAsState(initial = "Economy")
    val notificationsEnabled by dataStorePreferences.notificationsEnabled.collectAsState(initial = false)

    val travelClassOptions = listOf("Economy", "Business", "First Class")
    var travelClassExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(16.dp)) {
            Text(text = "Flight Preferences", style = MaterialTheme.typography.headlineLarge)

            //dùng các preference screens
            
            CheckboxPreference(
                title = "Wake for meals",
                isChecked = wakeForMeals,
                onCheckedChange = {
                    scope.launch {
                        dataStorePreferences.setWakeForMeals(it)
                    }
                },
                summary = if (wakeForMeals) "You will be woken for meals." else "You will not be woken for meals."
            )

            TextFieldPreference(
                title = "Favorite city",
                value = favoriteCity,
                onValueChange = {
                    scope.launch {
                        dataStorePreferences.setFavoriteCity(it)
                    }
                },
                label = "Favorite city",
                summary = "Your favorite city is $favoriteCity"
            )

            DropdownPreference(
                title = "Travel Class",
                selectedValue = travelClass,
                onValueChange = {
                    scope.launch {
                        dataStorePreferences.setTravelClass(it)
                    }
                },
                options = travelClassOptions,
                expanded = travelClassExpanded,
                onExpandedChange = { travelClassExpanded = it },
                summary = "You have selected $travelClass class."
            )

            SwitchPreference(
                title = "Enable notifications",
                isChecked = notificationsEnabled,
                onCheckedChange = {
                    scope.launch {
                        dataStorePreferences.setNotificationsEnabled(it)
                    }
                },
                summary = "Turn notifications on or off."
            )
        }
    }
}
