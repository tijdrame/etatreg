package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BduDepotTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BduDepot.class);
        BduDepot bduDepot1 = new BduDepot();
        bduDepot1.setId(1L);
        BduDepot bduDepot2 = new BduDepot();
        bduDepot2.setId(bduDepot1.getId());
        assertThat(bduDepot1).isEqualTo(bduDepot2);
        bduDepot2.setId(2L);
        assertThat(bduDepot1).isNotEqualTo(bduDepot2);
        bduDepot1.setId(null);
        assertThat(bduDepot1).isNotEqualTo(bduDepot2);
    }
}
