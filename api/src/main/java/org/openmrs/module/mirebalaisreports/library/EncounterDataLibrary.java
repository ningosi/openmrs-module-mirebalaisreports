/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */

package org.openmrs.module.mirebalaisreports.library;

import org.openmrs.module.mirebalaisreports.MirebalaisReportsProperties;
import org.openmrs.module.mirebalaisreports.MirebalaisReportsUtil;
import org.openmrs.module.reporting.data.encounter.definition.EncounterDataDefinition;
import org.openmrs.module.reporting.data.encounter.definition.SqlEncounterDataDefinition;
import org.openmrs.module.reporting.definition.library.BaseDefinitionLibrary;
import org.openmrs.module.reporting.definition.library.DocumentedDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
public class EncounterDataLibrary extends BaseDefinitionLibrary<EncounterDataDefinition> {

    @Autowired
    MirebalaisReportsProperties props;

    @Override
    public Class<? super EncounterDataDefinition> getDefinitionType() {
        return EncounterDataDefinition.class;
    }

    @Override
    public String getKeyPrefix() {
        return "mirebalais.encounterDataCalculation.";
    }

    @DocumentedDefinition("returnVisitDate")
    public EncounterDataDefinition getReturnVisitDate() {
        return sqlEncounterDataDefinition("returnVisitDate.sql", new Replacements().add("returnVisitDate", props.getReturnVisitDate().getId()));
    }

    private EncounterDataDefinition sqlEncounterDataDefinition(String resourceName, Replacements replacements) {
        String sql = MirebalaisReportsUtil.getStringFromResource("org/openmrs/module/mirebalaisreports/sql/encounterData/" + resourceName);
        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            sql = sql.replaceAll(":" + entry.getKey(), entry.getValue());
        }

        SqlEncounterDataDefinition definition = new SqlEncounterDataDefinition();
        definition.setSql(sql);
        return definition;
    }

    private class Replacements extends HashMap<String, String> {
        public Replacements add(String key, Object replacement) {
            super.put(key, replacement.toString());
            return this;
        }
    }
}