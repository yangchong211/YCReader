//package com.yc.mapgoogle;
//
//import com.yun.api.ApiConfig.Builder;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;
//import okhttp3.ResponseBody;
//import okhttp3.Interceptor.Chain;
//
//public class ApiServiceImpl {
//    private com.yun.api.service.ApiServiceImpl apiService;
//
//    public ApiServiceImpl() {
//        ArrayList<Interceptor> interceptors = new ArrayList();
//        ArrayList<Interceptor> networkInterceptors = new ArrayList();
//        HashMap<String, String> headers = new HashMap();
//        interceptors.add(new ApiServiceImpl.CookiesInterceptor());
//        this.apiService = com.yun.api.service.ApiServiceImpl.createApiServiceImpl((new Builder()).readTimeout(30000L).baseUrl("https://maps.googleapis.com/").retryOnConnectionFailure(false).header(headers).networkInterceptor(networkInterceptors).interceptor(interceptors).build());
//    }
//
//    public com.yun.api.service.ApiServiceImpl getApiService() {
//        return this.apiService;
//    }
//
//    private class CookiesInterceptor implements Interceptor {
//        public CookiesInterceptor() {
//        }
//
//        public Response intercept(Chain chain) throws IOException {
//            okhttp3.Request.Builder builder = chain.request().newBuilder();
//            RequestBody requestBody = chain.request().body();
//            if (requestBody != null) {
//                builder.method(chain.request().method(), requestBody);
//            }
//
//            Request newRequest = builder.build();
//            Response response = chain.proceed(newRequest);
//            if (response.body() != null) {
//                String response_body = response.body().string();
//                Response newResponse = response.newBuilder().body(ResponseBody.create(response.body().contentType(), response_body)).build();
//                return newResponse;
//            } else {
//                return chain.proceed(newRequest);
//            }
//        }
//    }
//}
//
