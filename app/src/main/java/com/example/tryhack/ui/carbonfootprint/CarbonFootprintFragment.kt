package com.example.tryhack.ui.carbonfootprint

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.example.tryhack.Cotton
import com.example.tryhack.Material
import com.example.tryhack.ui.theme.TryHackTheme
import com.example.tryhack.R
import com.example.tryhack.Wool

class MaterialFragment : Fragment() {

    private val materials = listOf(
        Material("Cotton", R.drawable.cotton, 2.2),
        Material("Wool", R.drawable.wool, 3.2),
        Material("Cashmere", R.drawable.cashmere, 6.5),
        Material("Vegan Material", R.drawable.vegan, 1.5),
        Material("Recycled Polyester", R.drawable.rpet, 1.5)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                TryHackTheme {
                    MaterialScreen(materials)
                }
            }
        }
    }
}

@Composable
fun MaterialScreen(materials: List<Material>) {
    var searchQuery by remember { mutableStateOf("") } // State for search query
    var sortedMaterials by remember { mutableStateOf(materials) }
    var isAscending by remember { mutableStateOf(true) } // Track sorting order

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(start = 8.dp, top = 18.dp, end = 8.dp)
        ) {
            // Search Bar with Icon
            TextField(
                value = searchQuery,
                onValueChange = { query ->
                    searchQuery = query
                    // Filter materials based on the search query
                    sortedMaterials = if (query.isBlank()) {
                        materials
                    } else {
                        materials.filter { it.name.contains(query, ignoreCase = true) }
                    }
                },
                placeholder = { Text("Search materials...") },
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search), // Use your search icon resource here
                        contentDescription = "Search",
                    )
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {
                    isAscending = !isAscending // Toggle sorting order
                    sortedMaterials = if (isAscending) {
                        materials.sortedBy { it.carbonFootprint } // Sort ascending
                    } else {
                        materials.sortedByDescending { it.carbonFootprint } // Sort descending
                    }
                }) {
                    Text(if (isAscending) "Sort by Least Carbon Footprint" else "Sort by Most Carbon Footprint")
                }
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(top = 8.dp)
            ) {
                // Loop through materials and create list items
                items(sortedMaterials) { material ->
                    ListItem(material)
                }
            }
        }
    }
}

@Composable
fun ListItem(material: Material) {
    val context = LocalContext.current // Get the current context

    Card(
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
            .clickable {
                when (material.name) {
                    "Cotton" -> context.startActivity(Intent(context, Cotton::class.java))
                    "Wool" -> context.startActivity(Intent(context, Wool::class.java))
                }
            }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = material.imageRes),
                contentDescription = material.name,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = material.name,
                modifier = Modifier.padding(4.dp)
            )
            // Display the carbon footprint
            Text(
                text = "Carbon Footprint: ${material.carbonFootprint} kg CO2 per kg",
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}