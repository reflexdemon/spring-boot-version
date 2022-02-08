package io.vpv.version.springbootversion.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
import java.util.TreeMap;

@Controller
public class AboutController {


    private final BuildProperties buildProperties;

    @Autowired
    public AboutController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }


    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String aboutPage(ModelMap model) {


        model.put("viewName", "about");
        model.put("buildInfo", getBuildInfo());
        return "about";
    }


    private Map<String, String> getBuildInfo(){
        final Map<String, String> map = new TreeMap<>();
        buildProperties.forEach(item -> map.put(item.getKey(), item.getValue()));
        return map;
    }
}
