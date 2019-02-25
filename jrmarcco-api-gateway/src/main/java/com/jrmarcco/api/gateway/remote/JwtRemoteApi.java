package com.jrmarcco.api.gateway.remote;

import com.jrmarcco.api.gateway.fallback.JwtApiFallback;
import com.jrmarcco.auth.client.api.JwtApi;
import com.jrmarcco.auth.client.constant.AuthConstants;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hongjc
 * @version 1.0  2019/2/18
 */
@FeignClient(value = AuthConstants.SERVICE_ID)
public interface JwtRemoteApi extends JwtApi {
}
