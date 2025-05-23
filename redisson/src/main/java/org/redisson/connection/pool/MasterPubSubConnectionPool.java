/**
 * Copyright (c) 2013-2024 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.connection.pool;

import org.redisson.client.RedisPubSubConnection;
import org.redisson.client.protocol.RedisCommand;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.connection.ConnectionManager;
import org.redisson.connection.MasterSlaveEntry;

import java.util.concurrent.CompletableFuture;

/**
 * Connection pool for Publish/Subscribe used with single node
 * 
 * @author Nikita Koksharov
 *
 */
public class MasterPubSubConnectionPool extends PubSubConnectionPool {

    public MasterPubSubConnectionPool(MasterSlaveServersConfig config, ConnectionManager connectionManager,
            MasterSlaveEntry masterSlaveEntry) {
        super(config, connectionManager, masterSlaveEntry);
    }

    @Override
    public CompletableFuture<RedisPubSubConnection> get(RedisCommand<?> command, boolean trackChanges) {
        return acquireConnection(command, masterSlaveEntry.getEntry(), trackChanges);
    }

}
