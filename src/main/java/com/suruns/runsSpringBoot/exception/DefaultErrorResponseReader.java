package com.suruns.runsSpringBoot.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.suruns.runsSpringBoot.common.ErrorResponse;
import com.suruns.runsSpringBoot.common.RunsResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: suruns
 * @Date: 2020年01月14日 16:54
 * @Desc:
 */
public class DefaultErrorResponseReader implements ErrorResponseReader {

    private static final String DEFAULT_ERROR_RESPONSE_SIGN = "default";
    private static final String RESPONSE_CODE_SIGN = "code";
    private static final String RESPONSE_MESSAGE_SIGN = "message";
    private static final String RESPONSE_INCLUDE_LIST_SIGN = "include";
    private static final String RESPONSE_NAME_SIGN = "name";
    private static final String RESPONSE_DATA_SIGN = "response";

    private ConfigurationErrorResponseFactory factory;

    public DefaultErrorResponseReader(ConfigurationErrorResponseFactory factory) {
        this.factory = factory;
    }

    @Override
    public void registerErrorResponse(InputStream in) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            StringBuilder sb = new StringBuilder();
            String l = null;
            while ((l = reader.readLine()) != null) {
                sb.append(l);
            }
            doRegisterErrorResponse(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doRegisterErrorResponse(String toString) {
        JSONObject jsonObject = JSON.parseObject(toString);

        if (jsonObject.containsKey(DEFAULT_ERROR_RESPONSE_SIGN)) {
            this.factory.registerDefaultErrorResponse(buildErrorResponse(jsonObject.get(DEFAULT_ERROR_RESPONSE_SIGN)));
        } else {
            this.factory.registerDefaultErrorResponse(ErrorResponseEnum.INTERNET_SERVER_ERROR);
        }
        if (jsonObject.containsKey(RESPONSE_INCLUDE_LIST_SIGN)) {
            JSONArray jsonArray = jsonObject.getJSONArray(RESPONSE_INCLUDE_LIST_SIGN);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject response = jsonArray.getJSONObject(i);
                this.factory.registerErrorResponse(response.getString(RESPONSE_NAME_SIGN),
                        buildErrorResponse(response.get(RESPONSE_DATA_SIGN)));
            }
        }
    }

    private ErrorResponse buildErrorResponse(Object data) {
        if (data instanceof String) {
            return ErrorResponseEnum.valueOf(data.toString()).getRunsResponse();
        } else {
            JSONObject response = (JSONObject) data;
            return new RunsResponse
                    .Builder()
                    .instance(response.get(RESPONSE_CODE_SIGN), response.get(RESPONSE_MESSAGE_SIGN))
                    .build();
        }
    }
}
