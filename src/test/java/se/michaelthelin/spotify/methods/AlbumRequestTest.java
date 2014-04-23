package se.michaelthelin.spotify.methods;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import se.michaelthelin.spotify.Api;
import se.michaelthelin.spotify.JsonUtilTest;
import se.michaelthelin.spotify.SpotifyProtos.Album;

import static junit.framework.Assert.fail;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AlbumRequestTest {

  @Test
  public void shouldGetAlbumResult_async() throws Exception {
    final Api api = Api.DEFAULT_API;
    final AlbumRequest request = api.album().id("7e0ij2fpWaxOEHv5fUYZjd").build();

    // Mock response
    final String albumResponseFixture = JsonUtilTest.readTestData("album.json");
    AlbumRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    ListenableFuture<Album> albumFuture = spy.getAlbumAsync();
    Futures.addCallback(albumFuture, new FutureCallback<Album>() {
      public void onSuccess(Album album) {
        assertNotNull(album);
        assertEquals("0sNOF9WDwhWunNAHPD3Baj", album.getId());
      }
      public void onFailure(Throwable thrown) {
        fail("Failed to resolve future");
      }
    });
  }

  @Test
  public void shouldGetAlbumResult_sync() throws Exception {
    final Api api = Api.DEFAULT_API;
    final AlbumRequest request = api.album().id("7e0ij2fpWaxOEHv5fUYZjd").build();

    // Mock response
    final String albumResponseFixture = JsonUtilTest.readTestData("album.json");
    AlbumRequest spy = spy(request);
    when(spy.getJson()).thenReturn(albumResponseFixture);

    Album album = spy.getAlbum();
    assertNotNull(album);
    assertEquals("0sNOF9WDwhWunNAHPD3Baj", album.getId());
  }
}
