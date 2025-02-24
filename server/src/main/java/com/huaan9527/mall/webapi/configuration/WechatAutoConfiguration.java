package com.huaan9527.mall.webapi.configuration;

import com.huaan9527.mall.webapi.social.jdbc.JhJdbcUsersConnectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.wechat2.WechatProviderType;
import org.springframework.social.wechat2.configuration.WechatProperties;
import org.springframework.social.wechat2.oauth2.WechatConnectionFactory;

import javax.sql.DataSource;


@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(WechatProperties.class)
public class WechatAutoConfiguration implements SocialConfigurer {

    private final WechatProperties properties;
    private DataSource dataSource;
    private ConnectionSignUp connectionSignUp;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        WechatProviderType providerType = WechatProviderType.valueOf(properties.getProviderType());
        final WechatConnectionFactory factory = new WechatConnectionFactory(providerType, properties.getAppId(),
                properties.getAppSecret(), properties.getAuthorizeUrl(),
                properties.getAccessTokenApiUrl());
        factory.setScope(properties.getScope());
        connectionFactoryConfigurer.addConnectionFactory(factory);
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JhJdbcUsersConnectionRepository connectionRepository = new JhJdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        connectionRepository.setConnectionSignUp(connectionSignUp);
        return connectionRepository;
    }

}
