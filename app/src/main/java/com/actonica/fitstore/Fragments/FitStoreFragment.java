package com.actonica.fitstore.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actonica.fitstore.API.JuiceFitAPIHandler;
import com.actonica.fitstore.Adapters.CategoriesAdapter;
import com.actonica.fitstore.ApiResponsesGson.GetCategoriesResponse;
import com.actonica.fitstore.DividerItemDecoration;
import com.actonica.fitstore.Models.Category;
import com.actonica.fitstore.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FitStoreFragment extends Fragment {

    private ProgressDialog progress;
    private RecyclerView categories_rv;
    private Toolbar toolbar;
    private List<Category> loadedCategories;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (loadedCategories == null){
            loadCategories();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fitstore, container, false);

        categories_rv = (RecyclerView) v.findViewById(R.id.categories);
        categories_rv.setHasFixedSize(true);
        toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        toolbar.setTitle("Fit Store");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        if (loadedCategories != null){
            CategoriesAdapter adapter = new CategoriesAdapter(getActivity(), loadedCategories);
            categories_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            categories_rv.addItemDecoration(
                    new DividerItemDecoration(getActivity(), R.drawable.divider));
            categories_rv.setAdapter(adapter);
        }

        return v;
    }

    private void showProgressDialog(String message){
        progress = new ProgressDialog(getActivity());
        progress.setMessage(message);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.setProgress(0);
        progress.show();
    }

    private void loadCategories(){
        showProgressDialog("Загружаем категории...");
        JuiceFitAPIHandler.getCategories(getActivity(), new Callback<GetCategoriesResponse>() {
            @Override
            public void onResponse(Call<GetCategoriesResponse> call, Response<GetCategoriesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loadedCategories = response.body().categories;
                    CategoriesAdapter adapter = new CategoriesAdapter(getActivity(), response.body().categories);
                    categories_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                    categories_rv.addItemDecoration(
                            new DividerItemDecoration(getActivity(), R.drawable.divider));
                    categories_rv.setAdapter(adapter);
                }
                progress.hide();
            }

            @Override
            public void onFailure(Call<GetCategoriesResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
                progress.hide();
            }
        });
    }
}
