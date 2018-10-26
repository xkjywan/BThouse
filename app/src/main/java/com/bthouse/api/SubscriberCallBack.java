package com.bthouse.api;

import com.bthouse.mvp.module.ResultResponse;
import com.bthouse.util.UIUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import rx.Subscriber;


/**
 * @author ChayChan
 * @description: 抽取CallBack
 * @date 2017/6/18  21:37
 */
public abstract class SubscriberCallBack<T> extends Subscriber<ResultResponse<T>> {

    @Override
    public void onNext(ResultResponse response) {
        if(response!=null&&response.getCode()!=null) {
            String isSuccess = response.getCode();
            if (isSuccess.equals("0")) {
                if(response.getData()!=null){
                    onSuccess((T) response.getData());
                }else{
                    onSuccess((T) response);
                }
            } else{
                UIUtils.showToast(response.getInfo());
                onFailure(response);
            }
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onError();
    }

    protected abstract void onSuccess(T response);
    protected abstract void onError();
    protected void onFailure(ResultResponse response) {
    }

    public <T> ArrayList<T> fromJsonList(String json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
//            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }


}
