package com.example.loadinggallery.datasource.repo

import android.app.Activity
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.loadinggallery.MainActivity
import com.example.loadinggallery.datasource.FakeLocalDataSource
import com.example.loadinggallery.datasource.LocalSource
import com.example.loadinggallery.model.Pojo
import com.example.loadinggallery.model.RepoInterface
import com.example.loadinggallery.model.Repository
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GalleryRepoTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repo : RepoInterface
    lateinit var localSource: LocalSource
    lateinit var activity: MainActivity

    @Before
    fun setUp(){
      val data = Pojo("")
        localSource = FakeLocalDataSource(listOf(data) as MutableList<Pojo>)
        repo = Repository.getInstance(localSource)
        activity = MainActivity()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun get_images_from_gallery_return_pojo() = runBlocking{

        var response = repo.getImagesFromGallery(activity)
        MatcherAssert.assertThat(response.toString(),Matchers.`is`(listOf(String)))
    }

}