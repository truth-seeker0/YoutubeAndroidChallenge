package com.androidapptech.youtubeandroidchallenge.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.androidapptech.youtubeandroidchallenge.Config;
import com.androidapptech.youtubeandroidchallenge.R;
import com.androidapptech.youtubeandroidchallenge.adapter.ExpandableRecyclerAdapter;
import com.androidapptech.youtubeandroidchallenge.adapter.ExpandableRecyclerViewAdapter;
import com.androidapptech.youtubeandroidchallenge.pojo_model.ListItem;
import com.androidapptech.youtubeandroidchallenge.pojo_model.Playlist;
import com.androidapptech.youtubeandroidchallenge.pojo_model.YoutubeList;
import com.androidapptech.youtubeandroidchallenge.retrofit.YoutubeService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements ExpandableRecyclerViewAdapter.OnItemClickListener {

    List<ExpandableRecyclerViewAdapter.YoutubeleListItem> items;
    private RecyclerView mRecyclerView;
    private ExpandableRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);


        YoutubeService youtubeService = YoutubeService.retrofit.create(YoutubeService.class);
        Call<YoutubeList> call = youtubeService.getLists();
        call.enqueue(new Callback<YoutubeList>() {
            @Override
            public void onResponse(Call<YoutubeList> call, Response<YoutubeList> response) {
                YoutubeList youtubeList = response.body();
                List<Playlist> playItems = youtubeList.getPlaylists();
                for (int i = 0; i < playItems.size(); i++) {
                    List<ListItem> list = playItems.get(i).getListItems();
                    items.add(new ExpandableRecyclerViewAdapter.YoutubeleListItem(playItems.get(i).getListTitle()));
                    for (int y = 0; y < list.size(); y++) {
                        items.add(new ExpandableRecyclerViewAdapter.YoutubeleListItem(list.get(y).getTitle(), list.get(y).getLink(), list.get(y).getThumb()));

                    }
                }
                //notifyDataSetChanged not work
                //adapter.notifyDataSetChanged();
                adapter = new ExpandableRecyclerViewAdapter(MainActivity.this, items);
                adapter.setOnItemClickListener(MainActivity.this);
                //collapse all
                //adapter.setMode(ExpandableRecyclerAdapter.MODE_ACCORDION);
                mRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<YoutubeList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*******************************************************************************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_expand_all:
                adapter.expandAll();
                return true;
            case R.id.action_collapse_all:
                adapter.collapseAll();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 0 - title
     * 1 - url
     *
     * @param parameters
     */
    @Override
    public void onItemClick(String... parameters) {
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(parameters[1])));
        //https://www.youtube.com/watch?v=<VIDEO_ID>
        String url = parameters[1];
        //second parameter VIDEO_ID
        String array[] = url.split("=");

        Intent intent = new Intent(this, YouTubePlayerFragmentActivity.class);
        intent.putExtra(Config.VIDEO_ID, array[1]);
        startActivity(intent);

    }
}
