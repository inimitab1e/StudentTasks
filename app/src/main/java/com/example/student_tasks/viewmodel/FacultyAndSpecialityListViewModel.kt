package com.example.student_tasks.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacultyAndSpecialityListViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val facAndSpecRepo: CountryStateRepository
    ) : ViewModel(),
    LifecycleObserver {
    private val LOG_TAG = "CountryStateViewModel"
    var loading: MutableLiveData<Boolean> = MutableLiveData()
    private val _obtainCountryStatesResponse= MutableLiveData<ResultOf<StateCapital>>()
    val  obtainCountryStatesResponse: LiveData<ResultOf<StateCapital>> = _obtainCountryStatesResponse

    fun obtainCountryStateCapitals(){
        loading.postValue(true)

        viewModelScope.launch(dispatcher){
            var  errorCode = -1
            try{
                var stateCapitalResponse =  countryStateRepository.fetchCountryStateCapitals()
                stateCapitalResponse?.let {
                    loading.postValue(false)
                    _obtainCountryStatesResponse.postValue(ResultOf.Success(it))
                }

            }catch (e : Exception){
                loading.postValue(false)
                e.printStackTrace()
                if(errorCode != -1){
                    _obtainCountryStatesResponse.postValue(ResultOf.Failure("Failed with Error Code ${errorCode} ", e))
                }else{
                    _obtainCountryStatesResponse.postValue(ResultOf.Failure("Failed with Exception ${e.message} ", e))
                }
            }
        }
    }

    fun prepareDataForExpandableAdapter(stateCapital: StateCapital) : MutableList<ExpandableFacAndSpecModel>{
        var expandableCountryModel = mutableListOf<ExpandableFacAndSpecModel>()
        for (states in stateCapital.countries) {
            expandableCountryModel.add(ExpandableFacAndSpecModel(ExpandableFacAndSpecModel.PARENT,states))
        }
        return expandableCountryModel
    }


    fun prepareDataForSectionedAdapter(stateCapital: StateCapital) : MutableList<ExpandableFacAndSpecModel>{
        var expandableCountryModel = mutableListOf<ExpandableFacAndSpecModel>()
        for (states in stateCapital.countries) {
            expandableCountryModel.add(ExpandableFacAndSpecModel(ExpandableFacAndSpecModel.PARENT,states))
            for(capitals in states.states){
                expandableCountryModel.add(ExpandableFacAndSpecModel(ExpandableFacAndSpecModel.CHILD,capitals))
            }
        }
        return expandableCountryModel
    }
}