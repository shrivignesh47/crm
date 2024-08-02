import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './contacts.reducer';

export const ContactsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const contactsEntity = useAppSelector(state => state.contacts.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="contactsDetailsHeading">Contacts</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{contactsEntity.id}</dd>
          <dt>
            <span id="first_name">First Name</span>
          </dt>
          <dd>{contactsEntity.first_name}</dd>
          <dt>
            <span id="last_name">Last Name</span>
          </dt>
          <dd>{contactsEntity.last_name}</dd>
          <dt>
            <span id="account_name">Account Name</span>
          </dt>
          <dd>{contactsEntity.account_name}</dd>
          <dt>
            <span id="vendor_name">Vendor Name</span>
          </dt>
          <dd>{contactsEntity.vendor_name}</dd>
          <dt>
            <span id="lead_source">Lead Source</span>
          </dt>
          <dd>{contactsEntity.lead_source}</dd>
          <dt>
            <span id="email">Email</span>
          </dt>
          <dd>{contactsEntity.email}</dd>
          <dt>
            <span id="title">Title</span>
          </dt>
          <dd>{contactsEntity.title}</dd>
          <dt>
            <span id="phone">Phone</span>
          </dt>
          <dd>{contactsEntity.phone}</dd>
          <dt>
            <span id="department">Department</span>
          </dt>
          <dd>{contactsEntity.department}</dd>
          <dt>
            <span id="mobile">Mobile</span>
          </dt>
          <dd>{contactsEntity.mobile}</dd>
          <dt>
            <span id="fax">Fax</span>
          </dt>
          <dd>{contactsEntity.fax}</dd>
          <dt>
            <span id="date_of_birth">Date Of Birth</span>
          </dt>
          <dd>
            {contactsEntity.date_of_birth ? (
              <TextFormat value={contactsEntity.date_of_birth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="social_media_handle">Social Media Handle</span>
          </dt>
          <dd>{contactsEntity.social_media_handle}</dd>
          <dt>
            <span id="street">Street</span>
          </dt>
          <dd>{contactsEntity.street}</dd>
          <dt>
            <span id="city">City</span>
          </dt>
          <dd>{contactsEntity.city}</dd>
          <dt>
            <span id="state">State</span>
          </dt>
          <dd>{contactsEntity.state}</dd>
          <dt>
            <span id="zip">Zip</span>
          </dt>
          <dd>{contactsEntity.zip}</dd>
          <dt>
            <span id="country">Country</span>
          </dt>
          <dd>{contactsEntity.country}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{contactsEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/contacts" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/contacts/${contactsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ContactsDetail;
