package io.vpv.version.springbootversion.controller.api;

import io.vpv.version.springbootversion.modal.DependencyDetails;
import io.vpv.version.springbootversion.modal.VersionInfo;
import io.vpv.version.springbootversion.service.BootVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by vprasanna on 6/12/18.
 */
@RestController
public class RESTBootVersionService extends RESTBaseClass {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private BootVersionService bootVersionService;

    @Autowired
    public RESTBootVersionService(BootVersionService bootVersionService) {
        this.bootVersionService = bootVersionService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/dependency/{bootVersion:.+}")
    public ResponseEntity<DependencyDetails> getDependenciesForVersion(@PathVariable final String bootVersion) {
        logger.debug("GET Boot Dependency Version API called");
        DependencyDetails dependencies = bootVersionService.getDependencies(bootVersion);
            return new ResponseEntity<>(dependencies, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/releases")
    public ResponseEntity<List<String>> getBootVersion() {
        logger.debug("GET Boot Version API called");
        List<String> list = bootVersionService.getVersionList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/milestones")
    public ResponseEntity<List<String>> getMileStoneVersionList() {
        logger.debug("GET Boot Version API called");
        List<String> list = bootVersionService.getMileStoneVersionList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/snapshot")
    public ResponseEntity<List<String>> getSnapshotVersionList() {
        logger.debug("GET Boot Version API called");
        List<String> list = bootVersionService.getSnapshotVersionList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/versions")
    public ResponseEntity<VersionInfo> getAllVersionInfo() {
        logger.debug("GET Boot Version API called");
        VersionInfo list = bootVersionService.getAllVersionInfo();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
