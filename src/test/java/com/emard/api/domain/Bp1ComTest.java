package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class Bp1ComTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bp1Com.class);
        Bp1Com bp1Com1 = new Bp1Com();
        bp1Com1.setId(1L);
        Bp1Com bp1Com2 = new Bp1Com();
        bp1Com2.setId(bp1Com1.getId());
        assertThat(bp1Com1).isEqualTo(bp1Com2);
        bp1Com2.setId(2L);
        assertThat(bp1Com1).isNotEqualTo(bp1Com2);
        bp1Com1.setId(null);
        assertThat(bp1Com1).isNotEqualTo(bp1Com2);
    }
}
