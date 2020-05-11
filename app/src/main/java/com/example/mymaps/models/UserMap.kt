package com.example.mymaps.models

import android.icu.text.CaseMap
import java.io.Serializable

data class UserMap(val title: String, val places: List<Place>) : Serializable