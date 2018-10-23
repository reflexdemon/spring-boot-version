package io.vpv.version.springbootversion.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DependencyController {

    @RequestMapping(value = {"/dependency"}, method = RequestMethod.GET)
    public String dependencyPage(ModelMap model) {
        model.put("viewName", "dependency");
        return "dependency";
    }
}
