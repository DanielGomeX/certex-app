package com.certex.certexapp.service;


import com.certex.certexapp.model.CEP;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIRetrofitService {

    String BASE_URL = "https://viacep.com.br/ws/";

    /* Retorna apenas um objeto CEP */
    @GET("{CEP}/json/")
    Call<CEP> getEnderecoByCEP(@Path("CEP") String CEP);

}