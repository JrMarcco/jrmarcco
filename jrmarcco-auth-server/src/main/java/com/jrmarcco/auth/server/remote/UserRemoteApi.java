package com.jrmarcco.auth.server.remote;

import com.jrmarcco.user.client.api.UserApi;
import com.jrmarcco.user.client.constant.UserServerConstants;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author hongjc
 * @version 1.0  2019/1/28
 */
@FeignClient(value = UserServerConstants.SERVICE_ID)
public interface UserRemoteApi extends UserApi {
}
