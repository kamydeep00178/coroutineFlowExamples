package com.usupgradeskills.coroutineflowexamples.repo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.IOException

class CFlowRepo {

    // Builder Example

    //1. flowOf()
    fun getFlowOf() = flowOf("Hello Flow Of")

    //2.  asFLow()
    fun getAsFollow() = (1..10).asFlow()

    //3. Flow{} with String
    suspend fun getFlowWithString() : Flow<String> {
        return flow {
                listOf("Hi","Hello..","All").forEach {
                emit(it)
            }
        }.flowOn(Dispatchers.Default)
    }

    //3. Flow{} with Int
    suspend fun getFLowWithNumber() : Flow<Int> {
        return flow {
            listOf(1,2,3,4,5,6).forEach {
                emit(it)
            }
        }.flowOn(Dispatchers.Default)
    }

    //4. Channel Flow
    suspend fun getChannelFlow() : Flow<Int>{
        return channelFlow {
            listOf(1,2,3,4,5).forEach {
                send(it)
            }
        }.flowOn(Dispatchers.Default)
    }


    suspend fun numberDataFromNetwork(): Flow<Int> {
        return flow {
            listOf(1,2,3).forEach {
                delay(500)
                emit(it)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun sampleDataFromNetwork(): Flow<String> {
        return flow {
            listOf("A","B","C").forEach {
                delay(1000)
                emit(it)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun exceptionData() : Flow<Int> {
        return flow {
                delay(1000)
            val randomNumber = (0..2).random()
            if (randomNumber == 0) {
                throw IOException()
            } else if (randomNumber == 1) {
                throw IndexOutOfBoundsException()
            }
            emit(2)
            }
    }


}