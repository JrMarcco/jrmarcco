package com.jrmarcco.api.gateway.filter;

import com.jrmarcco.api.gateway.config.GatewayConfiguration;
import com.jrmarcco.api.gateway.remote.JwtRemoteApi;
import com.jrmarcco.auth.client.dto.ValidateTokenReq;
import com.jrmarcco.common.base.BaseResult;
import com.jrmarcco.common.constant.BaseConstants;
import com.jrmarcco.common.exception.uaa.UaaError;
import com.jrmarcco.commons.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author hongjc
 * @version 1.0  2019/1/21
 */
@Slf4j
@Component
@RequiredArgsConstructor

public class AccessFilter implements GlobalFilter, Ordered {

    private final GatewayConfiguration gatewayConfiguration;

    private final JwtRemoteApi jwtRemoteApi;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var request = exchange.getRequest();
        var requestUri = request.getPath().pathWithinApplication().value();

        var mutateRequestBuilder = request.mutate();
        // 不进行拦截的地址
        if (isIgnorePath(requestUri)) {
            var mutateRequest = mutateRequestBuilder.build();
            return chain.filter(exchange.mutate().request(mutateRequest).build());
        }

        // 校验token
        var accessToken = getAccessToken(request, mutateRequestBuilder);
        if (StringUtils.isEmpty(accessToken)) {
            return errorMono(exchange, new BaseResult<Void>().error(UaaError.InvalidToken));
        }

        var validateTokenResult = jwtRemoteApi.validateToken(new ValidateTokenReq(accessToken, requestUri));
        if (!BaseConstants.RESULT_CODE_SUCCESS.equals(validateTokenResult.getCode())) {
            return errorMono(exchange, validateTokenResult);
        }

        return chain.filter(exchange.mutate().request(mutateRequestBuilder.build()).build());
    }

    @Override
    public int getOrder() {
        return 0;
    }

    // ====================================================================================================
    //                                   Private Method
    // ====================================================================================================
    private boolean isIgnorePath(String requestUri) {
        for (var s : gatewayConfiguration.getIgnorePaths().split(",")) {
            if (requestUri.startsWith(s)) {
                return true;
            }
        }
        return false;
    }

    private Mono<Void> errorMono(ServerWebExchange exchange, BaseResult<Void> result) {
        exchange.getResponse().setStatusCode(HttpStatus.OK);
        var bytes = Optional.ofNullable(JsonUtils.toJson(result)).map(String::getBytes).get();
        var buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Flux.just(buffer));
    }

    private String getAccessToken(ServerHttpRequest request, ServerHttpRequest.Builder mutateRequestBuilder) {
        var headerList = request.getHeaders().get(gatewayConfiguration.getTokenHeader());

        if (CollectionUtils.isEmpty(headerList)) {
            var paramList = request.getQueryParams().get("token");
            if (CollectionUtils.isEmpty(paramList)) {
                return null;
            }
            return paramList.get(0);
        } else {
            var token = headerList.get(0);
            mutateRequestBuilder.header(gatewayConfiguration.getTokenHeader(), token);
            return token;
        }
    }
}