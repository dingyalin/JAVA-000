/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.activemq.mqtt.mqtt;

import org.fusesource.hawtbuf.AsciiBuffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

/**
 * @author lw1243925457
 */
public class Publisher {

    public static void main(String[] args) throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost("localhost", 1883);
        mqtt.setUserName("admin");
        mqtt.setPassword("admin");

        FutureConnection connection = mqtt.futureConnection();
        connection.connect().await();
        System.out.println("connect");

        int messageAmount = 10;
        UTF8Buffer topic = new UTF8Buffer("mqttTest");
        while (messageAmount > 0) {
            connection.publish(topic, new AsciiBuffer("test message" + messageAmount), QoS.AT_LEAST_ONCE, false);
            System.out.println("send message " + messageAmount);
            messageAmount -= 1;
        }

        connection.disconnect().await();
        System.out.println("disconnect");
    }
}
