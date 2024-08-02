import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAccounts } from 'app/shared/model/accounts.model';
import { rating } from 'app/shared/model/enumerations/rating.model';
import { type } from 'app/shared/model/enumerations/type.model';
import { ownership } from 'app/shared/model/enumerations/ownership.model';
import { getEntity, updateEntity, createEntity, reset } from './accounts.reducer';

export const AccountsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const accountsEntity = useAppSelector(state => state.accounts.entity);
  const loading = useAppSelector(state => state.accounts.loading);
  const updating = useAppSelector(state => state.accounts.updating);
  const updateSuccess = useAppSelector(state => state.accounts.updateSuccess);
  const ratingValues = Object.keys(rating);
  const typeValues = Object.keys(type);
  const ownershipValues = Object.keys(ownership);

  const handleClose = () => {
    navigate('/accounts' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.phone !== undefined && typeof values.phone !== 'number') {
      values.phone = Number(values.phone);
    }
    if (values.account_number !== undefined && typeof values.account_number !== 'number') {
      values.account_number = Number(values.account_number);
    }
    if (values.employees !== undefined && typeof values.employees !== 'number') {
      values.employees = Number(values.employees);
    }
    if (values.sic_code !== undefined && typeof values.sic_code !== 'number') {
      values.sic_code = Number(values.sic_code);
    }

    const entity = {
      ...accountsEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          rating: 'NONE',
          account_type: 'NONE',
          ownership: 'NONE',
          ...accountsEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="crmApp.accounts.home.createOrEditLabel" data-cy="AccountsCreateUpdateHeading">
            Create or edit a Accounts
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="accounts-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Account Owner"
                id="accounts-account_owner"
                name="account_owner"
                data-cy="account_owner"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Rating" id="accounts-rating" name="rating" data-cy="rating" type="select">
                {ratingValues.map(rating => (
                  <option value={rating} key={rating}>
                    {rating}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Phone"
                id="accounts-phone"
                name="phone"
                data-cy="phone"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Account Site"
                id="accounts-account_site"
                name="account_site"
                data-cy="account_site"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Fax" id="accounts-fax" name="fax" data-cy="fax" type="text" />
              <ValidatedField
                label="Website"
                id="accounts-website"
                name="website"
                data-cy="website"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Account Number"
                id="accounts-account_number"
                name="account_number"
                data-cy="account_number"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField label="Ticket Symbol" id="accounts-ticket_Symbol" name="ticket_Symbol" data-cy="ticket_Symbol" type="text" />
              <ValidatedField label="Account Type" id="accounts-account_type" name="account_type" data-cy="account_type" type="select">
                {typeValues.map(type => (
                  <option value={type} key={type}>
                    {type}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Ownership" id="accounts-ownership" name="ownership" data-cy="ownership" type="select">
                {ownershipValues.map(ownership => (
                  <option value={ownership} key={ownership}>
                    {ownership}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Billing Street"
                id="accounts-billing_street"
                name="billing_street"
                data-cy="billing_street"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Billing City"
                id="accounts-billing_city"
                name="billing_city"
                data-cy="billing_city"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Billing State"
                id="accounts-billing_state"
                name="billing_state"
                data-cy="billing_state"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Billing Code"
                id="accounts-billing_code"
                name="billing_code"
                data-cy="billing_code"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Billing Country"
                id="accounts-billing_country"
                name="billing_country"
                data-cy="billing_country"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Shipping Street"
                id="accounts-shipping_street"
                name="shipping_street"
                data-cy="shipping_street"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Shipping City" id="accounts-shipping_city" name="shipping_city" data-cy="shipping_city" type="text" />
              <ValidatedField
                label="Shipping State"
                id="accounts-shipping_state"
                name="shipping_state"
                data-cy="shipping_state"
                type="text"
              />
              <ValidatedField label="Shipping Code" id="accounts-shipping_code" name="shipping_code" data-cy="shipping_code" type="text" />
              <ValidatedField
                label="Shipping Country"
                id="accounts-shipping_country"
                name="shipping_country"
                data-cy="shipping_country"
                type="text"
              />
              <ValidatedField
                label="Description"
                id="accounts-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Employees"
                id="accounts-employees"
                name="employees"
                data-cy="employees"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField label="Sic Code" id="accounts-sic_code" name="sic_code" data-cy="sic_code" type="text" />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/accounts" replace color="info">
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

export default AccountsUpdate;
