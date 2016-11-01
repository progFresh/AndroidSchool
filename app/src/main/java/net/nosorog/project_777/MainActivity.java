package net.nosorog.project_777;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Item[]>, TapListener {

    private static final String URL_ADDRESS = "https://agency5-mobile-school.firebaseio.com/";

    ProgressBar bar;
    Button retryButton;
    RecyclerView itemsRecView;
    ItemsAdapter itemsAdapter;
    Downloader downloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        bar = (ProgressBar) findViewById(R.id.bar);
        retryButton = (Button) findViewById(R.id.retry_button);
        itemsRecView = (RecyclerView) findViewById(R.id.items);

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloader.getItems().enqueue(MainActivity.this);
                retryButton.setVisibility(View.GONE);
                bar.setVisibility(View.VISIBLE);
            }
        });

         Gson firmGson = new GsonBuilder()
                 .registerTypeAdapter(Item[].class, new Deserializer())
                 .create();

         downloader = new Retrofit.Builder()
                .baseUrl(URL_ADDRESS)
                .addConverterFactory(GsonConverterFactory.create(firmGson))
                .build()
                .create(Downloader.class);

         downloader.getItems().enqueue(this);
    }

    @Override
    public void onResponse(Call<Item[]> call, Response<Item[]> response) {
        Item[] items = response.body();
        bar.setVisibility(View.GONE);
        itemsAdapter = new ItemsAdapter(items, this);
        itemsRecView.setAdapter(itemsAdapter);
    }

    @Override
    public void onFailure(Call<Item[]> call, Throwable t) {
        Toast.makeText(this, R.string.error_toast,Toast.LENGTH_SHORT).show();
        bar.setVisibility(View.GONE);
        retryButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTap(int number) {
        Item item = itemsAdapter.getItem(number);
        Intent intent = new Intent(this, FullInformationActivity.class);
        intent.putExtra("about", item.getAbout());
        intent.putExtra("address", item.getAddress());
        intent.putExtra("company", item.getCompany());
        intent.putExtra("email", item.getEmail());
        intent.putExtra("first_name", item.getFirstName());
        intent.putExtra("id", item.getId());
        intent.putExtra("last_name", item.getLastName());
        intent.putExtra("phone", item.getPhone());
        intent.putExtra("photo", item.getPhoto());
        startActivity(intent);
    }
}
