package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BpitrsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bpitrs.class);
        Bpitrs bpitrs1 = new Bpitrs();
        bpitrs1.setId(1L);
        Bpitrs bpitrs2 = new Bpitrs();
        bpitrs2.setId(bpitrs1.getId());
        assertThat(bpitrs1).isEqualTo(bpitrs2);
        bpitrs2.setId(2L);
        assertThat(bpitrs1).isNotEqualTo(bpitrs2);
        bpitrs1.setId(null);
        assertThat(bpitrs1).isNotEqualTo(bpitrs2);
    }
}
