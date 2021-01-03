package io.chthonic.codprob.business.image

import android.util.Log
import io.chthonic.codprob.data.bitmap.BitmapRepo
import io.chthonic.codprob.data.file.FilesRepo
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class ImageInteractorImplTest {

    @RelaxedMockK
    private lateinit var filesRepo: FilesRepo

    @RelaxedMockK
    private lateinit var bitmapRepo: BitmapRepo

    lateinit var interactor: ImageInteractorImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        interactor = ImageInteractorImpl(filesRepo, bitmapRepo)

        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0
    }

    @Test
    fun `testImagePaths() when no images`() {
        every { filesRepo.getImageFilePaths() } returns emptyList()

        assertEquals(interactor.getImagePaths(), emptyList<String>())
    }

    @Test
    fun `testImagePaths() peforms sort and precache`() {
        val filePathsSorted = mutableListOf<String>()
        for (i in 0..100) {
            filePathsSorted.add(i.toString().padStart(3, '0'))
        }
        val filePathsUnsorted = filePathsSorted.reversed()

        every { filesRepo.getImageFilePaths() } returns filePathsUnsorted

        assertEquals(interactor.getImagePaths(), filePathsSorted)

        verify(atLeast = 1, atMost = 1) {
            bitmapRepo.cacheBitmaps(filePathsSorted.subList(0, 24))
        }
    }

    @Test
    fun `generateImagePath() when success`() {
        every { filesRepo.generateImageFilePath() } returns "foo"

        assertEquals(interactor.generateImagePath(), "foo")
    }

    @Test
    fun `generateImagePath() when exception`() {
        every { filesRepo.generateImageFilePath() } throws RuntimeException("bar")

        assertNull(interactor.generateImagePath())
    }

    @Test
    fun clearAllImages() {
        interactor.clearAllImages()

        verify(atLeast = 1, atMost = 1) {
            bitmapRepo.clear()
            filesRepo.clearAllFiles()
        }
    }

}
