package com.api.supremeAdmision.repository.exam;

import com.api.supremeAdmision.model.ExamKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ExamKeyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ExamKeyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ExamKey> getExamKeys() {
        return jdbcTemplate.query("CALL get_exam_keys()", new ExamKeyRowMapper());
    }

    private static class ExamKeyRowMapper implements RowMapper<ExamKey> {
        @Override
        public ExamKey mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            ExamKey examKey = new ExamKey();
            examKey.setTema(rs.getString("TEMA"));
            examKey.setPreg001(rs.getString("PREG_001"));
            examKey.setPreg002(rs.getString("PREG_002"));
            examKey.setPreg003(rs.getString("PREG_003"));
            examKey.setPreg004(rs.getString("PREG_004"));
            examKey.setPreg005(rs.getString("PREG_005"));
            examKey.setPreg006(rs.getString("PREG_006"));
            examKey.setPreg007(rs.getString("PREG_007"));
            examKey.setPreg008(rs.getString("PREG_008"));
            examKey.setPreg009(rs.getString("PREG_009"));
            examKey.setPreg010(rs.getString("PREG_010"));
            examKey.setPreg011(rs.getString("PREG_011"));
            examKey.setPreg012(rs.getString("PREG_012"));
            examKey.setPreg013(rs.getString("PREG_013"));
            examKey.setPreg014(rs.getString("PREG_014"));
            examKey.setPreg015(rs.getString("PREG_015"));
            examKey.setPreg016(rs.getString("PREG_016"));
            examKey.setPreg017(rs.getString("PREG_017"));
            examKey.setPreg018(rs.getString("PREG_018"));
            examKey.setPreg019(rs.getString("PREG_019"));
            examKey.setPreg020(rs.getString("PREG_020"));
            examKey.setPreg021(rs.getString("PREG_021"));
            examKey.setPreg022(rs.getString("PREG_022"));
            examKey.setPreg023(rs.getString("PREG_023"));
            examKey.setPreg024(rs.getString("PREG_024"));
            examKey.setPreg025(rs.getString("PREG_025"));
            examKey.setPreg026(rs.getString("PREG_026"));
            examKey.setPreg027(rs.getString("PREG_027"));
            examKey.setPreg028(rs.getString("PREG_028"));
            examKey.setPreg029(rs.getString("PREG_029"));
            examKey.setPreg030(rs.getString("PREG_030"));
            examKey.setPreg031(rs.getString("PREG_031"));
            examKey.setPreg032(rs.getString("PREG_032"));
            examKey.setPreg033(rs.getString("PREG_033"));
            examKey.setPreg034(rs.getString("PREG_034"));
            examKey.setPreg035(rs.getString("PREG_035"));
            examKey.setPreg036(rs.getString("PREG_036"));
            examKey.setPreg037(rs.getString("PREG_037"));
            examKey.setPreg038(rs.getString("PREG_038"));
            examKey.setPreg039(rs.getString("PREG_039"));
            examKey.setPreg040(rs.getString("PREG_040"));
            examKey.setPreg041(rs.getString("PREG_041"));
            examKey.setPreg042(rs.getString("PREG_042"));
            examKey.setPreg043(rs.getString("PREG_043"));
            examKey.setPreg044(rs.getString("PREG_044"));
            examKey.setPreg045(rs.getString("PREG_045"));
            examKey.setPreg046(rs.getString("PREG_046"));
            examKey.setPreg047(rs.getString("PREG_047"));
            examKey.setPreg048(rs.getString("PREG_048"));
            examKey.setPreg049(rs.getString("PREG_049"));
            examKey.setPreg050(rs.getString("PREG_050"));
            examKey.setPreg051(rs.getString("PREG_051"));
            examKey.setPreg052(rs.getString("PREG_052"));
            examKey.setPreg053(rs.getString("PREG_053"));
            examKey.setPreg054(rs.getString("PREG_054"));
            examKey.setPreg055(rs.getString("PREG_055"));
            examKey.setPreg056(rs.getString("PREG_056"));
            examKey.setPreg057(rs.getString("PREG_057"));
            examKey.setPreg058(rs.getString("PREG_058"));
            examKey.setPreg059(rs.getString("PREG_059"));
            examKey.setPreg060(rs.getString("PREG_060"));
            examKey.setPreg061(rs.getString("PREG_061"));
            examKey.setPreg062(rs.getString("PREG_062"));
            examKey.setPreg063(rs.getString("PREG_063"));
            examKey.setPreg064(rs.getString("PREG_064"));
            examKey.setPreg065(rs.getString("PREG_065"));
            examKey.setPreg066(rs.getString("PREG_066"));
            examKey.setPreg067(rs.getString("PREG_067"));
            examKey.setPreg068(rs.getString("PREG_068"));
            examKey.setPreg069(rs.getString("PREG_069"));
            examKey.setPreg070(rs.getString("PREG_070"));
            examKey.setPreg071(rs.getString("PREG_071"));
            examKey.setPreg072(rs.getString("PREG_072"));
            examKey.setPreg073(rs.getString("PREG_073"));
            examKey.setPreg074(rs.getString("PREG_074"));
            examKey.setPreg075(rs.getString("PREG_075"));
            examKey.setPreg076(rs.getString("PREG_076"));
            examKey.setPreg077(rs.getString("PREG_077"));
            examKey.setPreg078(rs.getString("PREG_078"));
            examKey.setPreg079(rs.getString("PREG_079"));
            examKey.setPreg080(rs.getString("PREG_080"));
            examKey.setPreg081(rs.getString("PREG_081"));
            examKey.setPreg082(rs.getString("PREG_082"));
            examKey.setPreg083(rs.getString("PREG_083"));
            examKey.setPreg084(rs.getString("PREG_084"));
            examKey.setPreg085(rs.getString("PREG_085"));
            examKey.setPreg086(rs.getString("PREG_086"));
            examKey.setPreg087(rs.getString("PREG_087"));
            examKey.setPreg088(rs.getString("PREG_088"));
            examKey.setPreg089(rs.getString("PREG_089"));
            examKey.setPreg090(rs.getString("PREG_090"));
            examKey.setPreg091(rs.getString("PREG_091"));
            examKey.setPreg092(rs.getString("PREG_092"));
            examKey.setPreg093(rs.getString("PREG_093"));
            examKey.setPreg094(rs.getString("PREG_094"));
            examKey.setPreg095(rs.getString("PREG_095"));
            examKey.setPreg096(rs.getString("PREG_096"));
            examKey.setPreg097(rs.getString("PREG_097"));
            examKey.setPreg098(rs.getString("PREG_098"));
            examKey.setPreg099(rs.getString("PREG_099"));
            examKey.setPreg100(rs.getString("PREG_100"));
            return examKey;
        }
    }
}
