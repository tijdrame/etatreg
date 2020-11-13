package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BppaysisoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bppaysiso.class);
        Bppaysiso bppaysiso1 = new Bppaysiso();
        bppaysiso1.setId(1L);
        Bppaysiso bppaysiso2 = new Bppaysiso();
        bppaysiso2.setId(bppaysiso1.getId());
        assertThat(bppaysiso1).isEqualTo(bppaysiso2);
        bppaysiso2.setId(2L);
        assertThat(bppaysiso1).isNotEqualTo(bppaysiso2);
        bppaysiso1.setId(null);
        assertThat(bppaysiso1).isNotEqualTo(bppaysiso2);
    }
}
