package com.simao.giao;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.max.MaxBuilder;
import org.joda.time.DateTime;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ESClient {

    Client client;

    ESClient() {
        try {
            client = TransportClient.builder().settings(Settings.builder().put("cluster.name", "elasticsearch").build()).build()
                    .addTransportAddress(new InetSocketTransportAddress(
                            InetAddress.getByName("10.0.9.240"), Integer.valueOf(9300)));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private DateTime startTime;
    public void search() {
        try {
            // todo:无索引状态下异常解决
            SearchRequestBuilder builder = client.prepareSearch("soda-orders").setTypes("orders").setSearchType(SearchType.COUNT);
            MaxBuilder termsBuilder = AggregationBuilders.max("agg").field("结束时间");

            builder.addAggregation(termsBuilder);
            SearchResponse response = builder.execute().actionGet();
            Max max = response.getAggregations().get("agg");
            double value = max.getValue();
            startTime = (max != null) ? new DateTime(new Double(value).longValue()) : DateTime.now();
            System.out.println(startTime.toString("yyyy-MM-dd HH:mm:ss"));
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    public static void main(String[] args) {
        ESClient esClient = new ESClient();
        esClient.search();
    }
}

