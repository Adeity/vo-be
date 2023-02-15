alter table device
rename column oauth_token to user_access_token;

alter table device
    rename column device_id to research_number;

alter table device
    add primary key (id);

alter table device
    add constraint usr_acs_tkn_uniq unique (user_access_token);

alter table sleeps
    add column device_id bigint;

alter table sleeps
add constraint slps_device_id_FK  foreign key (device_id) references device (id);

alter table sleeps drop column delete_flag;
alter table sleeps drop column user_id;

DO $$
    DECLARE
        device record;
    BEGIN
        FOR device IN (
            SELECT
                dev.id,
                dev.user_access_token
            FROM
                device dev
        ) LOOP
                UPDATE sleeps SET device_id = device.id WHERE user_access_token = device.user_access_token;
            END LOOP;
    END $$;
