package com.sanapplications.jetkart.presentation.screens.profile_screen.component

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.HorizontalAnchorable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.sanapplications.jetkart.presentation.common.component.DefaultBackArrow
import com.sanapplications.jetkart.presentation.ui.theme.TextColor
import com.sanapplications.jetkart.R
import com.sanapplications.jetkart.domain.model.AuthViewModel
import com.sanapplications.jetkart.presentation.common.CustomDefaultBtn
import com.sanapplications.jetkart.presentation.graphs.Graph
import com.sanapplications.jetkart.presentation.graphs.auth_graph.AuthScreen
import com.sanapplications.jetkart.presentation.ui.theme.PrimaryColor
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

@Composable
fun ProfileScreen(
    navController: NavController
) {

    // Get the AuthViewModel instance
    val authViewModel: AuthViewModel = viewModel()

    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val storageRef = FirebaseStorage.getInstance().reference

    // Launcher to pick image from gallery
    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri
        uri?.let { authViewModel.uploadImageToFirebase(it, storageRef, authViewModel) }
    }

    // UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(0.5f)) {
                DefaultBackArrow {
                    navController.popBackStack()
                }
            }
            Box(modifier = Modifier.weight(0.7f)) {
                Text(
                    text = "Profile",
                    color = MaterialTheme.colors.TextColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val (image, cameraIcon) = createRefs()
            Image(
                painter = rememberAsyncImagePainter(authViewModel.userImageUrl),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .constrainAs(image) {
                        linkTo(start = parent.start, end = parent.end)
                    }
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.constrainAs(cameraIcon) {
                bottom.linkTo(image.bottom)
                end.linkTo(image.end)

            }) {

                IconButton(onClick = {  pickImageLauncher.launch("image/*")  }) {
                    Icon(
                        painter = painterResource(id = R.drawable.camera_icon),
                        contentDescription = "Change Picture",
                        modifier = Modifier.background(Color.LightGray)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(40.dp))

        TextField(
            value = authViewModel.userFirstName,
            onValueChange = { newName ->
                authViewModel.updateFirstName(newName)
            },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = authViewModel.userLastName,
            onValueChange = { newLastName ->
                authViewModel.updateLastName(newLastName)
            },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = authViewModel.userEmail,
            onValueChange = { newEmail ->
                authViewModel.updateEmail(newEmail)
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = authViewModel.userPhoneNumber,
            onValueChange = { newPhone ->
                authViewModel.updatePhoneNumber(newPhone)
                Log.d("Number", newPhone)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = authViewModel.userAddress,
            onValueChange = { newAddress ->
                authViewModel.updateAddress(newAddress)
            },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(15.dp))
        
        Row(
            modifier = Modifier
                .height(70.dp)
                .height(100.dp)
                .background(Color(0x8DB3B0B0), shape = RoundedCornerShape(10.dp))
                .clip(RoundedCornerShape(10.dp))
                .align(alignment = Alignment.CenterHorizontally)
                .clickable {
                    authViewModel.signOut()
                }
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.log_out),
                contentDescription = null,
                modifier = Modifier.weight(0.05f), tint = MaterialTheme.colors.PrimaryColor
            )
            Text("Logout", modifier = Modifier.weight(0.2f))
            Icon(
                painter = painterResource(id = R.drawable.arrow_right),
                contentDescription = null,
                modifier = Modifier.weight(0.05f),
                tint = MaterialTheme.colors.TextColor
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        CustomDefaultBtn(btnText = "Update Details", shapeSize = 10f) {
            authViewModel.updateUserInDb()
        }

    }
}