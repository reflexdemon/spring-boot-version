package io.vpv.version.springbootversion.controller.api;

import io.vpv.version.springbootversion.modal.VersionSummary;
import io.vpv.version.springbootversion.service.CompareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by vprasanna on 6/12/18.
 */
@RestController
public class RESTCompareService extends RESTBaseClass {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CompareService compareService;

    @Autowired
    public RESTCompareService(CompareService compareService) {
        this.compareService= compareService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/compare/{firstVersion:.+}/{secondVersion:.+}")
    public ResponseEntity<VersionSummary> merge(@PathVariable final String firstVersion,
                                                @PathVariable final String secondVersion, HttpServletRequest request) {
        logger.debug("GET Compare Report");
        logger.debug("request.getRemoteHost() = " + request.getRemoteHost());
        VersionSummary dependencies = compareService.merge(firstVersion, secondVersion);
        return new ResponseEntity<>(dependencies, HttpStatus.OK);
    }

}
