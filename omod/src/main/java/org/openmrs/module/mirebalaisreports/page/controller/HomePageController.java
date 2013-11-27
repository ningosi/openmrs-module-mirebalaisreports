package org.openmrs.module.mirebalaisreports.page.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.mirebalaisreports.MirebalaisReportsProperties;
import org.openmrs.module.mirebalaisreports.definitions.AllPatientsWithIdsReportManager;
import org.openmrs.module.mirebalaisreports.definitions.DailyCheckInsReportManager;
import org.openmrs.module.mirebalaisreports.definitions.DailyClinicalEncountersReportManager;
import org.openmrs.module.mirebalaisreports.definitions.DailyRegistrationsReportManager;
import org.openmrs.module.mirebalaisreports.definitions.LqasDiagnosesReportManager;
import org.openmrs.module.mirebalaisreports.definitions.NonCodedDiagnosesReportManager;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;

public class HomePageController {

	private final Log log = LogFactory.getLog(getClass());

    public void get(@SpringBean DailyRegistrationsReportManager dailyRegistrationsReportManager,
                    @SpringBean DailyCheckInsReportManager dailyCheckInsReportManager,
                    @SpringBean DailyClinicalEncountersReportManager dailyClinicalEncountersReportManager,
					@SpringBean NonCodedDiagnosesReportManager nonCodedDiagnosesReportManager,
                    @SpringBean LqasDiagnosesReportManager lqasDiagnosesReportManager,
                    @SpringBean AllPatientsWithIdsReportManager allPatientsWithIdsReportManager,
                    @SpringBean MirebalaisReportsProperties mirebalaisReportsProperties,
                    PageModel pageModel) {

		// TODO: Move this all into the reports or some external configuration

		pageModel.addAttribute("dailyRegistrationsReport", dailyRegistrationsReportManager);
        pageModel.addAttribute("dailyCheckInsReport", dailyCheckInsReportManager);
        pageModel.addAttribute("dailyClinicalEncountersReport", dailyClinicalEncountersReportManager);
		pageModel.addAttribute("nonCodedDiagnosesReport", nonCodedDiagnosesReportManager);
        pageModel.addAttribute("lqasDiagnosesReport", lqasDiagnosesReportManager);
        pageModel.addAttribute("allPatientsWithIdsReportManager", allPatientsWithIdsReportManager);
        pageModel.addAttribute("properties", mirebalaisReportsProperties);
    }
}
