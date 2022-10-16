package com.bqubique.quran_randomayah

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class NetworkConnectivityObserver(
    private val context: Context
) : ConnectivityObserver {

    private val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<ConnectivityObserver.Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    launch {
                        send(ConnectivityObserver.Status.Available)
                    }
                    super.onAvailable(network)
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    launch {
                        send(ConnectivityObserver.Status.Losing)
                    }
                    super.onLosing(network, maxMsToLive)
                }

                override fun onLost(network: Network) {
                    launch {
                        send(ConnectivityObserver.Status.Lost)
                    }
                    super.onLost(network)
                }

                override fun onUnavailable() {
                    launch {
                        send(ConnectivityObserver.Status.Unavailable)
                    }
                    super.onUnavailable()
                }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose{
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}