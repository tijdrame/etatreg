package com.emard.api.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.emard.api.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.emard.api.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.emard.api.domain.User.class.getName());
            createCache(cm, com.emard.api.domain.Authority.class.getName());
            createCache(cm, com.emard.api.domain.User.class.getName() + ".authorities");
            createCache(cm, com.emard.api.domain.BankInfos.class.getName());
            createCache(cm, com.emard.api.domain.FilesInfos.class.getName());
            createCache(cm, com.emard.api.domain.AcheteurVendeur.class.getName());
            createCache(cm, com.emard.api.domain.NatureDebiteur.class.getName());
            createCache(cm, com.emard.api.domain.NatureDepot.class.getName());
            createCache(cm, com.emard.api.domain.ObjetCredit.class.getName());
            createCache(cm, com.emard.api.domain.SecteurActivite.class.getName());
            createCache(cm, com.emard.api.domain.Bp2Infos.class.getName());
            createCache(cm, com.emard.api.domain.Bp3Infos.class.getName());
            createCache(cm, com.emard.api.domain.Bp4Infos.class.getName());
            createCache(cm, com.emard.api.domain.BduCdb.class.getName());
            createCache(cm, com.emard.api.domain.BduDepot.class.getName());
            createCache(cm, com.emard.api.domain.BduEffet.class.getName());
            createCache(cm, com.emard.api.domain.BduAutor.class.getName());
            createCache(cm, com.emard.api.domain.Periode.class.getName());
            createCache(cm, com.emard.api.domain.ChargeFile.class.getName());
            createCache(cm, com.emard.api.domain.Bp1Com.class.getName());
            createCache(cm, com.emard.api.domain.Bp1His.class.getName());
            createCache(cm, com.emard.api.domain.Bp2Com.class.getName());
            createCache(cm, com.emard.api.domain.Bp3His.class.getName());
            createCache(cm, com.emard.api.domain.CrpAtr.class.getName());
            createCache(cm, com.emard.api.domain.Bpdevise.class.getName());
            createCache(cm, com.emard.api.domain.Bpbqe.class.getName());
            createCache(cm, com.emard.api.domain.Bpitrs.class.getName());
            createCache(cm, com.emard.api.domain.Bpnaema.class.getName());
            createCache(cm, com.emard.api.domain.Bppaysiso.class.getName());
            createCache(cm, com.emard.api.domain.Bpsectinst.class.getName());
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
