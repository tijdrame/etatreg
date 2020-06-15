package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BduCdbTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BduCdb.class);
        BduCdb bduCdb1 = new BduCdb();
        bduCdb1.setId(1L);
        BduCdb bduCdb2 = new BduCdb();
        bduCdb2.setId(bduCdb1.getId());
        assertThat(bduCdb1).isEqualTo(bduCdb2);
        bduCdb2.setId(2L);
        assertThat(bduCdb1).isNotEqualTo(bduCdb2);
        bduCdb1.setId(null);
        assertThat(bduCdb1).isNotEqualTo(bduCdb2);
    }
}
