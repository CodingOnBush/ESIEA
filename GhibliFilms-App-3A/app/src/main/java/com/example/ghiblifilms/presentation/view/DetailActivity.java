package com.example.ghiblifilms.presentation.view;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.ghiblifilms.Config;
import com.example.ghiblifilms.R;
import com.example.ghiblifilms.presentation.model.Film;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import android.content.Intent;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.r0adkll.slidr.Slidr;

import java.lang.reflect.Type;
import java.util.HashMap;

public class DetailActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private HashMap<String, String> trailers;
    private String currentFilmTitle;
    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    private MyPlayerStateChangeListener playerStateChangeListener;
    private MyPlaybackEventListener playbackEventListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.detail_activity);

        Slidr.attach(this);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_API_KEY, this);

        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();

        trailers = new HashMap<String, String>();
        loadFilmTrailer();

        Film currentFilm = getCurrentFilmFromCache();
        currentFilmTitle = currentFilm
                .getTitle()
                .toLowerCase()
                .replace(" ", "_")
                .replace("'", "_");

        String drawablePath = "@drawable/" + currentFilmTitle;
        int imageResource = getResources().getIdentifier(drawablePath, null, getPackageName());

        TextView detailFilmTitle = (TextView) findViewById(R.id.detail_filmTitle);
        ImageView littlePoster = (ImageView) findViewById(R.id.littleFilmPoster);
        TextView producer = (TextView) findViewById(R.id.filmProducer);
        TextView director = (TextView) findViewById(R.id.filmDirector);
        TextView description = (TextView) findViewById(R.id.filmDescription);
        TextView releaseDate = (TextView) findViewById(R.id.release);
        TextView rtScore = (TextView) findViewById(R.id.rtscore);

        detailFilmTitle.setText(currentFilm.getTitle());
        littlePoster.setImageResource(imageResource);
        producer.setText(currentFilm.getProducer());
        director.setText(currentFilm.getDirector());
        releaseDate.setText(currentFilm.getRelease_date());
        rtScore.setText(currentFilm.getRt_score());
        description.setText(currentFilm.getDescription());


    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        if (!wasRestored) {
            player.cueVideo(trailers.get(currentFilmTitle)); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = String.format(getString(R.string.player_error), errorReason.toString());
            System.out.println(error);
            //Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_API_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }

    private void showMessage(String message) {
        System.out.println(message);
        //Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        @Override
        public void onPlaying() {
            // Called when playback starts, either due to user action or call to play().
            showMessage("Playing");
        }

        @Override
        public void onPaused() {
            // Called when playback is paused, either due to user action or call to pause().
            showMessage("Paused");
        }

        @Override
        public void onStopped() {
            // Called when playback stops for a reason other than being paused.
            showMessage("Stopped");
        }

        @Override
        public void onBuffering(boolean b) {
            // Called when buffering starts or ends.
        }

        @Override
        public void onSeekTo(int i) {
            // Called when a jump in playback position occurs, either
            // due to user scrubbing or call to seekRelativeMillis() or seekToMillis()
        }
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        @Override
        public void onLoading() {
            // Called when the player is loading a video
            // At this point, it's not ready to accept commands affecting playback such as play() or pause()
        }

        @Override
        public void onLoaded(String s) {
            // Called when a video is done loading.
            // Playback methods such as play(), pause() or seekToMillis(int) may be called after this callback.
        }

        @Override
        public void onAdStarted() {
            // Called when playback of an advertisement starts.
        }

        @Override
        public void onVideoStarted() {
            // Called when playback of the video starts.
        }

        @Override
        public void onVideoEnded() {
            // Called when the video reaches its end.
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            // Called when an error occurs.
        }
    }


    public Film getCurrentFilmFromCache() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        String jsonFilm = getSharedPreferences("saveFilms", Context.MODE_PRIVATE)
                .getString("jsonStringCurrentFilm", null);

        if (jsonFilm == null) {
            return null;
        } else {
            Type type = new TypeToken<Film>() {
            }.getType();
            // On déserialise la liste
            return gson.fromJson(jsonFilm, type);
        }
    }

    public void loadFilmTrailer() {
        trailers.put("spirited_away", "5-cro68n7CE");
        trailers.put("arrietty", "RYwYgH9uA_8");
        trailers.put("castle_in_the_sky", "bubkUJcYmeo");
        trailers.put("from_up_on_poppy_hill", "tVnW2Dk4zdg");
        trailers.put("grave_of_the_fireflies", "4vPeTSRd580");
        trailers.put("howl_s_moving_castle", "pGEsAlY49hw");
        trailers.put("kiki_s_delivery_service", "4bG17OYs-GA");
        trailers.put("my_neighbor_totoro", "92a7Hj0ijLs");
        trailers.put("my_neighbors_the_yamadas", "1C9ujuCPlnY");
        trailers.put("only_yesterday", "OfkQlZArxw0");
        trailers.put("pom_poko", "_7cowIHjCD4");
        trailers.put("ponyo", "YTrEECZhpL0");
        trailers.put("porco_rosso", "awEC-aLDzjs");
        trailers.put("princess_mononoke", "YOuG8m2RqOs");
        trailers.put("tales_from_earthsea", "zxSNcsox7nQ");
        trailers.put("the_cat_returns", "2JVas8usn6o");
        trailers.put("the_tale_of_the_princess_kaguya", "W71mtorCZDw");
        trailers.put("the_wind_rises", "RzSpDgiF5y8");
        trailers.put("when_marnie_was_there", "jjmrxqcQdYg");
        trailers.put("whisper_of_the_heart", "0pVkiod6V0U");
    }
}
