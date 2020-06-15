package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BduEffetTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BduEffet.class);
        BduEffet bduEffet1 = new BduEffet();
        bduEffet1.setId(1L);
        BduEffet bduEffet2 = new BduEffet();
        bduEffet2.setId(bduEffet1.getId());
        assertThat(bduEffet1).isEqualTo(bduEffet2);
        bduEffet2.setId(2L);
        assertThat(bduEffet1).isNotEqualTo(bduEffet2);
        bduEffet1.setId(null);
        assertThat(bduEffet1).isNotEqualTo(bduEffet2);
    }
}
