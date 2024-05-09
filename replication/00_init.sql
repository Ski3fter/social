create user replicator with replication password 'replicator_password';
select pg_create_physical_replication_slot('replication_slot');