package com.nine.ninenews.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.nine.ninenews.data.repository.NewsRepository
import com.nine.ninenews.data.model.*
import com.nine.ninenews.data.viewmodel.NewsViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response


@ExperimentalCoroutinesApi
class NewsViewModelTest {

    // Use this rule to make LiveData work with JUnit
    @get:Rule
    val rule = InstantTaskExecutorRule()

    // Use a TestCoroutineDispatcher for running coroutines in tests
    private val testDispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var newsRepository : NewsRepository

    private val newsObserver = mock<Observer<List<Asset>>>()
    private val isLoadingObserver = mock<Observer<Boolean>>()
    private val errorObserver = mock<Observer<String>>()

    // The ViewModel to be tested
    private lateinit var viewModel: NewsViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Set the TestCoroutineDispatcher as the main dispatcher

        MockitoAnnotations.openMocks(this)

        // Initialize the ViewModel with the mocked repository
        viewModel = NewsViewModel(newsRepository)

        // Observe LiveData in the ViewModel
        viewModel.newsArticles.observeForever(newsObserver)
        viewModel.isLoading.observeForever(isLoadingObserver)
        viewModel.error.observeForever(errorObserver)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain() // Reset the main dispatcher
    }

    @Test
    fun `test fetchNewsArticles success`() = testDispatcher.runBlockingTest {
        // Arrange
        val dummyAssets = listOf(
            Asset(
                false,
                "Article",
                "https://www.afr.com/chanticleer/chaos-in-congress-a-drubbing-why-fear-rules-markets-20231004-p5e9nb",
                listOf(Author("", "james", listOf(), listOf(), "Columnist")),
                "<p>You can’t say the Republicans",
                "james",
                listOf(),
                listOf(),
                "Chaos in Congress",
                123,
                "Chaos",
                12,
                "None",
                false,
                "",
                0,
                listOf(),
                listOf(),
                listOf(),
                "op",
                listOf(),
                false,
                "A Fresh",
                "",
                Overrides("", ""),
                "you cant say ",
                1696379886000
            )
        )

        val newsArticle = NewsArticle(
            "",
            "",
            listOf(),
            listOf(),
            "",
            12,
            12,
            12,
            listOf(),
            listOf(),
            false,
            dummyAssets,
            1696441251
        )

        val successResponse = Response.success(newsArticle)
        whenever(newsRepository.getNewsArticles()).thenReturn(successResponse)

        // Act
        viewModel.fetchNewsArticles()


        // Assert
        // Verify that loading state was updated
        verify(isLoadingObserver).onChanged(true)

        // Capture the value passed to newsObserver
        val captor = argumentCaptor<List<Asset>>()
        captor.run {
            // Verify that news articles were updated with the expected value
            verify(newsObserver, atLeastOnce()).onChanged(capture())
            // Assert the captured value against your expected value
            val capturedValue = firstValue // Assuming you captured the first value
            assertEquals(dummyAssets, capturedValue)
        }

        // Verify that loading state was updated after fetching
        verify(isLoadingObserver).onChanged(false)

        // Verify that error state was not updated
        verifyZeroInteractions(errorObserver)
    }

    @Test
    fun `test fetchNewsArticles failure`() = testDispatcher.runBlockingTest {
        // Arrange

        val errorMessage = "Failed to fetch news articles"
        val errorResponseBody = ResponseBody.create(MediaType.parse("application/json"), errorMessage)

        val failureResponse = Response.error<NewsArticle>(400, errorResponseBody)
        whenever(newsRepository.getNewsArticles()).thenReturn(failureResponse)

        // Act
        viewModel.fetchNewsArticles()

        // Assert
        // Verify that loading state was updated
        verify(isLoadingObserver).onChanged(true)

        // Verify that error state was updated
        val captor = ArgumentCaptor.forClass(String::class.java)
        captor.run {
            verify(errorObserver).onChanged(capture())

            val capturedValue = firstValue
            assertEquals(errorMessage, capturedValue)
        }

        // Verify that loading state was updated after fetching
        verify(isLoadingObserver).onChanged(false)

        // Verify that news articles were not updated
        verifyZeroInteractions(newsObserver)
    }
}