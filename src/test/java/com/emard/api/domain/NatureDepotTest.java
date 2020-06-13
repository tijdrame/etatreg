package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class NatureDepotTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(NatureDepot.class);
        NatureDepot natureDepot1 = new NatureDepot();
        natureDepot1.setId(1L);
        NatureDepot natureDepot2 = new NatureDepot();
        natureDepot2.setId(natureDepot1.getId());
        assertThat(natureDepot1).isEqualTo(natureDepot2);
        natureDepot2.setId(2L);
        assertThat(natureDepot1).isNotEqualTo(natureDepot2);
        natureDepot1.setId(null);
        assertThat(natureDepot1).isNotEqualTo(natureDepot2);
    }
}
