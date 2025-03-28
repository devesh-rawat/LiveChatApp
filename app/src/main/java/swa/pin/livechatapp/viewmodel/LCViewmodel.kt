package swa.pin.livechatapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import swa.pin.livechatapp.data.Event
import swa.pin.livechatapp.data.USER_NODE
import swa.pin.livechatapp.data.UserData
import javax.inject.Inject

@HiltViewModel
class LCViewmodel @Inject constructor(
    val auth: FirebaseAuth,
    val db: FirebaseFirestore
): ViewModel() {


    val userData=mutableStateOf<UserData?>(null)
    var inProgress= mutableStateOf(false)
    var eventMutableState=mutableStateOf<Event<String>?>(null)
    var signIn=mutableStateOf(false)
    init {
        val currentUser=auth.currentUser
        signIn.value=currentUser!=null
        currentUser?.uid?.let {
            getUserData(it)
        }


    }

    fun SignUp(name:String,number:String,email: String, password: String) {
        inProgress.value=true
        if(name.isEmpty() or number.isEmpty() or email.isEmpty() or password.isEmpty()){
            handleException(customMessage = "Please Fill All Credentials")
            return
        }
        inProgress.value=true
        db.collection(USER_NODE).whereEqualTo("number",number).get().addOnSuccessListener {
            if(it.isEmpty){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        signIn.value=true
                        createOrUpdateProfile(name,number)
                        Log.d("Login","User Logged In")
                    }else{
                        handleException(it.exception,"SignUp Failed")

                    }
                }
            }else{
                handleException(customMessage = "User Already Exists")
                inProgress.value=false
            }
        }

    }


    fun handleException(e: Exception?=null,customMessage:String=""){
        Log.e("LiveChatApp","Live chat Exception",e)
        e?.printStackTrace()
        val errorMsg=e?.localizedMessage?:""
        val message=if(customMessage.isNullOrEmpty()) errorMsg else customMessage
        eventMutableState.value= Event(message)
        inProgress.value=false
    }

    private fun getUserData(uid:String){
        inProgress.value=true
        db.collection(USER_NODE).document(uid).addSnapshotListener {
            value,error->
            if(error!=null){
                handleException(error,"Cannot Retrieve User")
            }
            if(value!=null){
                var user=value.toObject(UserData::class.java)
                userData.value=user
                inProgress.value=false
            }
        }
    }

    fun createOrUpdateProfile(name:String?=null,number: String?=null,imageurl: String?=null){
        var uid=auth.currentUser?.uid
        var userData= UserData(
            userId = uid,
            name=name?:userData.value?.name,
            number=number?:userData.value?.number,
            imageurl=imageurl?:userData.value?.imageurl
        )
        uid?.let {
            inProgress.value=true
            db.collection(USER_NODE).document(uid).get().addOnSuccessListener {
                if(it.exists()){
//                    update user data
                }else{
                    db.collection(USER_NODE).document(uid).set(userData)
                    inProgress.value=false
                    getUserData(uid)
                }
            }.addOnFailureListener {
                handleException(it,"Cannot Retrieve User")
            }

        }
    }



}



