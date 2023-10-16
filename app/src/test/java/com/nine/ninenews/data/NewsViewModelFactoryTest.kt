package com.nine.ninenews.data

import androidx.lifecycle.ViewModel
import com.nine.ninenews.data.repository.NewsRepository
import com.nine.ninenews.data.viewmodel.NewsViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import com.nine.ninenews.data.viewmodel.NewsViewModelFactory

class NewsViewModelFactoryTest {

    @Mock
    private lateinit var newsRepository: NewsRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test create NewsViewModel`() {
        // Arrange
        val factory = NewsViewModelFactory(newsRepository)
        val viewModel = factory.create(NewsViewModel::class.java)

        // Verify that the created ViewModel is of the expected type
        assertEquals(NewsViewModel::class.java, viewModel.javaClass)

        // You can also add more assertions here to test specific dependencies or behavior of the ViewModel.
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test create Unknown ViewModel`() {
        // Arrange
        class UnknownViewModel : ViewModel()
        val factory = NewsViewModelFactory(newsRepository)
        factory.create(UnknownViewModel::class.java)
    }
}