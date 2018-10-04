package io.vpv.version.springbootversion.controller.api;

import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.ErrorResponse;
import io.vpv.version.springbootversion.service.BootVersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by vprasanna on 6/12/18.
 */
@RestController
@RequestMapping("/api")
public class RESTBootVersionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private BootVersionService bootVersionService;

    @Autowired
    public RESTBootVersionService(BootVersionService bootVersionService) {
        this.bootVersionService = bootVersionService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/dependency/{bootVersion}")
    public ResponseEntity<List<Dependency>> getDependenciesForVersion(@PathVariable final String bootVersion) {
        logger.debug("GET Boot Dependency Version API called");
            List<Dependency> dependencies = bootVersionService.getDependencies(bootVersion);
            return new ResponseEntity<>(dependencies, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/releases")
    public ResponseEntity<List<String>> getBootVersion() {
        logger.debug("GET Boot Version API called");
        List<String> list = bootVersionService.getVersionList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> error(Exception ex) {
        logger.error("Exception raised " + ex);
        ErrorResponse response = new ErrorResponse("001", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
