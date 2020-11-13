package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BpnaemaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bpnaema.class);
        Bpnaema bpnaema1 = new Bpnaema();
        bpnaema1.setId(1L);
        Bpnaema bpnaema2 = new Bpnaema();
        bpnaema2.setId(bpnaema1.getId());
        assertThat(bpnaema1).isEqualTo(bpnaema2);
        bpnaema2.setId(2L);
        assertThat(bpnaema1).isNotEqualTo(bpnaema2);
        bpnaema1.setId(null);
        assertThat(bpnaema1).isNotEqualTo(bpnaema2);
    }
}
