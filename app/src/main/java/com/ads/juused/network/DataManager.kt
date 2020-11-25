package com.ads.juused.network

class DataManager() {

    private var apiManager:ApiManager? = null
    companion object{
        private var instance: DataManager? = null

        fun getInstance():DataManager {
            if(instance == null) {
                throw IllegalStateException("Call init() before getInstance()")
            }
            return instance as DataManager
        }

        fun initializeDataManager():DataManager{
            if (instance == null) {
                instance = DataManager()
            }
            return instance as DataManager
        }
    }

    fun initApiManager() {
        apiManager = ApiManager.getInstance()
    }

}