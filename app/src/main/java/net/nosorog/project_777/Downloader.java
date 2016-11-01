package net.nosorog.project_777;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Downloader {
    @GET("data.json")
    Call<Item[]> getItems();
}