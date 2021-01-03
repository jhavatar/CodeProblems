package io.chthonic.codprob.data.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class BitmapRepoImplTest {

    @RelaxedMockK
    private lateinit var appContext: Context

    @RelaxedMockK
    private lateinit var cache: BitmapCache

    @RelaxedMockK
    private lateinit var bitmap: Bitmap


    lateinit var repo: BitmapRepoImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        repo = BitmapRepoImpl(appContext, cache)

        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0
    }

    @Test
    fun `getThumbnailBitmap() when cache hit`() {
        val key = "foo"
        every { cache.get(key) } returns bitmap

        assertEquals(repo.getThumbnailBitmap(key), bitmap)

        verify(atLeast = 1, atMost = 1) {
            cache.put(key, bitmap)
        }
    }

    @Test
    fun `getFullBitmap() when cache hit`() {
        every { cache.get(BitmapRepoImpl.FULL_BITMAP_CACHE_KEY) } returns bitmap

        assertEquals(repo.getFullBitmap("foo"), bitmap)

        verify(atLeast = 1, atMost = 1) {
            cache.put(BitmapRepoImpl.FULL_BITMAP_CACHE_KEY, bitmap)
        }

        // second key is different
        clearMocks(cache)
        every { cache.get(BitmapRepoImpl.FULL_BITMAP_CACHE_KEY) } returns bitmap

        assertEquals(repo.getFullBitmap("bar"), bitmap)

        verify(atLeast = 1, atMost = 1) {
            cache.remove(BitmapRepoImpl.FULL_BITMAP_CACHE_KEY)
            cache.put(BitmapRepoImpl.FULL_BITMAP_CACHE_KEY, bitmap)
        }
    }

}
