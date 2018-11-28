package io.vpv.version.springbootversion.controller.page;

import io.vpv.version.springbootversion.service.BootVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {
    private BootVersionService bootVersionService;

    @Autowired
    public HomeController(BootVersionService bootVersionService) {
        this.bootVersionService = bootVersionService;
    }

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String homePage(ModelMap model) {
        List<String> versionInfo = bootVersionService.getDocumentedVersionList();
        model.put("versionInfo", versionInfo);
        model.put("viewName", "home");
        return "home";
    }
}
