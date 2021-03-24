package com.akih.submissionbfaa1.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Github(
    var username : String? = null,
    var name : String? = null,
    var avatar : Int? = 0,
    var company : String? = null,
    var location : String? = null,
    var repository : Int? = 0,
    var follower : Int? = 0,
    var following : Int? = 0
) :Parcelable