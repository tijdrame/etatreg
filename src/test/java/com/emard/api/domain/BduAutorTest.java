package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BduAutorTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BduAutor.class);
        BduAutor bduAutor1 = new BduAutor();
        bduAutor1.setId(1L);
        BduAutor bduAutor2 = new BduAutor();
        bduAutor2.setId(bduAutor1.getId());
        assertThat(bduAutor1).isEqualTo(bduAutor2);
        bduAutor2.setId(2L);
        assertThat(bduAutor1).isNotEqualTo(bduAutor2);
        bduAutor1.setId(null);
        assertThat(bduAutor1).isNotEqualTo(bduAutor2);
    }
}
