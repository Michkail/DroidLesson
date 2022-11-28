package com.tdsoft.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ToDoItem(val id:Int, var toTitle:String, var toDesc:String) : Parcelable