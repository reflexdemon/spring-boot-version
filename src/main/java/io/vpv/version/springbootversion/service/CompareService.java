package io.vpv.version.springbootversion.service;

import io.vpv.version.springbootversion.modal.Artifact;
import io.vpv.version.springbootversion.modal.Dependency;
import io.vpv.version.springbootversion.modal.VersionSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by vprasanna on 10/23/18.
 */
@Service
public class CompareService {

    private BootVersionService bootVersionService;

    @Autowired
    public CompareService(BootVersionService bootVersionService) {
        this.bootVersionService = bootVersionService;
    }

    private boolean isEmpty(final List<Dependency> list) {
        return (null == list) || (list.isEmpty());
    }

    public VersionSummary merge(final String first, final String second) {
        return merge(bootVersionService.getDependencies(first), bootVersionService.getDependencies(second));
    }

    public VersionSummary merge(final List<Dependency> first, final List<Dependency> second) {
        if (!isEmpty(first) && !isEmpty(second)) {

            VersionSummary summary = new VersionSummary();
            //Doing this as i ensure i dont run into empty list
            summary.setFirstBootVersion(first.stream()
                    .map(dep -> dep.getBootVersion())
                    .findFirst()
                    .orElse(null));
            summary.setSecondBootVersion(second.stream()
                    .map(dep -> dep.getBootVersion())
                    .findFirst()
                    .orElse(null));
            Map<String, Dependency> firstMap = parseListToMap(first);
            Map<String, Dependency> secondMap = parseListToMap(second);

            Set<String> completeKeySet = new TreeSet<>(firstMap.keySet());
            completeKeySet.addAll(secondMap.keySet());

            completeKeySet.stream()
                    .forEach(
                            key -> {
                                Dependency f = firstMap.get(key);
                                Dependency s = secondMap.get(key);
                                if (s == null) {
                                    // Dependency has been removed
                                    summary.getArtifacts().add(new Artifact(
                                            f.getGroupId(), f.getArtifactId(), f.getVersion(), null
                                    ));
                                } else if (f == null){
                                    //New dependency!
                                    summary.getArtifacts().add(new Artifact(
                                            s.getGroupId(), s.getArtifactId(), null, s.getVersion()
                                    ));
                                } else {
                                    summary.getArtifacts().add(new Artifact(
                                            f.getGroupId(), f.getArtifactId(), f.getVersion(), s.getVersion()
                                    ));
                                }
                            }
                    );

            return summary;
        }

        return null;
    }

    private Map<String, Dependency> parseListToMap(List<Dependency> first) {
        if (null == first || first.isEmpty()) {
            return null;
        }
        final Map<String, Dependency> entries = new HashMap<>();

        first.stream()
                .forEach(item ->
                        entries.put(item.getGroupId() + ":" + item.getArtifactId(), item)
                );

        return entries;
    }
}
