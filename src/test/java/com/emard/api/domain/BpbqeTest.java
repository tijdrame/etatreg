package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BpbqeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bpbqe.class);
        Bpbqe bpbqe1 = new Bpbqe();
        bpbqe1.setId(1L);
        Bpbqe bpbqe2 = new Bpbqe();
        bpbqe2.setId(bpbqe1.getId());
        assertThat(bpbqe1).isEqualTo(bpbqe2);
        bpbqe2.setId(2L);
        assertThat(bpbqe1).isNotEqualTo(bpbqe2);
        bpbqe1.setId(null);
        assertThat(bpbqe1).isNotEqualTo(bpbqe2);
    }
}
