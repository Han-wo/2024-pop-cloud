package com.api.global.config;

import com.api.auth.presentation.support.OAuthArgumentResolver;
import com.api.global.config.interceptor.auth.LoginValidCheckerInterceptor;
import com.api.global.config.interceptor.auth.ParseMemberIdFromTokenInterceptor;
import com.api.global.config.interceptor.auth.PathMatcherInterceptor;
import com.api.global.config.resolver.AuthArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.api.global.config.interceptor.auth.support.HttpMethod.DELETE;
import static com.api.global.config.interceptor.auth.support.HttpMethod.GET;
import static com.api.global.config.interceptor.auth.support.HttpMethod.OPTIONS;
import static com.api.global.config.interceptor.auth.support.HttpMethod.PATCH;
import static com.api.global.config.interceptor.auth.support.HttpMethod.POST;

@RequiredArgsConstructor
@Configuration
public class AuthConfig implements WebMvcConfigurer {

    private final AuthArgumentResolver authArgumentResolver;
    private final OAuthArgumentResolver oAuthArgumentResolver;
    private final ParseMemberIdFromTokenInterceptor parseMemberIdFromTokenInterceptor;
    private final LoginValidCheckerInterceptor loginValidCheckerInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(parseMemberIdFromTokenInterceptor());
        registry.addInterceptor(loginValidCheckerInterceptor());
    }

    private HandlerInterceptor parseMemberIdFromTokenInterceptor() {
        return new PathMatcherInterceptor(parseMemberIdFromTokenInterceptor)
                .excludePathPattern("/**", OPTIONS);
    }

    private HandlerInterceptor loginValidCheckerInterceptor() {
        return new PathMatcherInterceptor(loginValidCheckerInterceptor)
                .excludePathPattern("/**", OPTIONS)
                .addPathPatterns("/members/test", GET, POST, PATCH, DELETE);
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authArgumentResolver);
        resolvers.add(oAuthArgumentResolver);
    }
}
