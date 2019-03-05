package com.jrmarcco.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * @author hongjc
 * @version 1.0  2019/1/11
 */
@Slf4j
public class HttpUtils {

    // ====================================================================================================
    //                                   Get Method
    // ====================================================================================================
    public static String get(String uri, Collection<Header> headers, Map<String, Object> map, Charset charset) {
        try (var client = HttpClients.createDefault()) {

            var builder = new URIBuilder(uri);
            Optional.ofNullable(map).ifPresent(params -> params.forEach((k, v) -> builder.setParameter(k, v.toString())));

            var httpGet = new HttpGet(builder.build());

            Optional.ofNullable(headers).ifPresent(hs -> hs.forEach(httpGet::setHeader));

            try (var response = client.execute(httpGet)) {
                return EntityUtils.toString(response.getEntity(), charset);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String get(String uri, Collection<Header> headers, Map<String, Object> map) {
        return get(uri, headers, map, StandardCharsets.UTF_8);
    }

    public static String get(String uri, Collection<Header> headers) {
        return get(uri, headers, null);
    }

    public static String get(String uri, Map<String, Object> map) {
        return get(uri, null, map);
    }

    public static String get(String uri) {
        return get(uri, null, null);
    }

    // ====================================================================================================
    //                                   Post Method
    // ====================================================================================================
    public static String postForm(String uri, Collection<Header> headers, Map<String, Object> map) {
        try (var client = HttpClients.createDefault()) {
            var httpPost = new HttpPost(uri);

            Optional.ofNullable(headers).ifPresent(h -> h.forEach(httpPost::setHeader));

            if (map != null) {
                var params = map.entrySet().stream().map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue().toString())).collect(toList());
                httpPost.setEntity(new UrlEncodedFormEntity(params));
            }

            try (var response = client.execute(httpPost)) {
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String postForm(String uri, Map<String, Object> map) {
        return postForm(uri, null, map);
    }

    public static String postString(String uri, Collection<Header> headers, Object object) {
        try (var client = HttpClients.createDefault()) {
            var httpPost = new HttpPost(uri);
            headers.forEach(httpPost::setHeader);

            Optional.ofNullable(object).map(JsonUtils::toJson).ifPresent(json -> httpPost.setEntity(new StringEntity(json, StandardCharsets.UTF_8)));

            try (var response = client.execute(httpPost)) {
                return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String postJson(String uri, Object object) {
        return postString(uri, Collections.singleton(new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType())), object);
    }

}