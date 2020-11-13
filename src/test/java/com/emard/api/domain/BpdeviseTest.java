package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BpdeviseTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bpdevise.class);
        Bpdevise bpdevise1 = new Bpdevise();
        bpdevise1.setId(1L);
        Bpdevise bpdevise2 = new Bpdevise();
        bpdevise2.setId(bpdevise1.getId());
        assertThat(bpdevise1).isEqualTo(bpdevise2);
        bpdevise2.setId(2L);
        assertThat(bpdevise1).isNotEqualTo(bpdevise2);
        bpdevise1.setId(null);
        assertThat(bpdevise1).isNotEqualTo(bpdevise2);
    }
}
