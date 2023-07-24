package com.example.testleveldb.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testleveldb.data.LevelDBViewModel
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelDBScreen(viewModel: LevelDBViewModel) {
    var key by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = key,
            onValueChange = { key = it },
            label = { Text("Key") }
        )

        TextField(
            value = value,
            onValueChange = { value = it },
            label = { Text("Value") }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = { viewModel.writeToDatabase(key, value) }
            ) {
                Icon(Icons.Default.Star, contentDescription = "Save")
                Text("Save")
            }

            Button(
                onClick = { result = viewModel.readFromDatabase(key) ?: "Key not found" }
            ) {
                Icon(Icons.Default.Star, contentDescription = "Read")
                Text("Read")
            }
        }

        Text("Result: $result", modifier = Modifier.padding(8.dp))
    }
}

@Preview
@Composable
fun PreviewLevelDBScreen() {
    LevelDBScreen(viewModel = viewModel())
}
