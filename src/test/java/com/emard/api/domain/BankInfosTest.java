package com.emard.api.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.emard.api.web.rest.TestUtil;

public class BankInfosTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BankInfos.class);
        BankInfos bankInfos1 = new BankInfos();
        bankInfos1.setId(1L);
        BankInfos bankInfos2 = new BankInfos();
        bankInfos2.setId(bankInfos1.getId());
        assertThat(bankInfos1).isEqualTo(bankInfos2);
        bankInfos2.setId(2L);
        assertThat(bankInfos1).isNotEqualTo(bankInfos2);
        bankInfos1.setId(null);
        assertThat(bankInfos1).isNotEqualTo(bankInfos2);
    }
}
