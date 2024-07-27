import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ILead } from 'app/shared/model/lead.model';
import { source } from 'app/shared/model/enumerations/source.model';
import { status } from 'app/shared/model/enumerations/status.model';
import { industry } from 'app/shared/model/enumerations/industry.model';
import { rating } from 'app/shared/model/enumerations/rating.model';
import { social } from 'app/shared/model/enumerations/social.model';
import { getEntity, updateEntity, createEntity, reset } from './lead.reducer';

export const LeadUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const leadEntity = useAppSelector(state => state.lead.entity);
  const loading = useAppSelector(state => state.lead.loading);
  const updating = useAppSelector(state => state.lead.updating);
  const updateSuccess = useAppSelector(state => state.lead.updateSuccess);
  const sourceValues = Object.keys(source);
  const statusValues = Object.keys(status);
  const industryValues = Object.keys(industry);
  const ratingValues = Object.keys(rating);
  const socialValues = Object.keys(social);

  const handleClose = () => {
    navigate('/lead');
  };

  useEffect(() => {
    if (!isNew) {
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
    if (values.no_of_emp !== undefined && typeof values.no_of_emp !== 'number') {
      values.no_of_emp = Number(values.no_of_emp);
    }
    if (values.annual_revenue !== undefined && typeof values.annual_revenue !== 'number') {
      values.annual_revenue = Number(values.annual_revenue);
    }
    if (values.zipcode !== undefined && typeof values.zipcode !== 'number') {
      values.zipcode = Number(values.zipcode);
    }
    if (values.phone !== undefined && typeof values.phone !== 'number') {
      values.phone = Number(values.phone);
    }

    const entity = {
      ...leadEntity,
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
          lead_source: 'NONE',
          lead_status: 'ATTEMPTED_TO_CONTACT',
          industry: 'ASP',
          rating: 'ACCIQUED',
          social_media: 'INSTAGRAM',
          ...leadEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="crmApp.lead.home.createOrEditLabel" data-cy="LeadCreateUpdateHeading">
            Create or edit a Lead
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="lead-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="First Name"
                id="lead-first_name"
                name="first_name"
                data-cy="first_name"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Last Name"
                id="lead-last_name"
                name="last_name"
                data-cy="last_name"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Company"
                id="lead-company"
                name="company"
                data-cy="company"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Title"
                id="lead-title"
                name="title"
                data-cy="title"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Email"
                id="lead-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Fax" id="lead-fax" name="fax" data-cy="fax" type="text" />
              <ValidatedField
                label="Website"
                id="lead-website"
                name="website"
                data-cy="website"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Lead Source" id="lead-lead_source" name="lead_source" data-cy="lead_source" type="select">
                {sourceValues.map(source => (
                  <option value={source} key={source}>
                    {source}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Lead Status" id="lead-lead_status" name="lead_status" data-cy="lead_status" type="select">
                {statusValues.map(status => (
                  <option value={status} key={status}>
                    {status}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Industry" id="lead-industry" name="industry" data-cy="industry" type="select">
                {industryValues.map(industry => (
                  <option value={industry} key={industry}>
                    {industry}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="No Of Emp"
                id="lead-no_of_emp"
                name="no_of_emp"
                data-cy="no_of_emp"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField label="Annual Revenue" id="lead-annual_revenue" name="annual_revenue" data-cy="annual_revenue" type="text" />
              <ValidatedField label="Rating" id="lead-rating" name="rating" data-cy="rating" type="select">
                {ratingValues.map(rating => (
                  <option value={rating} key={rating}>
                    {rating}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField label="Social Media" id="lead-social_media" name="social_media" data-cy="social_media" type="select">
                {socialValues.map(social => (
                  <option value={social} key={social}>
                    {social}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label="Media Handle Id"
                id="lead-media_handle_id"
                name="media_handle_id"
                data-cy="media_handle_id"
                type="text"
              />
              <ValidatedField
                label="Street"
                id="lead-street"
                name="street"
                data-cy="street"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="City"
                id="lead-city"
                name="city"
                data-cy="city"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  minLength: { value: 0, message: 'This field is required to be at least 0 characters.' },
                  maxLength: { value: 20, message: 'This field cannot be longer than 20 characters.' },
                  pattern: { value: /^[a-zA-Z0-9]*$/, message: 'This field should follow pattern for ^[a-zA-Z0-9]*.' },
                }}
              />
              <ValidatedField
                label="State"
                id="lead-state"
                name="state"
                data-cy="state"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField
                label="Zipcode"
                id="lead-zipcode"
                name="zipcode"
                data-cy="zipcode"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField
                label="Country"
                id="lead-country"
                name="country"
                data-cy="country"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                }}
              />
              <ValidatedField label="Description" id="lead-description" name="description" data-cy="description" type="text" />
              <ValidatedBlobField label="Lead Image" id="lead-lead_image" name="lead_image" data-cy="lead_image" openActionLabel="Open" />
              <ValidatedField
                label="Phone"
                id="lead-phone"
                name="phone"
                data-cy="phone"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/lead" replace color="info">
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

export default LeadUpdate;
