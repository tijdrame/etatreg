package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class Bp2ComTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bp2Com.class);
        Bp2Com bp2Com1 = new Bp2Com();
        bp2Com1.setId(1L);
        Bp2Com bp2Com2 = new Bp2Com();
        bp2Com2.setId(bp2Com1.getId());
        assertThat(bp2Com1).isEqualTo(bp2Com2);
        bp2Com2.setId(2L);
        assertThat(bp2Com1).isNotEqualTo(bp2Com2);
        bp2Com1.setId(null);
        assertThat(bp2Com1).isNotEqualTo(bp2Com2);
    }
}
