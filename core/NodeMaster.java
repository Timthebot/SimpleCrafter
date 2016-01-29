package timthebot.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TimTheBot
 * 26/01/2016
 */
public abstract class NodeMaster {

    private List<Node> nodes;

    private String currentStatus;

    public NodeMaster() {
        this.nodes = new ArrayList<>();
    }

    public NodeMaster(List<Node> nodes) {
        this.nodes = nodes;
    }

    public abstract boolean validate();
    public int execute() {
        for (Node node : nodes) {
            if (node.validate()) {
                String newStatus = node.getStatus();
                if (newStatus != currentStatus) {
                    currentStatus = newStatus;
                    node.getApi().log(newStatus);
                }
                return node.execute();
            }
        }
        return 1000;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void add(Node newNode) {
        nodes.add(newNode);
    }
}
