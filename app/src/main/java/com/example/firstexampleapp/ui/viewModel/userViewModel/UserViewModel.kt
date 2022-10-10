package com.example.firstexampleapp.ui.viewModel.userViewModel

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.firstexampleapp.model.user.Trimester
import com.example.firstexampleapp.model.user.UserState
import com.example.firstexampleapp.model.user.UserVar
import com.example.firstexampleapp.model.user.repository.users
import kotlinx.coroutines.flow.*
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class UserViewModel(

) : ViewModel() {
    private val _user = MutableStateFlow(UserState())
    val user: StateFlow<UserState> = _user.asStateFlow()
    private val _userList = MutableStateFlow(getUsers())
    val userList: StateFlow<List<UserState>> = _userList.asStateFlow()
    var isFirstChild = mutableStateOf("")
        private set
    var _credential = mutableStateMapOf<String, String>()
        private set
    var _imc = mutableStateMapOf<String, String>()
        private set

    fun onValueChangeName(text: String) = _user.update { it.copy(name = text) }

    fun onValueChangeAge(age: String) = _user.update { it.copy(age = age) }

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
                _imc[UserVar.Height.type] = imc[UserVar.Height.type].toString()
                _user.value.imc[UserVar.Height.type] =
                    _imc[UserVar.Height.type].toString().toDouble()
            }
            UserVar.Weight.type -> {
                _imc[UserVar.Weight.type] = imc[UserVar.Weight.type].toString()
                _user.value.imc[UserVar.Weight.type] =
                    _imc[UserVar.Weight.type].toString().toDouble()
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
        }
    }

    fun isPassWordValid(): Boolean {
        return _user.value.credentials[UserVar.Password2.type] == _user.value.credentials[UserVar.Password.type]
    }

    //this function should be lightly different. Look up later
    fun isValidCredential(): Boolean {
        val initUser = userList.value.filter { items ->
            items.credentials[UserVar.Email.type] == _user.value.credentials[UserVar.Email.type] &&
//                    items.credentials.containsValue(value = _user.value.credentials[UserVar.Password.type])
                    items.credentials[UserVar.Password.type] == _user.value.credentials[UserVar.Password.type]
        }
        return if (initUser.isNotEmpty()) {
            _user.value = initUser.first()
            getCurrentPregnancyDay()
            getTrimester()
            getPregnancyProgress()
            true
        }else{
            false
        }
//        return !_user.value.credentials[UserVar.Email.type].isNullOrEmpty() && !_user.value.credentials[UserVar.Password.type].isNullOrEmpty()
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
        if (_user.value.pregnancyWeek <= 12) {
            _user.update { it.copy(trimester = Trimester.First) }
        } else if (_user.value.pregnancyWeek <= 24) {
            _user.update { it.copy(trimester = Trimester.Second) }
        } else {
            _user.update { it.copy(trimester = Trimester.Third) }
        }
    }//do begin later in home screen not login screen

    private fun getPregnancyProgress() {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        _user.update {
            it.copy(pregnancyProgress = df.format(_user.value.pregnancyWeek / 36.0).toFloat())
        }//36.0 is equal to 9 months or 32 weeks
    }//do begin later in home screen not login screen

    fun getFirstNameLetter() = _user.value.name.substring(0, 1).uppercase()
    //do begin later in home screen not login screen

    private fun getUsers() = users

}
//
//fun main() {
//    val format = SimpleDateFormat("d/M/yyyy", Locale.getDefault())
//    val totalPregnancy = 36 //const for total pregnancy
//    val weekInInYear = 52//const for week of a year
//    val todayWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) //retain current year
//    val userPregnancy = Calendar.getInstance()
//    userPregnancy.time =
//        format.parse("1/10/2022") ?: Date()//convert user pregnancy into date object
//    val userYear = userPregnancy.get(Calendar.YEAR)//retain year of user pregnancy
//    val userPregnancyWeek = userPregnancy.get(Calendar.WEEK_OF_YEAR)//retain week of user pregnancy
//    var pregnancyWeek = 13//for test purposes
//    val finalWeek = userPregnancyWeek + totalPregnancy//plus userWeek with totalPregnancy
//    var comingWeek = (pregnancyWeek - userPregnancyWeek)//subtract todayWeek with userWeek
//    val remainderWeek = finalWeek - pregnancyWeek//subtract finalWee to todayWeek
//    var trimester = ""
//    if (pregnancyWeek < 20) {
//        val restartWeek = (weekInInYear - userPregnancyWeek)//retain week after completes one year
//        comingWeek = restartWeek + pregnancyWeek
//        println(restartWeek)
//    }
//
//    trimester = if (comingWeek <= 12) {
//        "1 trimester"
//    } else if (comingWeek <= 24) {
//        "2 trimester"
//    } else {
//        "3 trimester"
//    }
//
//    println("week from user: $userPregnancyWeek")
//    println("week for current: $pregnancyWeek")
//    println("total week for pregnancy: $finalWeek")
//    println("falta: $remainderWeek semanas")
//    println("you are on : $comingWeek week")
//    println(trimester)
//
//}
