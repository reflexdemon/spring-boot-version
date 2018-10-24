package io.vpv.version.springbootversion.controller.page;

import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.VersionInfo;
import io.vpv.version.springbootversion.modal.VersionSummary;
import io.vpv.version.springbootversion.service.BootVersionService;
import io.vpv.version.springbootversion.service.CompareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CompareController {

    private final String viewName = "compare";
    private BootVersionService bootVersionService;

    private CompareService compareService;

    @Autowired
    public CompareController(BootVersionService bootVersionService, CompareService compareService) {
        this.bootVersionService = bootVersionService;
        this.compareService = compareService;
    }

    @RequestMapping(value = {"/compare"}, method = RequestMethod.GET)
    public String dependencyPage(
            ModelMap model,
            @RequestParam(required = false) final String firstVersion,
            @RequestParam(required = false) final String secondVersion,
            HttpServletRequest request
    ) {
        if (null != firstVersion && null != secondVersion) {
            return ("redirect:" + request.getContextPath() + "/compare/" + firstVersion + "/" + secondVersion);
        } else {
            updateGettingAllVersions(model);
        }

        model.put("viewName", viewName);
        return viewName;
    }

    @RequestMapping(value = {"/compare/{firstVersion:.+}/{secondVersion:.+}"}, method = RequestMethod.GET)
    public String dependencyPage(@PathVariable final String firstVersion,
                                 @PathVariable final String secondVersion,
                                 ModelMap model) {
        updateGettingAllVersions(model);

        try {
            VersionSummary compareResult = compareService.merge(firstVersion, secondVersion);

            model.put("compareResult", compareResult);
        } catch (RuntimeException re) {
            model.put("error", re.getLocalizedMessage());
        }

        model.put("firstVersion", firstVersion);
        model.put("secondVersion", secondVersion);

        model.put("viewName", viewName);
        return viewName;
    }

    private void updateGettingAllVersions(ModelMap model) {
        VersionInfo versionInfo = bootVersionService.getAllVersionInfo();
        model.put("versionInfo", versionInfo);
    }
}
