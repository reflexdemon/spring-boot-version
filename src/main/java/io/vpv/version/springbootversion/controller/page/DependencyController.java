package io.vpv.version.springbootversion.controller.page;

import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.VersionInfo;
import io.vpv.version.springbootversion.service.BootVersionService;
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
public class DependencyController {

    private final String viewName = "dependency";
    private BootVersionService bootVersionService;

    @Autowired
    public DependencyController(BootVersionService bootVersionService) {
        this.bootVersionService = bootVersionService;
    }

    @RequestMapping(value = {"/dependency"}, method = RequestMethod.GET)
    public String dependencyPage(
            ModelMap model,
            @RequestParam(required = false) final String bootVersion,
            HttpServletRequest request
    ) {
        if (null != bootVersion) {
            return ("redirect:" + request.getContextPath() + "/dependency/" + bootVersion);
        } else {
            updateGettingAllVersions(model);
        }

        model.put("viewName", viewName);
        return viewName;
    }

    @RequestMapping(value = {"/dependency/{bootVersion:.+}"}, method = RequestMethod.GET)
    public String dependencyPage(@PathVariable final String bootVersion, ModelMap model) {
        updateGettingAllVersions(model);

        try {
            List<Dependency> dependencies = bootVersionService.getDependencies(bootVersion);

            model.put("dependencies", dependencies);
        } catch (RuntimeException re) {
            model.put("error", re.getLocalizedMessage());
        }

        model.put("bootVersion", bootVersion);
        model.put("viewName", viewName);
        return viewName;
    }

    private void updateGettingAllVersions(ModelMap model) {
        VersionInfo versionInfo = bootVersionService.getAllVersionInfo();
        model.put("versionInfo", versionInfo);
    }
}
