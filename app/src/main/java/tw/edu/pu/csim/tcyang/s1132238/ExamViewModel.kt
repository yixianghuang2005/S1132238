package tw.edu.pu.csim.tcyang.s1132238



import androidx.lifecycle.ViewModel

import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.getValue

import androidx.compose.runtime.setValue



class ExamViewModel : ViewModel() {



    var score by mutableStateOf(0)

}