package org.test.elasticsearch.inject;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;

/**
 * Created by luchabou on 23/04/2014.
 */
public class ElasticSearchProvider {

    private Node node;
    private Client client;

    public void init() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("http.enabled", "false")
                .put("transport.tcp.port", "9300-9400")
                .put("discovery.zen.ping.multicast.enabled", "false")
                .put("discovery.zen.ping.unicast.hosts", "localhost").build();
        node = NodeBuilder.nodeBuilder().clusterName("workshop").client(true).settings(settings).node();
        client = node.client();
    }

    public Client getClient() {
        return client;
    }

    public void close() {
        node.close();
        node = null;
        client = null;
    }

}
