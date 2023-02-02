package com.example.firstexampleapp.ui.viewModel.userViewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstexampleapp.model.response.Response
import com.example.firstexampleapp.model.user.Trimester
import com.example.firstexampleapp.model.user.UserState
import com.example.firstexampleapp.model.user.UserVar
import com.example.firstexampleapp.model.user.repository.UserRepo
import com.example.firstexampleapp.model.weight.WeightState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

class UserViewModel() : ViewModel() {
    private val userRepo = UserRepo()
    val isUserAuth = mutableStateOf(userRepo.isUserAuthenticated())
    private val _user = MutableStateFlow(UserState())
    val user: StateFlow<UserState> = _user.asStateFlow()
    var isFirstChild = mutableStateOf("")
        private set
    var _credential = mutableStateMapOf<String, String>()
        private set
    var _imc = mutableStateMapOf<String, String>()
        private set
    var isDark = mutableStateOf(value = true)//was false
        private set
    var msgError = mutableStateOf("")
        private set
//    private val _userList = MutableStateFlow(getUsers())
//    val userList: StateFlow<List<UserState>> = _userList.asStateFlow()

    fun onThemeChange(n: Boolean = false) { isDark.value = n }

    fun onValueChangeName(text: String) = _user.update { it.copy(name = text) }

    fun onValueChangeAge(age: String) = _user.update { it.copy(age = age.filterNot { it.toString() == "." }) }

    fun onValueChangeBabySex(babySex: String) = _user.update { it.copy(babySex = babySex) }

    fun onValueChangeFirstChild(firstChild: String) {
        isFirstChild.value = firstChild
        _user.update { it.copy(isFirstChild = isFirstChild.value == "Sí") }
    }

    //this function must be evaluate the credential with data base on login screen
    fun onValueChangeCredential(credential: Map<String, String>) {
        when (credential.keys.first()) {
            UserVar.Email.type -> {
                _credential[UserVar.Email.type] = credential[UserVar.Email.type] ?: ""
                _user.value.credentials[UserVar.Email.type] = _credential[UserVar.Email.type] ?: ""
            }
            UserVar.Password.type -> {
                _credential[UserVar.Password.type] = credential[UserVar.Password.type] ?: ""
                _user.value.credentials[UserVar.Password.type] =
                    _credential[UserVar.Password.type] ?: ""
            }
            UserVar.Password2.type -> {
                _credential[UserVar.Password2.type] = credential[UserVar.Password2.type] ?: ""
                _user.value.credentials[UserVar.Password2.type] =
                    _credential[UserVar.Password2.type] ?: ""
            }
            else -> return
        }
//        Log.d("credential", _user.value.credentials.toString())//works fine
    }

    fun onValueChangeImc(imc: Map<String, String>) {
        when (imc.keys.first()) {
            UserVar.Height.type -> {
                _imc[UserVar.Height.type] = imc[UserVar.Height.type].toString()//look closer
                _user.value.imc[UserVar.Height.type] =
                    _imc[UserVar.Height.type].toString()
            }
            UserVar.Weight.type -> {
                _imc[UserVar.Weight.type] = imc[UserVar.Weight.type].toString()//look closer
                _user.value.imc[UserVar.Weight.type] =
                    _imc[UserVar.Weight.type].toString()
                _user.value.weightRecord[0] = _user.value.weightRecord[0].copy(weight = imc[UserVar.Weight.type].toString())//update weightRecord
            }
            else -> return
        }
//        Log.d("imc", _user.value.imc.toString())//works fine
    }

    fun onValueChangeLastPeriod(lastPeriod: String) {
        _user.update { it.copy(lastPeriod = lastPeriod) }
        getCurrentPregnancyDay()
        getTrimester()
        getPregnancyProgress()
        _user.value.weightRecord[0] = _user.value.weightRecord[0].copy(week = _user.value.pregnancyWeek)//update weightRecord
//        addWeightRecord()
    }

    fun onValueChangeFirstPregnancy(firstPregnancy: String) =
        _user.update { it.copy(firstPregnancy = firstPregnancy) }

    fun onClearText(attr: String) {
        when (attr) {
            UserVar.Name.type -> _user.update { it.copy(name = "") }
            UserVar.Age.type -> _user.update { it.copy(age = "") }
            UserVar.Email.type -> _credential[UserVar.Email.type] = ""
            UserVar.Password.type -> _credential[UserVar.Password.type] = ""
            UserVar.Password2.type -> _credential[UserVar.Password2.type] = ""
            UserVar.Height.type -> _imc[UserVar.Height.type] = ""
            UserVar.Weight.type -> _imc[UserVar.Weight.type] = ""
            "all" -> _credential.clear()
        }
    }

    fun isPassWordValid(): Boolean {
        return _user.value.credentials[UserVar.Password2.type] == _user.value.credentials[UserVar.Password.type]
    }

    fun logoutUser() {
        _user.value = UserState()
        _credential = mutableStateMapOf()
        userOut()
    }

    private fun getCurrentPregnancyDay() {
        val userPregnancy = _user.value.firstPregnancy ?: _user.value.lastPeriod ?: "11/13/1995"
        val weekInInYear = 52//const for week of a year
        val format = SimpleDateFormat("d/M/yyyy", Locale.getDefault())
        val userPregnancyDate = Calendar.getInstance()
        userPregnancyDate.time =
            format.parse(userPregnancy) ?: Date()//convert user pregnancy into date object
        val currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) //retain current week

        if (userPregnancyDate[Calendar.YEAR] != Calendar.getInstance().get(Calendar.YEAR)) {
            _user.update { it.copy(pregnancyWeek = (weekInInYear - userPregnancyDate.get(Calendar.WEEK_OF_YEAR)) + currentWeek) }
        } else {
            _user.update { it.copy(pregnancyWeek = currentWeek - userPregnancyDate.get(Calendar.WEEK_OF_YEAR)) }//retain current pregnancy week
        }
    }//do begin later in home screen not login screen

    private fun getTrimester() {
        if (_user.value.pregnancyWeek <= 13) {
            _user.update { it.copy(trimester = Trimester.First.type) }//was without type
        } else if (_user.value.pregnancyWeek <= 27) {
            _user.update { it.copy(trimester = Trimester.Second.type) }
        } else {
            _user.update { it.copy(trimester = Trimester.Third.type) }
        }
    }//do begin later in home screen not login screen

    private fun getPregnancyProgress() {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        _user.update {
            it.copy(pregnancyProgress = df.format(_user.value.pregnancyWeek / 40.0).toFloat())
        }//40.0 is equal to 10 months or 40 weeks
    }//do begin later in home screen not login screen

    fun getFirstNameLetter() = if (_user.value.name.isNotEmpty()) _user.value.name.substring(0, 1).uppercase() else "C"

    //do begin later in home screen not login screen

//    private fun getUsers() = users

    fun addWeightRecord(weight: String = "0.0"){
        val today = Calendar.getInstance()
        val new = WeightState(
            currentDate = "${today.get(Calendar.DAY_OF_MONTH)}/${today.get(Calendar.MONTH) + 1}/${today.get(Calendar.YEAR)}",
            week = _user.value.pregnancyWeek,
            weight = weight,
            changeWeight = String.format("%.1f", weight.toDouble() - _user.value.weightRecord.first().weight.toDouble())
                .toDouble()//round double to one decimal
        )
        _user.update { it.copy(weightRecord = mutableListOf(new).also { newList -> _user.value.weightRecord.forEach { newList.add(it) }}) }
        viewModelScope.launch { userRepo.addWeightRecord(newValue = new, userId = _user.value.idUser) }
    }

    fun deleteWeightRecord(position: Int){
        if (position == _user.value.weightRecord.lastIndex) return
        val deletedValue = _user.value.weightRecord[position]
        _user.update { it.copy(weightRecord = (_user.value.weightRecord - deletedValue).toMutableList()) }
        viewModelScope.launch { userRepo.deleteWeightRecord(deletedValue = deletedValue, userId = _user .value.idUser) }
    }

    //this function add a new user to firestore
    fun addUser(
        callback: (String) -> Unit,
        authId: String
    ) = viewModelScope.launch {
        when (val res = userRepo.addUser(userState = _user.value, authId = authId)) {
            is Response.Error -> Log.d("addUser", res.error)
            Response.Loading -> TODO()
            is Response.Success -> Log.d("addUser", res.data).also { callback(authId) }
        }
    }

    //this function retrieve a user object from Firestore by it id
    fun getUserByCredentials(
        callback: (String) -> Unit
    ) = viewModelScope.launch {
        msgError.value = ""
        when (val res = userRepo.verifyUserCredentials(
            email = _user.value.credentials[UserVar.Email.type] ?: "",
            password = _user.value.credentials[UserVar.Password.type] ?: "")
        ) {
            is Response.Error -> msgError.value = res.error
            Response.Loading -> TODO()
            is Response.Success -> Log.d("getUser", res.data.idUser)
                .also { _user.update { res.data.copy(weightRecord = res.data.weightRecord.asReversed()) } ; callback(_user.value.idUser) }
        }
    }

    fun getUserByUid(
        authId: String,
        callback: (authId: String) -> Unit,
    ) = viewModelScope.launch {
        when (val res = userRepo.getUserByUid(authId)) {
            is Response.Error -> {}
            Response.Loading -> TODO()
            is Response.Success -> Log.d("getUser", res.data.idUser)
                .also { _user.update { res.data.copy(weightRecord = res.data.weightRecord.asReversed()) }; callback(authId) }
        }
    }

    fun isFieldFilled(attr: List<String>) = !attr.contains("")

    //auth functions
    fun signIn(
        callback: (String) -> Unit
    ) = viewModelScope.launch {
        msgError.value = ""
        when (val res = userRepo.signIn(
            email = _user.value.credentials[UserVar.Email.type] ?: "",
            password = _user.value.credentials[UserVar.Password.type] ?: "")){
            is Response.Error -> msgError.value = "Correo o contraseña inválidos"//msgError.value = res.error
            Response.Loading -> {}
            is Response.Success -> Log.d("signUser", "user with uid.. : ${res.data}")
                .also { getUserByUid(callback = callback, authId = res.data) }
        }
    }

    fun createUser(
        callback: (String) -> Unit
    ) = viewModelScope.launch {
        when (val res = userRepo.createUser(
            email = _user.value.credentials[UserVar.Email.type] ?: "",
            password = _user.value.credentials[UserVar.Password.type] ?: "")){
            is Response.Error -> Log.w("addUser", "Error.. ${res.error}" )
            Response.Loading -> Log.d("addUser", "loading...")
            is Response.Success -> Log.d("addUser", "user added with uid.. : ${res.data}")
                .also{ addUser(callback = callback, authId = res.data) }
        }
    }

    private fun changeAuthState () = viewModelScope.launch {
        userRepo.getAuthState().collect {
            isUserAuth.value = it
        }
    }

    fun getCurrentUser() = userRepo.getCurrentUser()

    fun userOut() = userRepo.userOut()

}
