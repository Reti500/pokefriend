package com.example.remote

import com.example.remote.data.responses.PokemonResponse
import com.example.remote.data.responses.EntryResult
import com.example.remote.service.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class ApiServiceIntegrationTest {

    private val mockPokemon = PokemonResponse(
        count = 1,
        next = null,
        previous = null,
        results = listOf(
        EntryResult("1", "http://"),
        EntryResult("2", "http://")
        )
    )

    @Mock
    private lateinit var mockApiService: ApiService

    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
            .baseUrl(MockWebServer().url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mockApiService = retrofit.create(ApiService::class.java)
    }

    @Test
    fun `fetch list of pokemon`() = runTest{
        val mockWebServer = MockWebServer()
        mockWebServer.enqueue(mockPokemonOKResponse())
        mockWebServer.start()

        val baseUrl = mockWebServer.url("/").toString()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val resp = apiService.listOfPokemon(1, 1)

        assert(resp.isSuccessful)
        assert(resp.body()?.equals(mockPokemon) == true)
    }

    @Test
    fun `fail to fetch list of pokemon`() = runTest{
        val mockWebServer = MockWebServer()
        mockWebServer.enqueue(mockPokemonBadResponse())
        mockWebServer.start()

        val baseUrl = mockWebServer.url("/").toString()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val resp = apiService.listOfPokemon(1, 1)

        assert(!resp.isSuccessful)
    }

    private fun mockPokemonOKResponse() = MockResponse()
        .setResponseCode(200)
        .setBody(Gson().toJson(mockPokemon))

    private fun mockPokemonBadResponse() = MockResponse()
        .setResponseCode(422)
        .setBody(Gson().toJson(mockPokemon))
}