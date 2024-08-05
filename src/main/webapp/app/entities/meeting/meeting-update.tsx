import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { IContacts } from 'app/shared/model/contacts.model';
import { getEntities as getContacts } from 'app/entities/contacts/contacts.reducer';
import { ILead } from 'app/shared/model/lead.model';
import { getEntities as getLeads } from 'app/entities/lead/lead.reducer';
import { IMeeting } from 'app/shared/model/meeting.model';
import { location } from 'app/shared/model/enumerations/location.model';
import { participants } from 'app/shared/model/enumerations/participants.model';
import { getEntity, updateEntity, createEntity, reset } from './meeting.reducer';

export const MeetingUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const contacts = useAppSelector(state => state.contacts.entities);
  const leads = useAppSelector(state => state.lead.entities);
  const meetingEntity = useAppSelector(state => state.meeting.entity);
  const loading = useAppSelector(state => state.meeting.loading);
  const updating = useAppSelector(state => state.meeting.updating);
  const updateSuccess = useAppSelector(state => state.meeting.updateSuccess);
  const locationValues = Object.keys(location);
  const participantsValues = Object.keys(participants);

  const handleClose = () => {
    navigate('/meeting' + location);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getUsers({}));
    dispatch(getContacts({}));
    dispatch(getLeads({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    values.current_time = convertDateTimeToServer(values.current_time);
    values.from = convertDateTimeToServer(values.from);
    values.to = convertDateTimeToServer(values.to);

    const entity = {
      ...meetingEntity,
      ...values,
      user: users.find(it => it.id.toString() === values.user?.toString()),
      contacts: contacts.find(it => it.id.toString() === values.contacts?.toString()),
      lead: leads.find(it => it.id.toString() === values.lead?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          current_time: displayDefaultDateTime(),
          from: displayDefaultDateTime(),
          to: displayDefaultDateTime(),
        }
      : {
          location: 'ONLINE',
          participants: 'OFFLINE',
          ...meetingEntity,
          current_time: convertDateTimeFromServer(meetingEntity.current_time),
          from: convertDateTimeFromServer(meetingEntity.from),
          to: convertDateTimeFromServer(meetingEntity.to),
          user: meetingEntity?.user?.id,
          contacts: meetingEntity?.contacts?.id,
          lead: meetingEntity?.lead?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="crmApp.meeting.home.createOrEditLabel" data-cy="MeetingCreateUpdateHeading">
            Create or edit a Meeting
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="meeting-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Title"
                id="meeting-title"
                name="title"
                data-cy="title"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Location" id="meeting-location" name="location" data-cy="location" type="select">
                {locationValues.map(location => (
                  <option value={location} key={location}>
                    {location}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Offline Location"
                id="meeting-offline_location"
                name="offline_location"
                data-cy="offline_location"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Current Time"
                id="meeting-current_time"
                name="current_time"
                data-cy="current_time"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="From"
                id="meeting-from"
                name="from"
                data-cy="from"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="To"
                id="meeting-to"
                name="to"
                data-cy="to"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Participants" id="meeting-participants" name="participants" data-cy="participants" type="select">
                {participantsValues.map(participants => (
                  <option value={participants} key={participants}>
                    {participants}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField id="meeting-user" name="user" data-cy="user" label="User" type="select" required>
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.login}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>This field is required.</FormText>
              <ValidatedField id="meeting-contacts" name="contacts" data-cy="contacts" label="Contacts" type="select">
                <option value="" key="0" />
                {contacts
                  ? contacts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.first_name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField id="meeting-lead" name="lead" data-cy="lead" label="Lead" type="select">
                <option value="" key="0" />
                {leads
                  ? leads.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.email}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/meeting" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default MeetingUpdate;
