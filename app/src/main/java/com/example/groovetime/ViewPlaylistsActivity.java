package com.example.groovetime;

import androidx.appcompat.app.AppCompatActivity;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;


import android.os.Bundle;
import android.util.Log;


public class ViewPlaylistsActivity extends AppCompatActivity {
    private static final String CLIENT_ID = "20a9d447821d4818878fa3f3d8fcce6b";
    private static final String REDIRECT_URI = "http://localhost:8080";
    private SpotifyAppRemote mSpotifyAppRemote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_playlists);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ConnectionParams connectionParams =
                new ConnectionParams.Builder(CLIENT_ID)
                        .setRedirectUri(REDIRECT_URI)
                        .showAuthView(true)
                        .build();
        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {
            //Connects user to app remote
            @Override
            public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                mSpotifyAppRemote = spotifyAppRemote;
                Log.d("ViewPlaylistActivity", "You're connected!");
                connected();
            }
            //Handles connection errors
            @Override
            public void onFailure(Throwable throwable) {
                Log.e("ViewPlaylistActivty", throwable.getMessage(), throwable);
            }
        });
    }

    private void connected() {
        mSpotifyAppRemote.getPlayerApi().play("spotify:playlist:37i9dQZF1DX2sUQwD7tbmL");

        // Then we will write some more code here.
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Aaand we will finish off here.
    }
}