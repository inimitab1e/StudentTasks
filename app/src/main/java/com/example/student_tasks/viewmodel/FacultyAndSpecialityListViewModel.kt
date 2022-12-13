package com.example.student_tasks.viewmodel

import android.content.Context
import androidx.lifecycle.*
import com.example.student_tasks.data.model.ExpandableFacAndSpecModel
import com.example.student_tasks.data.model.FacultyAndSpecialityModel
import com.example.student_tasks.interfaces.content.FacultyAndSpecialityListInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacultyAndSpecialityListViewModel @Inject constructor(
    private val facAndSpecRepo: FacultyAndSpecialityListInterface
) : ViewModel() {
    private val _facAndSpecResponse= MutableLiveData<FacultyAndSpecialityModel>()
    val  facAndSpecResponse: LiveData<FacultyAndSpecialityModel> = _facAndSpecResponse

    fun obtainFacAndSpec(context: Context) {
        viewModelScope.launch() {
            var stateCapitalResponse = facAndSpecRepo.fetchFacAndSpec(context)
            _facAndSpecResponse.postValue(stateCapitalResponse)
        }
    }

    fun prepareDataForExpandableAdapter(facAndSpec: FacultyAndSpecialityModel): MutableList<ExpandableFacAndSpecModel> {
        var expandableFacultyModel = mutableListOf<ExpandableFacAndSpecModel>()
        for (faculty in facAndSpec.facultyList) {
            expandableFacultyModel.add(
                ExpandableFacAndSpecModel(
                    ExpandableFacAndSpecModel.PARENT,
                    faculty
                )
            )
        }
        return expandableFacultyModel
    }


//    fun prepareDataForSectionedAdapter(facAndSpec: FacultyAndSpecialityModel): MutableList<ExpandableFacAndSpecModel> {
//        var expandableFacultyModel = mutableListOf<ExpandableFacAndSpecModel>()
//        for (faculty in facAndSpec.facultyList) {
//            expandableFacultyModel.add(
//                ExpandableFacAndSpecModel(
//                    ExpandableFacAndSpecModel.PARENT,
//                    faculty
//                )
//            )
//            for (specialities in faculty.specialityList) {
//                expandableFacultyModel.add(
//                    ExpandableFacAndSpecModel(
//                        ExpandableFacAndSpecModel.CHILD,
//                        specialities
//                    )
//                )
//            }
//        }
//        return expandableFacultyModel
//    }
}