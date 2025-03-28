package swa.pin.livechatapp.Screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import swa.pin.livechatapp.R
import swa.pin.livechatapp.routes.DestinationRoutes
import swa.pin.livechatapp.ui.theme.app_color
import swa.pin.livechatapp.utils.CommonProgressBar
import swa.pin.livechatapp.utils.checkSignedIn
import swa.pin.livechatapp.utils.navigateTo
import swa.pin.livechatapp.viewmodel.LCViewmodel
@Composable
fun SignUpScreen(navController: NavHostController, vm: LCViewmodel) {
    checkSignedIn(vm,navController)

        val context= LocalContext.current

        var name by remember {
            mutableStateOf("")
        }
        var number by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        var passvisibility by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column (
            modifier = Modifier.fillMaxWidth().wrapContentHeight().verticalScroll(
                rememberScrollState()
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(360.dp)
            )
            Text(
                "Create Your Account",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = name,
                onValueChange = {name=it},
                label = { Text("Enter Your Name") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = number,
                onValueChange = {number=it},
                label = { Text("Enter Your Mobile Number") },
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                trailingIcon = {
                    if(number.isNotEmpty()){
                        if(number.length==10){
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.Green)
                        }else{
                            Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red)
                        }
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {email=it},
                label = { Text("Enter Your Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                trailingIcon = {
                    if(email.isNotEmpty()){
                        if(!email.endsWith("@gmail.com")){
                            Icon(Icons.Default.Warning, contentDescription = null, tint = Color.Red)
                        }else{
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color.Green)
                        }
                    }
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {password=it},
                label = { Text("Enter Your Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                trailingIcon = {
                    if(password.isNotEmpty()){
                        IconButton(onClick = {
                            passvisibility=!passvisibility
                        }) {
                            if(passvisibility){
                                Icon(painter = painterResource(R.drawable.visibillity_on) , contentDescription = null)
                            }else{
                                Icon(painter = painterResource(R.drawable.visibility_off), contentDescription = null)
                            }
                        }
                    }
                },
                visualTransformation = if(passvisibility){
                    VisualTransformation.None
                }else{
                    PasswordVisualTransformation()
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            )
            Spacer(modifier=Modifier.height(16.dp))
            Button(
                onClick = {
                         vm.SignUp(name,number,email,password)
                },
                shape = ButtonDefaults.elevatedShape,
                colors = ButtonDefaults.buttonColors(app_color),
                modifier=Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                Text("Create Account",
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    ))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row (modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center){

                TextButton(onClick = {
                    navigateTo(navController, DestinationRoutes.Login.route)
                }) {
                    Text(text = buildAnnotatedString {
                        append("Already have an account?")
                        withStyle(style = SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                        ){
                            append(" Login")
                        }
                    },
                        style = TextStyle(
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp,
                            color = Color.Black,

                            ))
                }
            }
        }
        if(vm.inProgress.value){
            CommonProgressBar()
        }
    }








}