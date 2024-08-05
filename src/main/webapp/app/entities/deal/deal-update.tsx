import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IContacts } from 'app/shared/model/contacts.model';
import { getEntities as getContacts } from 'app/entities/contacts/contacts.reducer';
import { IDeal } from 'app/shared/model/deal.model';
import { stage } from 'app/shared/model/enumerations/stage.model';
import { getEntity, updateEntity, createEntity, reset } from './deal.reducer';

export const DealUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const contacts = useAppSelector(state => state.contacts.entities);
  const dealEntity = useAppSelector(state => state.deal.entity);
  const loading = useAppSelector(state => state.deal.loading);
  const updating = useAppSelector(state => state.deal.updating);
  const updateSuccess = useAppSelector(state => state.deal.updateSuccess);
  const stageValues = Object.keys(stage);

  const handleClose = () => {
    navigate('/deal' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getContacts({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.amount !== undefined && typeof values.amount !== 'number') {
      values.amount = Number(values.amount);
    }
    values.closing_date = convertDateTimeToServer(values.closing_date);
    if (values.probability !== undefined && typeof values.probability !== 'number') {
      values.probability = Number(values.probability);
    }
    if (values.expected_revenue !== undefined && typeof values.expected_revenue !== 'number') {
      values.expected_revenue = Number(values.expected_revenue);
    }

    const entity = {
      ...dealEntity,
      ...values,
      contacts: contacts.find(it => it.id.toString() === values.contacts?.toString()),
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
          closing_date: displayDefaultDateTime(),
        }
      : {
          stage: 'QUALIFICATION',
          ...dealEntity,
          closing_date: convertDateTimeFromServer(dealEntity.closing_date),
          contacts: dealEntity?.contacts?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="crmApp.deal.home.createOrEditLabel" data-cy="DealCreateUpdateHeading">
            Create or edit a Deal
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="deal-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Deal Name"
                id="deal-deal_name"
                name="deal_name"
                data-cy="deal_name"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Amount"
                id="deal-amount"
                name="amount"
                data-cy="amount"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Closing Date"
                id="deal-closing_date"
                name="closing_date"
                data-cy="closing_date"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Probability" id="deal-probability" name="probability" data-cy="probability" type="text" />
              <ValidatedField label="Next Step" id="deal-next_Step" name="next_Step" data-cy="next_Step" type="text" />
              <ValidatedField
                label="Expected Revenue"
                id="deal-expected_revenue"
                name="expected_revenue"
                data-cy="expected_revenue"
                type="text"
              />
              <ValidatedField
                label="Campaign Source"
                id="deal-campaign_source"
                name="campaign_source"
                data-cy="campaign_source"
                type="text"
              />
              <ValidatedField
                label="Description Information"
                id="deal-description_information"
                name="description_information"
                data-cy="description_information"
                type="text"
              />
              <ValidatedField label="Stage" id="deal-stage" name="stage" data-cy="stage" type="select">
                {stageValues.map(stage => (
                  <option value={stage} key={stage}>
                    {stage}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField id="deal-contacts" name="contacts" data-cy="contacts" label="Contacts" type="select" required>
                <option value="" key="0" />
                {contacts
                  ? contacts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.first_name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>This field is required.</FormText>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/deal" replace color="info">
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

export default DealUpdate;
