package com.actonica.fitstore.Helpers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.actonica.fitstore.API.JuiceFitAPIHandler;
import com.actonica.fitstore.Adapters.ExtendedProgramsAdapter;
import com.actonica.fitstore.ApiResponsesGson.GetFullProgramResponse;
import com.actonica.fitstore.ApiResponsesGson.GetProgramsResponse;
import com.actonica.fitstore.DividerItemDecoration;
import com.actonica.fitstore.Downloader.Downloader;
import com.actonica.fitstore.Models.Bit;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.Models.Training;
import com.actonica.fitstore.R;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ilgar on 03.07.2016.
 */
public class UserInfoSyncer {

    public static void fillActivePrograms(final Context ctx){
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(ctx).build();
        Realm.setDefaultConfiguration(realmConfig);
        JuiceFitAPIHandler.getUserPrograms(ctx, new Callback<GetProgramsResponse>() {
            @Override
            public void onResponse(Call<GetProgramsResponse> call, Response<GetProgramsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Program> programs = response.body().programs;
                    if (programs.size() > 0) {
                        for (final Program prog : programs) {


                            JuiceFitAPIHandler.getFullProgram(prog.getId(), ctx, new Callback<GetFullProgramResponse>() {
                                @Override
                                public void onResponse(Call<GetFullProgramResponse> call, Response<GetFullProgramResponse> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        Program fullProgram = response.body().program;
                                        fullProgram.setNext_training_id(prog.getNext_training_id());

                                        int i=0;
                                        for  (Training train : fullProgram.getTrainings())
                                        {
                                            if (train.getId() == prog.getNext_training_id())
                                            {
                                                break;
                                            }
                                            for (Bit bit : fullProgram.getTrainings().get(i).getBits())
                                            {
                                                bit.setFinished(true);
                                            }
                                            fullProgram.setUsed(true);
                                            fullProgram.setLastUsedAt(prog.getUpdatedAt());
                                            i++;
                                        }

                                        Realm realm = Realm.getDefaultInstance();
                                        realm.beginTransaction();
                                        realm.copyToRealm(fullProgram);
                                        realm.commitTransaction();

                                        Downloader.getInstance(ctx).startDownload(fullProgram);
                                    }
                                }

                                @Override
                                public void onFailure(Call<GetFullProgramResponse> call, Throwable t) {
                                    Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });





                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProgramsResponse> call, Throwable t) {
                Toast.makeText(ctx, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
