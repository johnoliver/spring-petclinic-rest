/*
 * Copyright 2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for cache configuration.
 * Note: Caching is disabled in test mode (spring.cache.type=none in test application.properties)
 * so this test verifies that caching is properly disabled during tests.
 */
@SpringBootTest
class CacheConfigTests {

    @Autowired(required = false)
    private CacheManager cacheManager;

    @Test
    void testCachingIsDisabledInTestMode() {
        // In test mode, caching is disabled (spring.cache.type=none)
        // Verify that cache operations are no-ops
        assertThat(cacheManager).isNotNull();
        
        // Try to get a cache - it should return a no-op cache
        Cache cache = cacheManager.getCache("vets");
        assertThat(cache).isNotNull();
        
        // Verify cache operations don't actually store anything
        cache.put("testKey", "testValue");
        Cache.ValueWrapper result = cache.get("testKey");
        // In NoOpCacheManager, get always returns null
        assertThat(result).isNull();
    }
}
