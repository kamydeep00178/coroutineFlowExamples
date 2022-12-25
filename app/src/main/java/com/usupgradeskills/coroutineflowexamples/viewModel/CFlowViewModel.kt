package com.usupgradeskills.coroutineflowexamples.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.usupgradeskills.coroutineflowexamples.repo.CFlowRepo
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException


class CFlowViewModel constructor(val repo: CFlowRepo) : ViewModel() {

    private val _firstData = MutableStateFlow("")
    val firstData = _firstData.asStateFlow()

    private val _secondData = MutableStateFlow<List<Int>>(emptyList())
    val secondData = _secondData.asStateFlow()

    private val _thirdData = MutableStateFlow<List<String>>(emptyList())
    val thirdData = _thirdData.asStateFlow()

    private val _fourthData = MutableStateFlow<List<Int>>(emptyList())
    val fourthData = _fourthData.asStateFlow()

    private val _filterData = MutableStateFlow<List<Int>>(emptyList())
    val filterData = _filterData.asStateFlow()

    private val _mapData = MutableStateFlow<List<Int>>(emptyList())
    val mapData = _mapData.asStateFlow()

    private val _takeOperator = MutableStateFlow<List<Int>>(emptyList())
    val takeOperator = _takeOperator.asStateFlow()

    private val _takeWhileOperator = MutableStateFlow<List<Int>>(emptyList())
    val takeWhileOperator = _takeWhileOperator.asStateFlow()

    private val _dropOperator = MutableStateFlow<List<Int>>(emptyList())
    val dropOperator = _dropOperator.asStateFlow()

    private val _dropWhileOperator = MutableStateFlow<List<Int>>(emptyList())
    val dropWhileOperator = _dropWhileOperator.asStateFlow()

    private val _combineOperator = MutableStateFlow<List<String>>(emptyList())
    val combineOperator = _combineOperator.asStateFlow()

    private val _zipOperator = MutableStateFlow<List<String>>(emptyList())
    val zipOperator = _zipOperator.asStateFlow()

    private val _flattenMergeOperator = MutableStateFlow<List<String>>(emptyList())
    val flattenMergeOperator = _flattenMergeOperator.asStateFlow()

    private val _flatMapConnectOperator = MutableStateFlow<List<String>>(emptyList())
    val flatMapConnectOperator = _flatMapConnectOperator.asStateFlow()

    private val _flatMapMergeOperator = MutableStateFlow<List<String>>(emptyList())
    val flatMapMergeOperator = _flatMapMergeOperator.asStateFlow()

    private val _flatMapLatest = MutableStateFlow<List<String>>(emptyList())
    val flatMapLatest = _flatMapLatest.asStateFlow()

    private val _bufferData = MutableStateFlow<List<String>>(emptyList())
    val bufferData = _bufferData.asStateFlow()

    private val _conflateData = MutableStateFlow<List<String>>(emptyList())
    val conflateData = _conflateData.asStateFlow()

    private val _collectLatestData = MutableStateFlow<List<String>>(emptyList())
    val collectLatestData = _collectLatestData.asStateFlow()

    fun getFlowOfData(){
        viewModelScope.launch {
            repo.getFlowOf().collect{
                _firstData.emit(it)
            }
        }
    }

    fun getAsFlowData(){
        viewModelScope.launch {
            val list = ArrayList<Int>()
            repo.getAsFollow().collect{
                list.add(it)
                _secondData.emit(list)
            }
        }
    }

    fun getFlowWithString(){
        viewModelScope.launch {
            val list = ArrayList<String>()
            repo.getFlowWithString().collect{
                list.add(it)
                _thirdData.emit(list)
            }
        }
    }

    fun getChannelFlow(){
        viewModelScope.launch {
            val list = ArrayList<Int>()
            repo.getChannelFlow().collect{
                list.add(it)
                _fourthData.emit(list)
            }
        }
    }

    fun getFilterOperator(){
        viewModelScope.launch {
            val list = ArrayList<Int>()
            repo.getFLowWithNumber().filter { num -> num < 3 }.collect{
                list.add(it)
                _filterData.emit(list)
            }
        }
    }

    fun getMapOperator(){
        viewModelScope.launch {
            val list = ArrayList<Int>()
            repo.getFLowWithNumber().map { it * it }.collect{
                list.add(it)
            }
            _mapData.emit(list)
        }
    }

    fun getTakeOperator(){
        viewModelScope.launch {
            val list = ArrayList<Int>()
            repo.getFLowWithNumber().take(2).collect{
                list.add(it)
            }
            _takeOperator.emit(list)
        }
    }

    fun getTakeWhileOperator(){
        viewModelScope.launch {
            val list = ArrayList<Int>()
            repo.getFLowWithNumber().takeWhile { it % 2 == 1 }.collect{
                list.add(it)
            }
            _takeWhileOperator.emit(list)
        }
    }

    fun getDropOperator(){
        viewModelScope.launch {
            val list = ArrayList<Int>()
            repo.getFLowWithNumber().drop(3).collect{
                list.add(it)
            }
            _dropOperator.emit(list)
        }
    }

    fun getDropWhileOperator(){
        viewModelScope.launch {
            val list = ArrayList<Int>()
            repo.getFLowWithNumber().dropWhile { it % 2 == 1 }.collect{
                list.add(it)
            }
            _dropWhileOperator.emit(list)
        }
    }

    fun getCombineOperator(){
        viewModelScope.launch {
            val list = ArrayList<String>()
            repo.numberDataFromNetwork().combine(repo.sampleDataFromNetwork()){ num , let ->
                "$num$let"
            }.collect{
                list.add(it)
            }
            _combineOperator.emit(list)
        }
    }

    fun getZipOperator(){
        viewModelScope.launch {
            val list = ArrayList<String>()
            repo.numberDataFromNetwork().zip(repo.sampleDataFromNetwork()){ num , let ->
                "$num$let"
            }.collect{
                list.add(it)
            }
            _zipOperator.emit(list)
        }
    }

    fun getFlattenMergeOperator() {
        viewModelScope.launch {
            val list = ArrayList<String>()
            flowOf(repo.numberDataFromNetwork(), repo.sampleDataFromNetwork()).flattenMerge()
                .collect {
                    list.add(it.toString())
                }
            _flattenMergeOperator.emit(list)
        }
    }

    @FlowPreview
    fun getFlatMapConnectOperator() {
        viewModelScope.launch {
            val list = ArrayList<String>()
            repo.getFlowWithString().flatMapConcat { str ->
                repo.numberDataFromNetwork().map {
                    "$it -> $str"
                }
            }.collect{
                list.add(it)
            }
            _flatMapConnectOperator.emit(list)
        }
    }

    @FlowPreview
    fun getFlatMapMergeOperator() {
        viewModelScope.launch {
            val list = ArrayList<String>()
            repo.getFlowWithString().flatMapMerge { str ->
                repo.numberDataFromNetwork().map {
                    "$it -> $str"
                }
            }.collect{
                list.add(it)
            }
            _flatMapMergeOperator.emit(list)
        }
    }

    @FlowPreview
    fun getFlatMapLatestOperator() {
        viewModelScope.launch {
            val list = ArrayList<String>()
            repo.getFlowWithString().flatMapLatest { str ->
                repo.numberDataFromNetwork().map {
                    "$it -> $str"
                }
            }.collect{
                list.add(it)
            }
            _flatMapLatest.emit(list)
        }
    }

    fun getRetryOperator(){
        viewModelScope.launch {
            repo.exceptionData().retry(retries = 3){ throwData ->
                throwData is IOException
            }.catch {
                Log.e("TAG", "exception: $this" )
            }.collect{
                Log.e("TAG", "getRetryOperator: $it")
            }
        }
    }

    fun getBuffer(){
        viewModelScope.launch {
            val list = ArrayList<String>()
            repo.sampleDataFromNetwork().buffer().collect{
                delay(3000)
                list.add(it)
            }
            _bufferData.emit(list)
        }
    }

    fun getConflate(){
        viewModelScope.launch {
            val list = ArrayList<String>()
            repo.sampleDataFromNetwork().conflate().collect{
                delay(3000)
                list.add(it)
            }
            _conflateData.emit(list)
        }
    }

    fun getConflateLatest(){
        viewModelScope.launch {
            val list = ArrayList<String>()
            repo.sampleDataFromNetwork().collectLatest{
                delay(3000)
                list.add(it)
            }
            _collectLatestData.emit(list)
        }
    }



}