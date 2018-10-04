package io.vpv.version.springbootversion.modal;

import java.util.List;

/**
 * Created by vprasanna on 10/4/18.
 */
public class VersionInfo {

    List<String> milestones;
    List<String> snapshots;

    public VersionInfo(List<String> milestones, List<String> snapshots) {
        this.milestones = milestones;
        this.snapshots = snapshots;
    }

    public List<String> getMilestones() {
        return milestones;
    }

    public List<String> getSnapshots() {
        return snapshots;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("io.vpv.version.springbootversion.modal.VersionInfo{");
        sb.append("milestones=").append(milestones);
        sb.append(", snapshots=").append(snapshots);
        sb.append('}');
        return sb.toString();
    }
}
