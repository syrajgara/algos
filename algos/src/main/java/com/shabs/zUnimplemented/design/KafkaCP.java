package com.shabs.zUnimplemented.design;

/**
 * Kafka Architecture
 *
 * 2 Topics, client (userid) sending 2 different events to these topics
 * one of the topic could be delayed due to some reason.
 *
 * How do you do a streaming join between these 2 topics.
 *
 * Windowing has to be done on both KStreams.
 */
public class KafkaCP {
}
